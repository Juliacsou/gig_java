package br.com.fiap.gig.application.service;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Avaliacao;
import br.com.fiap.gig.domain.model.Score;
import br.com.fiap.gig.domain.repository.ScoreRepository;
import br.com.fiap.gig.domain.service.ScoreService;

import java.util.List;

public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;

    public ScoreServiceImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    public Score criarScore(Score score) {
        return null;
    }

    @Override
    public Score buscarScore(String cpf_usuario) throws EntidadeNaoLocalizada {
        return null;
    }

    @Override
    public Score calcularScore(Score score, List<Avaliacao> avaliacoes) {
        return null;
    }
}
