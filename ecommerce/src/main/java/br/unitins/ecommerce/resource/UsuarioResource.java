package br.unitins.ecommerce.resource;

import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.service.usuario.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    @Inject
    UsuarioService usuarioService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        UsuarioResponse anuncioResponse = usuarioService.findOrFailResponseById(id);
        return Response.ok(anuncioResponse).build();
    }

    @GET
    public Response findUsers() {
        List<UsuarioResponse> listaUsuario = usuarioService.findAllUsers();

        return Response.status(listaUsuario.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaUsuario)
                .build();
    }

    @POST
    public Response add(UsuarioForm form) {
        usuarioService.add(form);
        return Response.status(Response.Status.CREATED).build();
    }

    @PATCH
    @Path("/{id}/adicionar-perfis")
    public Response addProfiles(@PathParam("id") Long id, List<Integer> perfil) {
         usuarioService.addProfiles(id, perfil);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UsuarioRequest request) {
        UsuarioResponse response = usuarioService.update(id, request);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @PATCH
    @Path("/{id}")
    public Response merge(@PathParam("id") Long id, UsuarioPatch request) {
        UsuarioResponse response = usuarioService.merge(id, request);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        usuarioService.delete(id);
        return Response.ok().build();
    }


}
