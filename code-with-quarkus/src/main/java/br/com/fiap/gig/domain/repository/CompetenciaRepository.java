package br.com.fiap.gig.domain.repository;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Competencia;
import java.util.List;

public interface CompetenciaRepository {
    Competencia criarCompetencia (Competencia competencia);
    void editarCompetencia (Competencia competencia);
    Competencia buscarCompetencia (int id_competencia) throws EntidadeNaoLocalizada;
    void excluirCompetencia (int id_competencia);
    List<Competencia> buscarCompetenciaUsuario(String cpf_usuario);
}
