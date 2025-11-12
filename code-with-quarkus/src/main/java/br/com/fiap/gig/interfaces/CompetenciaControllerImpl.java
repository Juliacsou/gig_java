package br.com.fiap.gig.interfaces;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Competencia;
import br.com.fiap.gig.domain.service.CompetenciaService;

import java.util.List;

public class CompetenciaControllerImpl implements CompetenciaController{

    private final CompetenciaService competenciaService;

    public CompetenciaControllerImpl(CompetenciaService competenciaService) {
        this.competenciaService = competenciaService;
    }

    @Override
    public Competencia criarCompetencia(Competencia competencia) {
        return competenciaService.criarCompetencia(competencia);
    }

    @Override
    public void editarCompetencia(Competencia competencia) {
        competenciaService.editarCompetencia(competencia);
    }

    @Override
    public Competencia buscarCompetencia(int id_competencia) throws EntidadeNaoLocalizada {
        return competenciaService.buscarCompetencia(id_competencia);
    }

    @Override
    public void excluirCompetencia(int id_competencia) {
        competenciaService.excluirCompetencia(id_competencia);
    }

    @Override
    public List<Competencia> BuscarCompetenciaUsuario(String cpf_usuario) {
        return competenciaService.BuscarCompetenciaUsuario(cpf_usuario);
    }
}
