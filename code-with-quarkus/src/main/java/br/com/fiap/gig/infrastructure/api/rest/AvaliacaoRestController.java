package br.com.fiap.gig.infrastructure.api.rest;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Avaliacao;
import br.com.fiap.gig.interfaces.AvaliacaoController;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/avaliacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoRestController {

    private final AvaliacaoController avaliacaoController;

    @Inject
    public AvaliacaoRestController(AvaliacaoController avaliacaoController) {
        this.avaliacaoController = avaliacaoController;
    }

    @POST
    public Response criarAvaliacao(Avaliacao avaliacaoInput) {
        try {
            Avaliacao avaliacao = avaliacaoController.criarAvaliacao(avaliacaoInput);
            return Response.status(Response.Status.CREATED).entity(avaliacao).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Path("adicionar/competencia/{id_avaliacao}/{id_competencia}")
    @POST
    public Response adicionarCompetencia(@PathParam("id_avaliacao") int id_avaliacao, @PathParam("id_competencia") int id_competencia) {
        try {
            this.avaliacaoController.adicionarCompetencias(id_avaliacao, id_competencia);
            return Response.status(Response.Status.CREATED).build();
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
    public Response buscarAvaliacao(@PathParam("id") int id) {
        try {
            Avaliacao avaliacao = this.avaliacaoController.buscarAvaliacao(id);
            return Response.ok(avaliacao).build();
        } catch (RuntimeException | EntidadeNaoLocalizada e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("buscar/usuario/{cpf}")
    public Response buscarPorUsuario(@PathParam("cpf") String cpf) {
        try {
            List<Avaliacao> avaliacao = this.avaliacaoController.buscarAvaliacaoUsuario(cpf);
            return Response.ok(avaliacao).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
