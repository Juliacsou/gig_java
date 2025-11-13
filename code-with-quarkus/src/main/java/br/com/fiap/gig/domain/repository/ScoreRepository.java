package br.com.fiap.gig.domain.repository;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Score;

public interface ScoreRepository {
    Score criarScore(Score score);
    Score buscarScore(String cpf_usuario) throws EntidadeNaoLocalizada;
    void atualizarScore(Score score);
}
