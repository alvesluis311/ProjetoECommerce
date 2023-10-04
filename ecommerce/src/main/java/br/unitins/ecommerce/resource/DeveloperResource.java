package br.unitins.ecommerce.resource;

import java.util.List;

import org.jboss.logging.Logger;

import jakarta.annotation.security.PermitAll;
//import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.developer.DeveloperDTO;
import br.unitins.ecommerce.dto.developer.DeveloperResponseDTO;
import br.unitins.ecommerce.service.developer.DeveloperService;

@Path("/developers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeveloperResource {

    @Inject
    DeveloperService developerService;

    private static final Logger LOG = Logger.getLogger(DeveloperResource.class);

    @GET
    @PermitAll
    public List<DeveloperResponseDTO> getAll(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        LOG.info("Buscando todos os developers");
        LOG.debug("ERRO DE DEBUG.");
        return developerService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public DeveloperResponseDTO getById(@PathParam("id") Long id) throws NotFoundException {
        LOG.infof("Buscando developers por ID. ", id);
        LOG.debug("ERRO DE DEBUG.");
        return developerService.getById(id);
    }

    @POST
    @PermitAll
    // @RolesAllowed({"Admin"})
    public Response insert(DeveloperDTO developerDto) {
        LOG.infof("Inserindo um developer: %s", developerDto.nome());
        Result result = null;
        try {
            LOG.infof("Developer criado.");
            return Response
                    .status(Status.CREATED) // 201
                    .entity(developerService.create(developerDto))
                    .build();

        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao incluir um Developer.");
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
    // @RolesAllowed({"Admin"})
    public Response update(@PathParam("id") Long id, DeveloperDTO developerDto) {
        Result result = null;
        try {
            developerService.update(id, developerDto);
            LOG.infof("Developer (%d) atualizado com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT) // 204
                    .build();

        } catch (ConstraintViolationException e) {
            LOG.errorf("Erro ao atualizar um developer. ", id, e);
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
    // @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException {
        try {
            developerService.delete(id);
            LOG.infof("developer excluído com sucesso.", id);

            return Response
                    .status(Status.NO_CONTENT)
                    .build();

        } catch (IllegalArgumentException e) {
            LOG.error("Erro ao deletar developer: parâmetros inválidos.", e);
            throw e;
        }
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome){
         LOG.infof("Contando todos os developers por nome");
        LOG.debug("ERRO DE DEBUG.");
        return developerService.countByNome(nome);
    }

    @GET
    @Path("/count")
    @PermitAll
    // @RolesAllowed({"Admin"})
    public Long count() {
        LOG.info("Contando todos os developers.");
        LOG.debug("ERRO DE DEBUG.");
        return developerService.count();
    }

    @GET
    @Path("/searchByNome/{nome}")
    @PermitAll
    public List<DeveloperResponseDTO> getByNome(
        @PathParam("nome") String nome,
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        LOG.infof("Buscando developer pelo nome. ", nome);
        LOG.debug("ERRO DE DEBUG.");
        return developerService.findByNome(nome, page, pageSize);
    }

}