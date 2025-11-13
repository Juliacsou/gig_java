package br.com.fiap.gig.infrastructure.persistence;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Avaliacao;
import br.com.fiap.gig.domain.model.Usuario;
import br.com.fiap.gig.domain.repository.AvaliacaoRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcAvaliacaoRepository implements AvaliacaoRepository {

    private final DatabaseConnection databaseConnection;

    public JdbcAvaliacaoRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Avaliacao criarAvaliacao(Avaliacao avaliacao) {
        String sql = """
        INSERT INTO T_GIG_AVALIACAO
            (COM_AVALIACAO, NOTA_AVALIACAO, DT_AVALIACAO, CPF_AVALIADOR, CPF_AVALIADO)
        VALUES (?, ?, SYSDATE, ?, ?)
        """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, new String[] {"ID_AVALIACAO"})) {

            stmt.setString(1, avaliacao.getCom_avaliacao());
            stmt.setInt(2, avaliacao.getNota());
            stmt.setString(3, avaliacao.getAvaliador().getCpf_usuario());
            stmt.setString(4, avaliacao.getAvaliado().getCpf_usuario());

            int affected = stmt.executeUpdate();

            if (affected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        avaliacao.setId_avaliacao(rs.getInt(1));
                    }
                }
            }

            return avaliacao;

        } catch ( SQLException e) {
            throw new RuntimeException("Erro ao criar avaliação", e);
        }
    }

    @Override
    public void editarAvaliacao(Avaliacao avaliacao) {
        String sql = """
            UPDATE T_GIG_AVALIACAO
            SET COM_AVALIACAO = ?,
               NOTA_AVALIACAO = ?,
            WHERE ID_AVALIACAO = ?
        """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, avaliacao.getCom_avaliacao());
            stmt.setInt(2, avaliacao.getNota());
            stmt.setInt(3, avaliacao.getId_avaliacao());

            int affected = stmt.executeUpdate();

            if (affected == 0) {
                throw new EntidadeNaoLocalizada("Nenhuma avaliação encontrada");
            }

        } catch (SQLException | EntidadeNaoLocalizada e) {
            throw new RuntimeException("Erro ao editar avaliação", e);
        }
    }

    @Override
    public Avaliacao buscarAvaliacao(int id_avaliacao) throws EntidadeNaoLocalizada {
        String sql = """
        SELECT  a.ID_AVALIACAO,
                a.COM_AVALIACAO,
                a.NOTA_AVALIACAO,
                a.DT_AVALIACAO,
                a.CPF_AVALIADOR,
                a.CPF_AVALIADO,
                u.NOME_USUARIO AS NOME_AVALIADOR
          FROM  T_GIG_AVALIACAO a
          JOIN  T_GIG_USUARIO u
            ON  u.CPF_USUARIO = a.CPF_AVALIADOR
         WHERE  a.ID_AVALIACAO = ?
        """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_avaliacao);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new EntidadeNaoLocalizada(
                            "Avaliação com ID " + id_avaliacao + " não encontrada."
                    );
                }

                Avaliacao avaliacao = new Avaliacao();
                avaliacao.setId_avaliacao(rs.getInt("ID_AVALIACAO"));
                avaliacao.setCom_avaliacao(rs.getString("COM_AVALIACAO"));
                avaliacao.setNota(rs.getInt("NOTA_AVALIACAO"));
                avaliacao.setDt_avaliacao(rs.getString("DT_AVALIACAO"));


                Usuario avaliador = new Usuario();
                avaliador.setCpf_usuario(rs.getString("CPF_AVALIADOR"));
                avaliador.setNome_usuario(rs.getString("NOME_AVALIADOR"));


                Usuario avaliado = new Usuario();
                avaliado.setCpf_usuario(rs.getString("CPF_AVALIADO"));

                avaliacao.setAvaliador(avaliador);
                avaliacao.setAvaliado(avaliado);

                return avaliacao;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar avaliação", e);
        }
    }

    @Override
    public List<Avaliacao> buscarAvaliacaoUsuario(String cpf_usuario) {
        String sql = """
        SELECT  a.ID_AVALIACAO,
                a.COM_AVALIACAO,
                a.NOTA_AVALIACAO,
                a.DT_AVALIACAO,
                a.CPF_AVALIADOR,
                a.CPF_AVALIADO,
                u.NOME_USUARIO AS NOME_AVALIADOR
          FROM  T_GIG_AVALIACAO a
          JOIN  T_GIG_USUARIO u
            ON  u.CPF_USUARIO = a.CPF_AVALIADOR
         WHERE  a.CPF_AVALIADO = ?
         ORDER BY a.DT_AVALIACAO DESC
        """;

        List<Avaliacao> avaliacoes = new ArrayList<>();

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf_usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Avaliacao avaliacao = new Avaliacao();
                    avaliacao.setId_avaliacao(rs.getInt("ID_AVALIACAO"));
                    avaliacao.setCom_avaliacao(rs.getString("COM_AVALIACAO"));
                    avaliacao.setNota(rs.getInt("NOTA_AVALIACAO"));
                    avaliacao.setDt_avaliacao(rs.getString("DT_AVALIACAO"));

                    // Monta objeto Avaliador
                    Usuario avaliador = new Usuario();
                    avaliador.setCpf_usuario(rs.getString("CPF_AVALIADOR"));
                    avaliador.setNome_usuario(rs.getString("NOME_AVALIADOR"));

                    // Monta objeto Avaliado
                    Usuario avaliado = new Usuario();
                    avaliado.setCpf_usuario(rs.getString("CPF_AVALIADO"));

                    avaliacao.setAvaliador(avaliador);
                    avaliacao.setAvaliado(avaliado);

                    avaliacoes.add(avaliacao);
                }
            }

            return avaliacoes;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar avaliações do usuário avaliado: " + cpf_usuario, e);
        }
    }

    @Override
    public void adicionarCompetencias(int idAvaliacao, int idCompetencia) {
        String sql = """
        INSERT INTO T_GIG_AVALIACAO_COMPETENCIA (ID_COMPETENCIA, ID_AVALIACAO)
        VALUES (?, ?)
        """;

        try (Connection conn = this.databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCompetencia);
            stmt.setInt(2, idAvaliacao);

            stmt.executeUpdate();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                System.out.println("Competência já está vinculada à avaliação ");
                return;
            }

            throw new RuntimeException("Erro ao adicionar competência à avaliação", e);
        }
    }
}
