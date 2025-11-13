package br.com.fiap.gig.infrastructure.persistence;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Score;
import br.com.fiap.gig.domain.model.Usuario;
import br.com.fiap.gig.domain.repository.ScoreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcScoreRepository implements ScoreRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcScoreRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Score criarScore(Score score) {
        String sql = """
        INSERT INTO T_GIG_SCORE
            (SCORE_TOTAL, NOTA_MEDIA, CPF_USUARIO)
        VALUES (?, ?, ?)
        """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID_SCORE"})) {

            stmt.setDouble(1, score.getScore_total());
            stmt.setDouble(2, score.getNota_media());
            stmt.setString(3, score.getUsuario().getCpf_usuario());

            int affected = stmt.executeUpdate();

            if (affected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        score.setId_score(rs.getInt(1));
                    }
                }
            }

            return score;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar score", e);
        }
    }

    @Override
    public Score buscarScore(String cpf_usuario) throws EntidadeNaoLocalizada {
        String sql = """
        SELECT ID_SCORE,
               SCORE_TOTAL,
               NOTA_MEDIA,
               CPF_USUARIO
          FROM T_GIG_SCORE
         WHERE CPF_USUARIO = ?
        """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf_usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Score score = new Score();
                    score.setId_score(rs.getInt("ID_SCORE"));
                    score.setScore_total(rs.getFloat("SCORE_TOTAL"));
                    score.setNota_media(rs.getFloat("NOTA_MEDIA"));

                    Usuario usuario = new Usuario();
                    usuario.setCpf_usuario(rs.getString("CPF_USUARIO"));
                    score.setUsuario(usuario);

                    return score;
                }
            }

            throw new EntidadeNaoLocalizada("Score para esse usuário não encontrado.");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar score do usuário " + cpf_usuario, e);
        }
    }

    @Override
    public void atualizarScore(Score score) {
        String sql = """
        UPDATE T_GIG_SCORE
           SET SCORE_TOTAL = ?,
               NOTA_MEDIA = ?
         WHERE CPF_USUARIO = ?
        """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, score.getScore_total());
            stmt.setDouble(2, score.getNota_media());
            stmt.setString(3, score.getUsuario().getCpf_usuario());

            int affected = stmt.executeUpdate();

            if (affected == 0) {
                throw new RuntimeException("Nenhum score encontrado para este usuário");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar score do usuário");
        }
    }
}
