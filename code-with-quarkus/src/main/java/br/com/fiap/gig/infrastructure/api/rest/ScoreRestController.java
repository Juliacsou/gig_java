package br.com.fiap.gig.infrastructure.api.rest;

import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Score;
import br.com.fiap.gig.interfaces.ScoreController;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/score")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScoreRestController {

    private final ScoreController scoreController;

    @Inject
    public ScoreRestController(ScoreController scoreController) {
        this.scoreController = scoreController;
    }

    @POST
    public Response criarScore(Score scoreInput) {
        try {
            Score score = this.scoreController.criarScore(scoreInput);
            return Response.status(Response.Status.CREATED).entity(score).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("buscar/{cpf}")
    public Response buscarScore(@PathParam("cpf") String cpf) {
        try {
            Score score = this.scoreController.buscarScore(cpf);
            return Response.ok(score).build();
        } catch (RuntimeException | EntidadeNaoLocalizada e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
