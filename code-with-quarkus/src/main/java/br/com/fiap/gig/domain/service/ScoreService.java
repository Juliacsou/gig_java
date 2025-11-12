package br.com.fiap.gig.domain.service;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Avaliacao;
import br.com.fiap.gig.domain.model.Score;

import java.util.List;

public interface ScoreService {
    Score criarScore(Score score);
    Score buscarScore(String cpf_usuario) throws EntidadeNaoLocalizada;
    Score calcularScore(Score score, List<Avaliacao> avaliacoes);
}
