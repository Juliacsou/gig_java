package br.com.fiap.gig.application.service;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Avaliacao;
import br.com.fiap.gig.domain.model.Competencia;
import br.com.fiap.gig.domain.model.Score;
import br.com.fiap.gig.domain.repository.AvaliacaoRepository;
import br.com.fiap.gig.domain.repository.CompetenciaRepository;
import br.com.fiap.gig.domain.repository.ScoreRepository;
import br.com.fiap.gig.domain.service.ScoreService;

import java.util.List;

public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final AvaliacaoRepository avaliacaoRepository;
    private final CompetenciaRepository competenciaRepository;

    public ScoreServiceImpl(ScoreRepository scoreRepository, AvaliacaoRepository avaliacaoRepository, CompetenciaRepository competenciaRepository) {
        this.scoreRepository = scoreRepository;
        this.avaliacaoRepository = avaliacaoRepository;
        this.competenciaRepository = competenciaRepository;
    }

    @Override
    public Score criarScore(Score score) {
        return scoreRepository.criarScore(score);
    }

    @Override
    public Score buscarScore(String cpf_usuario) throws EntidadeNaoLocalizada {
        try{
            this.atualizarScore(cpf_usuario);
            return scoreRepository.buscarScore(cpf_usuario);
        } catch (EntidadeNaoLocalizada e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void atualizarScore(String cpf) {
        Score score = this.calcularScore(cpf);
        scoreRepository.atualizarScore(score);
    }

    public Score calcularScore(String cpfUsuario){
        List<Avaliacao> avaliacoes = avaliacaoRepository.buscarAvaliacaoUsuario(cpfUsuario);

        if (avaliacoes == null || avaliacoes.isEmpty()) {
            throw new RuntimeException("Usuário " + cpfUsuario + " ainda não possui avaliações.");
        }


        double notaMedia = avaliacoes.stream().mapToInt(Avaliacao::getNota).average().orElse(0.0);

        List<Competencia> competenciasUsuario = competenciaRepository.buscarCompetenciaUsuario(cpfUsuario);

        double somaPesos = 0.0;
        if (competenciasUsuario != null) {
            somaPesos = competenciasUsuario.stream()
                    .mapToDouble(Competencia::getPeso)
                    .sum();
        }

        double scoreBase = (notaMedia / 5.0) * 100.0;
        double bonus = Math.min(10.0, somaPesos);
        double scoreTotal = Math.min(100.0, scoreBase + bonus);

        Score score = new Score();
        score.getUsuario().setCpf_usuario(cpfUsuario);
        score.setNota_media((float) notaMedia);
        score.setScore_total((float) scoreTotal);

        return score;
    }
    }

