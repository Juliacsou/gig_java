package br.com.fiap.gig.infrastructure.api.rest;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Usuario;
import br.com.fiap.gig.interfaces.UsuarioController;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioRestController {

    private final UsuarioController usuarioController;

    @Inject
    public UsuarioRestController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    @POST
    public Response criarUsuario(Usuario usuarioInput) {
        try {
            Usuario usuario = this.usuarioController.criarUsuario(usuarioInput);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    public Response editarUsuario(Usuario usuarioInput) {
        try {
            this.usuarioController.editarUsuario(usuarioInput);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/buscar/{cpf}")
    public Response buscarUsuario(@PathParam("cpf") String cpf) {
        try {
            Usuario usuario = this.usuarioController.buscarUsuario(cpf);
            return Response.ok(usuario).build();
        } catch (RuntimeException | EntidadeNaoLocalizada e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("validar/{cpf}/{senha}")
    public Response validarUsuario(@PathParam("cpf") String cpf, @PathParam("senha") String senha) {
        try {
            Usuario usuario = this.usuarioController.validarUsuario(cpf, senha);
            return Response.ok(usuario).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/excluir/{cpf}")
    public Response excluirUsuario(@PathParam("cpf") String cpf) {
        try {
            this.usuarioController.excluirUsuario(cpf);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


}
