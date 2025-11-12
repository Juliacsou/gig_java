package br.com.fiap.gig.interfaces;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Avaliacao;
import br.com.fiap.gig.domain.service.AvaliacaoService;
import br.com.fiap.gig.domain.service.CompetenciaService;

import java.util.List;

public class AvaliacaoControllerImpl implements AvaliacaoController{

    private final AvaliacaoService avaliacaoService;
    private final CompetenciaService competenciaService;

    public AvaliacaoControllerImpl(AvaliacaoService avaliacaoService, CompetenciaService competenciaService) {
        this.avaliacaoService = avaliacaoService;
        this.competenciaService = competenciaService;
    }

    @Override
    public Avaliacao criarAvaliacao(Avaliacao avaliacao) {
        return avaliacaoService.criarAvaliacao(avaliacao);
    }

    @Override
    public void editarAvaliacao(Avaliacao avaliacao) {
        avaliacaoService.editarAvaliacao(avaliacao);
    }

    @Override
    public Avaliacao buscarAvaliacao(int id_avaliacao) throws EntidadeNaoLocalizada {
        return avaliacaoService.buscarAvaliacao(id_avaliacao);
    }

    @Override
    public List<Avaliacao> buscarAvaliacaoUsuario(String cpf_usuario) {
        return avaliacaoService.buscarAvaliacaoUsuario(cpf_usuario);
    }

    @Override
    public void adicionarCompetencias(int idAvaliacao, int idCompetencia) {
        avaliacaoService.adicionarCompetencias(idAvaliacao, idCompetencia);
    }
}
