package br.com.fiap.gig.infrastructure.config;

import br.com.fiap.gig.application.service.AvaliacaoServiceImpl;
import br.com.fiap.gig.domain.repository.AvaliacaoRepository;
import br.com.fiap.gig.domain.service.AvaliacaoService;
import jakarta.enterprise.context.RequestScoped;

public class AvaliacaoServiceConfig {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoServiceConfig(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @RequestScoped
    public AvaliacaoService avaliacaoService(){
        return new AvaliacaoServiceImpl(avaliacaoRepository);
    }
}
