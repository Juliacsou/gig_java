package br.com.fiap.gig.infrastructure.config;

import br.com.fiap.gig.domain.service.AvaliacaoService;
import br.com.fiap.gig.domain.service.CompetenciaService;
import br.com.fiap.gig.domain.service.ScoreService;
import br.com.fiap.gig.domain.service.UsuarioService;
import br.com.fiap.gig.interfaces.*;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ControllerConfig {

    @ApplicationScoped
    public AvaliacaoController avaliacaoController(AvaliacaoService funcionarioService) {
        return new AvaliacaoControllerImpl(funcionarioService);
    }

    @ApplicationScoped
    public CompetenciaController competenciaController(CompetenciaService competenciaService) {
        return new CompetenciaControllerImpl(competenciaService);
    }

    @ApplicationScoped
    public ScoreController scoreController(ScoreService scoreService) {
        return new ScoreControllerImpl(scoreService);
    }

    @ApplicationScoped
    public UsuarioController usuarioController(UsuarioService usuarioService) {
        return new UsuarioControllerImpl(usuarioService);
    }

}
