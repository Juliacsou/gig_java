package br.com.fiap.gig.domain.repository;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Avaliacao;
import java.util.List;

public interface AvaliacaoRepository {
    Avaliacao criarAvaliacao (Avaliacao avaliacao);
    void editarAvaliacao (Avaliacao avaliacao);
    Avaliacao buscarAvaliacao (int id_avaliacao) throws EntidadeNaoLocalizada;
    List<Avaliacao> buscarAvaliacaoUsuario (String cpf_usuario);
    void adicionarCompetencias(int idAvaliacao, int idCompetencia);
}
