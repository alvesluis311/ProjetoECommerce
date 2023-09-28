package br.unitins.ecommerce.resource;

import java.util.List;

import org.jboss.logging.Logger;

import jakarta.annotation.security.PermitAll;
//import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.fabricante.FabricanteDTO;
import br.unitins.ecommerce.dto.fabricante.FabricanteResponseDTO;
import br.unitins.ecommerce.service.fabricante.FabricanteService;

@Path("/fabricantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FabricanteResource {

    @Inject
    FabricanteService fabricanteService;


    private static final Logger LOG = Logger.getLogger(FabricanteResource.class);

    @GET
    @PermitAll
    public List<FabricanteResponseDTO> getAll() {
        LOG.info("Buscando todas os fabricantes");
        LOG.debug("ERRO DE DEBUG.");
        return fabricanteService.getAll();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public FabricanteResponseDTO getById(@PathParam("id") Long id) throws NotFoundException {
        LOG.infof("Buscando fabricantes por ID. ", id);
        LOG.debug("ERRO DE DEBUG.");
        return fabricanteService.getById(id);
    }

    @POST
    @PermitAll
    //@RolesAllowed({"Admin"})
    public Response insert(FabricanteDTO fabricanteDto) {
        LOG.infof("Inserindo um gênero: %s", fabricanteDto.nome());
        Result result = null;
        try {
            LOG.infof("Fabricante criado.");
            return Response
                    .status(Status.CREATED) // 201
                    .entity(fabricanteService.create(fabricanteDto))
                    .build();

        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao incluir um Fabricante.");
            LOG.debug(e.getMessage());
            result = new Result(e.getConstraintViolations());

        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), false);

        }
        return Response
        .status(Status.NOT_FOUND)
        .entity(result)
        .build();
    }

    @PUT
    @Path("/{id}")
    @PermitAll
    //@RolesAllowed({"Admin"})
    public Response update(@PathParam("id") Long id, FabricanteDTO fabricanteDto) {
        Result result = null;
        try {
            fabricanteService.update(id, fabricanteDto);
            LOG.infof("Fabricante (%d) atualizado com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT) // 204
                    .build();

        } catch (ConstraintViolationException e) {
            LOG.errorf("Erro ao atualizar um fabricante. ", id, e);
            LOG.debug(e.getMessage());

            result = new Result(e.getConstraintViolations());

        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), false);

        }
        return Response
                .status(Status.NOT_FOUND)
                .entity(result)
                .build();
    }


    @DELETE
    @Path("/{id}")
    @PermitAll
    //@RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException {
        try {
            fabricanteService.delete(id);
            LOG.infof("fabricante excluído com sucesso.", id);

            return Response
                    .status(Status.NO_CONTENT)
                    .build();

        } catch (IllegalArgumentException e) {
            LOG.error("Erro ao deletar fabricante: parâmetros inválidos.", e);
            throw e;
        }
    }

    @GET
    @Path("/count")
    @PermitAll
    //@RolesAllowed({"Admin"})
    public Long count() {
        LOG.info("Contando todos os fabricantes.");
        LOG.debug("ERRO DE DEBUG.");
        return fabricanteService.count();
    }

    @GET
    @Path("/searchByNome/{nome}")
    @PermitAll
    public List<FabricanteResponseDTO> getByNome(@PathParam("nome") String nome) {
        LOG.infof("Buscando fabricante pelo nome. ", nome);
        LOG.debug("ERRO DE DEBUG.");
        return fabricanteService.findByNome(nome);
    }

}