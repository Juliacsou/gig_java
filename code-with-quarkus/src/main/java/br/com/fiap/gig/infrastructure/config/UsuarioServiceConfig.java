package br.com.fiap.gig.infrastructure.config;

import br.com.fiap.gig.application.service.UsuarioServiceImpl;
import br.com.fiap.gig.domain.repository.UsuarioRepository;
import br.com.fiap.gig.domain.service.UsuarioService;
import jakarta.enterprise.context.RequestScoped;

public class UsuarioServiceConfig {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @RequestScoped
    public UsuarioService usuarioService(){
        return new UsuarioServiceImpl(usuarioRepository);
    }
}
