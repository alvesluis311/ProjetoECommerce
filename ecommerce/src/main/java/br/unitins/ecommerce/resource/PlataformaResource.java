package br.unitins.ecommerce.resource;

import br.unitins.ecommerce.dto.plataforma.PlataformaForm;
import br.unitins.ecommerce.dto.plataforma.PlataformaResponse;
import br.unitins.ecommerce.service.plataforma.PlataformaService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/plataformas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlataformaResource {

    @Inject
    PlataformaService plataformaService;

    private static final Logger LOG = Logger.getLogger(PlataformaResource.class);

    @GET
    @PermitAll
    public Response getAll() {
        List<PlataformaResponse> responseList = plataformaService.getAll();
        return Response.status(responseList.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(responseList)
                .build();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response getById(@PathParam("id") Long id) throws NotFoundException {
        PlataformaResponse response = plataformaService.finResponseById(id);

        return Response.ok(response).build();
    }

    @POST
    @PermitAll
    // @RolesAllowed({"Admin"})
    public Response insert(PlataformaForm form) {
        plataformaService.create(form);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @PermitAll
    // @RolesAllowed({"Admin"})
    public Response update(@PathParam("id") Long id, PlataformaForm form) {
        plataformaService.update(id, form);

        return Response.ok().build();
    }


    @DELETE
    @Path("/{id}")
    @PermitAll
    //@RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException {
        plataformaService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
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
    @Path("/search/{nome}")
    @PermitAll
    public Response getByNome(@PathParam("nome") String nome) {
        List<PlataformaResponse> responseList = plataformaService.findByNome(nome);

        return Response.status(responseList.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(responseList)
                .build();
    }

}