package br.com.fiap.gig.domain.service;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Usuario;

public interface UsuarioService {
    Usuario criarUsuario(Usuario usuario);
    void editarUsuario(Usuario usuario);
    Usuario buscarUsuario(String cpf) throws EntidadeNaoLocalizada;
    Usuario validarUsuario(String cpf, String senha);
    void excluirUsuario(String cpf);
}
