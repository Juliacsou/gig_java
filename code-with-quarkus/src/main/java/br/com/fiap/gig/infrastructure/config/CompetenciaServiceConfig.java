package br.com.fiap.gig.infrastructure.config;

import br.com.fiap.gig.application.service.CompetenciaServiceImpl;
import br.com.fiap.gig.domain.repository.CompetenciaRepository;
import br.com.fiap.gig.domain.service.CompetenciaService;
import jakarta.enterprise.context.RequestScoped;

public class CompetenciaServiceConfig {

    private final CompetenciaRepository competenciaRepository;

    public CompetenciaServiceConfig(CompetenciaRepository competenciaRepository) {
        this.competenciaRepository = competenciaRepository;
    }

    @RequestScoped
    public CompetenciaService competenciaService(){
        return new CompetenciaServiceImpl(competenciaRepository);
    }
}
