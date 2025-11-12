package br.com.fiap.gig.infrastructure.api.rest;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Avaliacao;
import br.com.fiap.gig.interfaces.AvaliacaoController;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/avaliacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoRestController {

    private final AvaliacaoController avaliacaoController;

    public AvaliacaoRestController(AvaliacaoController avaliacaoController) {
        this.avaliacaoController = avaliacaoController;
    }

    @POST
    public Response criarAvaliacao(Avaliacao avaliacaoInput) {
        try {
            Avaliacao avaliacao = this.avaliacaoController.criarAvaliacao(avaliacaoInput);
            return Response.status(Response.Status.CREATED).entity(avaliacao).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    public Response editarAvaliacao(Avaliacao avaliacaoInput) {
        try {
            this.avaliacaoController.editarAvaliacao(avaliacaoInput);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("buscar/{id}")
    public Response buscarAvaliacao(int id) {
        try {
            Avaliacao avaliacao = this.avaliacaoController.buscarAvaliacao(id);
            return Response.ok(avaliacao).build();
        } catch (RuntimeException | EntidadeNaoLocalizada e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("buscar/usuario/{cpf}")
    public Response buscarPorUsuario(String cpf) {
        try {
            List<Avaliacao> avaliacao = this.avaliacaoController.buscarAvaliacaoUsuario(cpf);
            return Response.ok(avaliacao).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
