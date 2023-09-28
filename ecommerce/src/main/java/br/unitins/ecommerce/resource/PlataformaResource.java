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
import br.unitins.ecommerce.dto.plataforma.PlataformaDTO;
import br.unitins.ecommerce.dto.plataforma.PlataformaResponseDTO;
import br.unitins.ecommerce.service.plataforma.PlataformaService;

@Path("/plataformas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlataformaResource {

    @Inject
    PlataformaService plataformaService;

    private static final Logger LOG = Logger.getLogger(PlataformaResource.class);

    @GET
    @PermitAll
    public List<PlataformaResponseDTO> getAll() {
        LOG.info("Buscando todas os plataformas");
        LOG.debug("ERRO DE DEBUG.");
        return plataformaService.getAll();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public PlataformaResponseDTO getById(@PathParam("id") Long id) throws NotFoundException {
        LOG.infof("Buscando plataformas por ID. ", id);
        LOG.debug("ERRO DE DEBUG.");
        return plataformaService.getById(id);
    }

    @POST
    @PermitAll
    // @RolesAllowed({"Admin"})
    public Response insert(PlataformaDTO plataformaDto) {
        LOG.infof("Inserindo uma plataforma: %s", plataformaDto.nome());
        Result result = null;
        try {
            LOG.infof("Plataforma criada.");
            return Response
                    .status(Status.CREATED) // 201
                    .entity(plataformaService.create(plataformaDto))
                    .build();

        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao incluir um Plataforma.");
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
    public Response update(@PathParam("id") Long id, PlataformaDTO plataformaDto) {
        Result result = null;
        try {
            plataformaService.update(id, plataformaDto);
            LOG.infof("Plataforma (%d) atualizada com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT) // 204
                    .build();

        } catch (ConstraintViolationException e) {
            LOG.errorf("Erro ao atualizar uma plataforma. ", id, e);
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
            plataformaService.delete(id);
            LOG.infof("plataforma excluída com sucesso.", id);

            return Response
                    .status(Status.NO_CONTENT)
                    .build();

        } catch (IllegalArgumentException e) {
            LOG.error("Erro ao deletar plataforma: parâmetros inválidos.", e);
            throw e;
        }
    }

    @GET
    @Path("/count")
    @PermitAll
    // @RolesAllowed({"Admin"})
    public Long count() {
        LOG.info("Contando todos os plataformas.");
        LOG.debug("ERRO DE DEBUG.");
        return plataformaService.count();
    }

    @GET
    @Path("/searchByNome/{nome}")
    @PermitAll
    public List<PlataformaResponseDTO> getByNome(@PathParam("nome") String nome) {
        LOG.infof("Buscando plataforma pelo nome. ", nome);
        LOG.debug("ERRO DE DEBUG.");
        return plataformaService.findByNome(nome);
    }

}