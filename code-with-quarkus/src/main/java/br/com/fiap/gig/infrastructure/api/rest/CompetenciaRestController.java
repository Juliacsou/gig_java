package br.com.fiap.gig.infrastructure.api.rest;


import br.com.fiap.gig.domain.exception.EntidadeNaoLocalizada;
import br.com.fiap.gig.domain.model.Competencia;
import br.com.fiap.gig.interfaces.CompetenciaController;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/competencia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompetenciaRestController {

    private final CompetenciaController competenciaController;

    @Inject
    public CompetenciaRestController(CompetenciaController competenciaController) {
        this.competenciaController = competenciaController;
    }


    @POST
    public Response criarCompetencia(Competencia competenciaInput) {
        try {
            Competencia competencia = this.competenciaController.criarCompetencia(competenciaInput);
            return Response.status(Response.Status.CREATED).entity(competencia).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    public Response editarCompetencia(Competencia competenciaInput) {
        try {
            this.competenciaController.editarCompetencia(competenciaInput);
            return Response.status(Response.Status.ACCEPTED).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("buscar/{id}")
    public Response buscarCometencia(@PathParam("id") int id) {
        try {
            Competencia competencia = this.competenciaController.buscarCompetencia(id);
            return Response.ok(competencia).build();
        } catch (RuntimeException | EntidadeNaoLocalizada e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("buscar/usuario/{cpf}")
    public Response buscarPorUsuario(@PathParam("cpf") String cpf) {
        try {
            List<Competencia> compatencias = this.competenciaController.buscarCompetenciaUsuario(cpf);
            return Response.ok(compatencias).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/excluir/{id}")
    public Response excluirCompetecia(@PathParam("id") int id) {
        try {
            this.competenciaController.excluirCompetencia(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


}
