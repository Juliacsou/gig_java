package br.com.fiap.gig.infrastructure.config;

import br.com.fiap.gig.application.service.ScoreServiceImpl;
import br.com.fiap.gig.domain.repository.AvaliacaoRepository;
import br.com.fiap.gig.domain.repository.CompetenciaRepository;
import br.com.fiap.gig.domain.repository.ScoreRepository;
import br.com.fiap.gig.domain.service.ScoreService;
import jakarta.enterprise.context.RequestScoped;

public class ScoreServiceConfig {

    private final ScoreRepository scoreRepository;
    private final AvaliacaoRepository avaliacaoRepository;
    private final CompetenciaRepository competenciaRepository;

    public ScoreServiceConfig(ScoreRepository scoreRepository, AvaliacaoRepository avaliacaoRepository, CompetenciaRepository competenciaRepository) {
        this.scoreRepository = scoreRepository;
        this.avaliacaoRepository = avaliacaoRepository;
        this.competenciaRepository = competenciaRepository;
    }

    @RequestScoped
    public ScoreService scoreService(){
        return new ScoreServiceImpl(scoreRepository, avaliacaoRepository, competenciaRepository);
    }
}
