package br.com.fiap.gig.application.service;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Avaliacao;
import br.com.fiap.gig.domain.model.Competencia;
import br.com.fiap.gig.domain.repository.AvaliacaoRepository;
import br.com.fiap.gig.domain.repository.CompetenciaRepository;
import br.com.fiap.gig.domain.service.AvaliacaoService;

import java.util.List;

public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final CompetenciaRepository competenciaRepository;

    public AvaliacaoServiceImpl(AvaliacaoRepository avaliacaoRepository, CompetenciaRepository competenciaRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.competenciaRepository = competenciaRepository;
    }

    @Override
    public Avaliacao criarAvaliacao(Avaliacao avaliacao) {
        int nota = avaliacao.getNota();
        if (nota < 0 || nota > 5) {
            throw new IllegalArgumentException("A nota da avaliação deve estar entre 0 e 5.");
        }
        return avaliacaoRepository.criarAvaliacao(avaliacao);
    }

    @Override
    public void editarAvaliacao(Avaliacao avaliacao) {
        try {
            Avaliacao avaliacaoExistente = avaliacaoRepository.buscarAvaliacao(avaliacao.getId_avaliacao());
            avaliacaoRepository.editarAvaliacao(avaliacao);
        } catch (EntidadeNaoLocalizada e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Avaliacao buscarAvaliacao(int id_avaliacao) throws EntidadeNaoLocalizada {
        return avaliacaoRepository.buscarAvaliacao(id_avaliacao);
    }

    @Override
    public List<Avaliacao> buscarAvaliacaoUsuario(String cpf_usuario) {
        return avaliacaoRepository.buscarAvaliacaoUsuario(cpf_usuario);
    }

    @Override
    public void adicionarCompetencias(int idAvaliacao, int idCompetencia) {
        try{
            Avaliacao avaliacaoExistente = avaliacaoRepository.buscarAvaliacao(idAvaliacao);
            Competencia competenciaExistente = competenciaRepository.buscarCompetencia(idCompetencia);
            avaliacaoRepository.adicionarCompetencias(idAvaliacao, idCompetencia);
        } catch (EntidadeNaoLocalizada e) {
            throw new RuntimeException(e);
        }
    }
}
