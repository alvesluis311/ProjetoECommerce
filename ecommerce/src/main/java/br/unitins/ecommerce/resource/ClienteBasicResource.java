package br.unitins.ecommerce.resource;

import br.unitins.ecommerce.dto.usuario.ClienteBasicForm;
import br.unitins.ecommerce.service.usuario.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.logging.Logger;

@Path("/cliente-basico")
public class ClienteBasicResource {

    @Inject
    UsuarioService usuarioService;


    private static final Logger LOG = Logger.getLogger(ClienteBasicResource.class);

    @POST
    @Path("/sign-in")
    public Response signInUser(ClienteBasicForm form) {
        usuarioService.add(form);

        return Response.status(Status.CREATED).build();
    }


}