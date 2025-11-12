package br.com.fiap.gig.infrastructure.persistence;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Avaliacao;
import br.com.fiap.gig.domain.model.Score;
import br.com.fiap.gig.domain.repository.ScoreRepository;

import java.util.List;

public class JdbcScoreRepository implements ScoreRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcScoreRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
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
