package br.com.fiap.gig.application.service;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Avaliacao;
import br.com.fiap.gig.domain.repository.AvaliacaoRepository;
import br.com.fiap.gig.domain.service.AvaliacaoService;

import java.util.List;

public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoServiceImpl(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Override
    public Avaliacao criarAvaliacao(Avaliacao avaliacao) {
        return avaliacaoRepository.criarAvaliacao(avaliacao);
    }

    @Override
    public void editarAvaliacao(Avaliacao avaliacao) {
        try {
            Avaliacao avaliacaoExistente = avaliacaoRepository.buscarAvaliacao(avaliacao.getId_avaliacao());
            avaliacaoRepository.editarAvaliacao(avaliacao);
        } catch (EntidadeNaoLocalizada e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Avaliacao buscarAvaliacao(int id_avaliacao) throws EntidadeNaoLocalizada {
        return avaliacaoRepository.buscarAvaliacao(id_avaliacao);
    }

    @Override
    public List<Avaliacao> buscarAvaliacaoUsuario(String cpf_usuario) {
        return avaliacaoRepository.buscarAvaliacaoUsuario(cpf_usuario);
    }
}
