package br.unitins.ecommerce.resource;

import br.unitins.ecommerce.dto.usuario.SignUserForm;
import br.unitins.ecommerce.service.usuario.ClienteServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/sign-in")
public class SignInResource {
    @Inject
    ClienteServiceImpl service;


    private static final Logger LOG = Logger.getLogger(SignInResource.class);

    @POST
    public Response signIn(SignUserForm form) {
        String token = service.signIn(form);
        return Response.ok()
                .header("Authorization", token)
                .build();
    }


}