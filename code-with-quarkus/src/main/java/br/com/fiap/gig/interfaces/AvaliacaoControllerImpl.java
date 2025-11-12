package br.com.fiap.gig.interfaces;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Avaliacao;
import br.com.fiap.gig.domain.service.AvaliacaoService;

import java.util.List;

public class AvaliacaoControllerImpl implements AvaliacaoController{

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoControllerImpl(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
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
}
