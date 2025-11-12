package br.com.fiap.gig.interfaces;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Avaliacao;
import br.com.fiap.gig.domain.model.Score;
import br.com.fiap.gig.domain.service.ScoreService;

import java.util.List;

public class ScoreControllerImpl implements ScoreController{

    private final ScoreService scoreService;

    public ScoreControllerImpl(ScoreService scoreService) {
        this.scoreService = scoreService;
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
