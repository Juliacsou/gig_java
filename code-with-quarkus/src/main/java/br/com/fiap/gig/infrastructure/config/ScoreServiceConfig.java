package br.com.fiap.gig.infrastructure.config;

import br.com.fiap.gig.application.service.ScoreServiceImpl;
import br.com.fiap.gig.domain.repository.ScoreRepository;
import br.com.fiap.gig.domain.service.ScoreService;
import jakarta.enterprise.context.RequestScoped;

public class ScoreServiceConfig {

    private final ScoreRepository scoreRepository;

    public ScoreServiceConfig(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @RequestScoped
    public ScoreService scoreService(){
        return new ScoreServiceImpl(scoreRepository);
    }
}
