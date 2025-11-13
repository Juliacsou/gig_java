package br.com.fiap.gig.application.service;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Competencia;
import br.com.fiap.gig.domain.repository.CompetenciaRepository;
import br.com.fiap.gig.domain.service.CompetenciaService;

import java.util.List;

public class CompetenciaServiceImpl implements CompetenciaService {

    private final CompetenciaRepository competenciaRepository;

    public CompetenciaServiceImpl(CompetenciaRepository competenciaRepository) {
        this.competenciaRepository = competenciaRepository;
    }

    @Override
    public Competencia criarCompetencia(Competencia competencia) {
        return competenciaRepository.criarCompetencia(competencia);
    }

    @Override
    public void editarCompetencia(Competencia competencia) {
        try{
            Competencia competenciaExistente = competenciaRepository.buscarCompetencia(competencia.getId_competencia());
            competenciaRepository.editarCompetencia(competencia);
        } catch (EntidadeNaoLocalizada e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Competencia buscarCompetencia(int id_competencia) throws EntidadeNaoLocalizada {
        try{
            return competenciaRepository.buscarCompetencia(id_competencia);
        } catch (EntidadeNaoLocalizada e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void excluirCompetencia(int id_competencia) {
        try{
            Competencia competenciaExistente = competenciaRepository.buscarCompetencia(id_competencia);
            competenciaRepository.excluirCompetencia(id_competencia);
        } catch (EntidadeNaoLocalizada e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Competencia> buscarCompetenciaUsuario(String cpf_usuario) {
        return competenciaRepository.buscarCompetenciaUsuario(cpf_usuario);
    }
}
