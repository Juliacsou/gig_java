package br.com.fiap.gig.domain.service;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Score;

public interface ScoreService {
    Score criarScore(Score score);
    Score buscarScore(String cpf_usuario) throws EntidadeNaoLocalizada;
    void atualizarScore(String cpf);
}
