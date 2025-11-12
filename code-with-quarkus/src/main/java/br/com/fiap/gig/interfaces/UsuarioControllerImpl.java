package br.com.fiap.gig.interfaces;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Usuario;
import br.com.fiap.gig.domain.service.UsuarioService;

public class UsuarioControllerImpl implements UsuarioController{

    private final UsuarioService usuarioService;

    public UsuarioControllerImpl(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public Usuario criarUsuario(Usuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    @Override
    public void editarUsuario(Usuario usuario) {
        usuarioService.editarUsuario(usuario);
    }

    @Override
    public Usuario buscarUsuario(String cpf) throws EntidadeNaoLocalizada {
        return usuarioService.buscarUsuario(cpf);
    }

    @Override
    public Usuario validarUsuario(String cpf, String senha) {
        return usuarioService.validarUsuario(cpf, senha);
    }

    @Override
    public void excluirUsuario(String cpf) {
        usuarioService.excluirUsuario(cpf);
    }
}
