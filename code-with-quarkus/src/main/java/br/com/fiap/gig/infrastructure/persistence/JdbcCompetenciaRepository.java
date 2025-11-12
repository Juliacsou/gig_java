package br.com.fiap.gig.infrastructure.persistence;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Competencia;
import br.com.fiap.gig.domain.repository.CompetenciaRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCompetenciaRepository implements CompetenciaRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcCompetenciaRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Competencia criarCompetencia(Competencia competencia) {
        String sql = """
        INSERT INTO T_GIG_COMPETENCIA (NOME_COMPETENCIA, PESO)
        VALUES (?, ?)
        """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[] {"ID_COMPETENCIA"})) {
            stmt.setString(1, competencia.getNome_competencia());
            stmt.setDouble(2, competencia.getPeso());

            int affected = stmt.executeUpdate();

            if (affected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        competencia.setId_competencia(rs.getInt(1));
                    }
                }
            }
            return competencia;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editarCompetencia(Competencia competencia) {
        String sql = """
        UPDATE T_GIG_COMPETENCIA
           SET NOME_COMPETENCIA = ?,
               PESO = ?
         WHERE ID_COMPETENCIA = ?
        """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, competencia.getNome_competencia());
            stmt.setDouble(2, competencia.getPeso());
            stmt.setInt(3, competencia.getId_competencia());

            int affected = stmt.executeUpdate();
            if (affected == 0) {
                throw new EntidadeNaoLocalizada("Competência não encontrada)");
            }
        } catch (SQLException | EntidadeNaoLocalizada e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Competencia buscarCompetencia(int id_competencia) throws EntidadeNaoLocalizada {
        String sql = """
        SELECT ID_COMPETENCIA, NOME_COMPETENCIA, PESO
          FROM T_GIG_COMPETENCIA
         WHERE ID_COMPETENCIA = ?
        """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_competencia);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("ID_COMPETENCIA");
                    String nome = rs.getString("NOME_COMPETENCIA");
                    float peso = rs.getFloat("PESO");
                    return new Competencia(id, nome, peso);
                } else {
                    throw new EntidadeNaoLocalizada("Competência com não encontrada.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar competência: " + e.getMessage(), e);
        }
    }

    @Override
    public void excluirCompetencia(int id_competencia) {
        String sql = """
        DELETE FROM T_GIG_COMPETENCIA
         WHERE ID_COMPETENCIA = ?
        """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_competencia);

            int affected = stmt.executeUpdate();

            if (affected == 0) {
                throw new EntidadeNaoLocalizada("Nenhuma competência encontrada");
            }

        } catch (SQLException | EntidadeNaoLocalizada e) {
            throw new RuntimeException("Erro ao excluir competência: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Competencia> BuscarCompetenciaUsuario(String cpf_usuario) {
        String sql = """
        SELECT DISTINCT c.ID_COMPETENCIA, c.NOME_COMPETENCIA, c.PESO
          FROM T_GIG_COMPETENCIA c
          JOIN T_GIG_AVALIACAO_COMPETENCIA ac
            ON ac.ID_COMPETENCIA = c.ID_COMPETENCIA
          JOIN T_GIG_AVALIACAO a
            ON a.ID_AVALIACAO = ac.ID_AVALIACAO
         WHERE a.CPF_AVALIADO = ?
        """;

        List<Competencia> competencias = new ArrayList<>();

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf_usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Competencia competencia = new Competencia();
                    competencia.setId_competencia(rs.getInt("ID_COMPETENCIA"));
                    competencia.setNome_competencia(rs.getString("NOME_COMPETENCIA"));
                    competencia.setPeso(rs.getFloat("PESO"));
                    competencias.add(competencia);
                }
            }

            return competencias;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar competências do usuário avaliado: " + cpf_usuario, e);
        }
    }
}
