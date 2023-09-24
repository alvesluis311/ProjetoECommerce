package br.unitins.ecommerce.resource;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.usuario.ClienteBasicForm;
import br.unitins.ecommerce.model.usuario.Usuario;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.ecommerce.service.usuario.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("/cliente-basico")
public class ClienteBasicResource {

    @Inject
    UsuarioService usuarioService;

    @Inject
    JsonWebToken tokenJwt;

    private static final Logger LOG = Logger.getLogger(ClienteBasicResource.class);

    @POST
    @Path("/sign-in")
    public Response signInUser(ClienteBasicForm form) {
        usuarioService.add(form);

        return Response.status(Status.CREATED).build();
    }

    @POST
    @RolesAllowed({ "User_Basic" })
    @Path("/adicionar-enderecos")
    public Response addAdresses(List<EnderecoForm> forms) {
        String login = tokenJwt.getSubject();

        usuarioService.addAdresses(login, forms);

        return Response.status(Status.CREATED).build();
    }
}