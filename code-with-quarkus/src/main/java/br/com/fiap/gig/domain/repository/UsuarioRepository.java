package br.com.fiap.gig.domain.repository;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Usuario;

public interface UsuarioRepository {
    Usuario criarUsuario(Usuario usuario);
    void editarUsuario(Usuario usuario);
    Usuario buscarUsuario(String cpf) throws EntidadeNaoLocalizada;
    Usuario validarUsuario(String cpf, String senha);
    void excluirUsuario(String cpf);
}
