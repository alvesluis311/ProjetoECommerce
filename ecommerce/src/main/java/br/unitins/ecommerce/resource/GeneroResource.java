package br.unitins.ecommerce.resource;

import java.util.List;

import org.jboss.logging.Logger;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
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
import br.unitins.ecommerce.dto.genero.GeneroDTO;
import br.unitins.ecommerce.dto.genero.GeneroResponseDTO;
import br.unitins.ecommerce.service.genero.GeneroService;
import br.unitins.ecommerce.service.file.FileService;

@Path("/generos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeneroResource {

    @Inject
    GeneroService generoService;

    @Inject
    FileService fileService;

    private static final Logger LOG = Logger.getLogger(GeneroResource.class);

    @GET
    @PermitAll
    public List<GeneroResponseDTO> getAll() {
        LOG.info("Buscando todas os gêneros");
        LOG.debug("ERRO DE DEBUG.");
        return generoService.getAll();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public GeneroResponseDTO getById(@PathParam("id") Long id) throws NotFoundException {
        LOG.infof("Buscando gêneros por ID. ", id);
        LOG.debug("ERRO DE DEBUG.");
        return generoService.getById(id);
    }

    @POST
    @RolesAllowed({"Admin"})
    public Response insert(GeneroDTO generoDto) {
        LOG.infof("Inserindo um gênero: %s", generoDto.nome());
        Result result = null;
        try {
            LOG.infof("Gênero criado.");
            return Response
                    .status(Status.CREATED) // 201
                    .entity(generoService.create(generoDto))
                    .build();

        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao incluir um Gênero.");
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
    @RolesAllowed({"Admin"})
    public Response update(@PathParam("id") Long id, GeneroDTO generoDto) {
        Result result = null;
        try {
            generoService.update(id, generoDto);
            LOG.infof("Gênero (%d) atualizado com sucesso.", id);
            return Response
                    .status(Status.NO_CONTENT) // 204
                    .build();

        } catch (ConstraintViolationException e) {
            LOG.errorf("Erro ao atualizar um genêro. ", id, e);
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
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException {
        try {
            generoService.delete(id);
            LOG.infof("Gênero excluído com sucesso.", id);

            return Response
                    .status(Status.NO_CONTENT)
                    .build();

        } catch (IllegalArgumentException e) {
            LOG.error("Erro ao deletar gênero: parâmetros inválidos.", e);
            throw e;
        }
    }

    @GET
    @Path("/count")
    @RolesAllowed({"Admin"})
    public Long count() {
        LOG.info("Contando todos os gêneros.");
        LOG.debug("ERRO DE DEBUG.");
        return generoService.count();
    }

    @GET
    @Path("/searchByNome/{nome}")
    @PermitAll
    public List<GeneroResponseDTO> getByNome(@PathParam("nome") String nome) {
        LOG.infof("Buscando gênero pelo nome. ", nome);
        LOG.debug("ERRO DE DEBUG.");
        return generoService.findByNome(nome);
    }

}