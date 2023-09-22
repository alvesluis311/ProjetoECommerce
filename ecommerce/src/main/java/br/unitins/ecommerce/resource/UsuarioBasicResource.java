package br.unitins.ecommerce.resource;

import br.unitins.ecommerce.dto.usuario.UsuarioResponse;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.usuario.UpgradeUsuarioDTO;
import br.unitins.ecommerce.dto.usuario.UsuarioBasicoForm;
import br.unitins.ecommerce.dto.usuario.UsuarioBasicoResponseDTO;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.service.usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuario-basico")
public class UsuarioBasicResource {

    @Inject
    UsuarioService usuarioService;

    @Inject
    JsonWebToken tokenJwt;

    private static final Logger LOG = Logger.getLogger(UsuarioBasicResource.class);

    @POST
    public Response cadastrarUsuario(UsuarioBasicoForm form) {
        UsuarioResponse response = usuarioService.cadastrar(form);

        return Response.status(Status.OK).entity(response).build();
    }

//    @GET
//    @RolesAllowed({ "User_Basic" })
//    public Response getDadosPessoais() {
//
//        String login = tokenJwt.getSubject();
//
//        UsuarioBasicoResponseDTO usuarioBasico = new UsuarioBasicoResponseDTO(usuarioService.buscarPorLogin(login));
//        LOG.infof("Buscando o dados pessoais do usuário: ", login);
//        LOG.debug("ERRO DE DEBUG.");
//
//        return Response.ok(usuarioBasico).build();
//    }

//    @PATCH
//    @RolesAllowed({ "User_Basic" })
//    public Response upgradeUsuario(UpgradeUsuarioDTO usuarioDTO) {
//        String login = tokenJwt.getSubject();
//        LOG.info("Usuário " + login + " solicitando upgrade de conta.");
//
//        Usuario usuario = usuarioService.buscarPorLogin(login);
//        try {
//             LOG.info("Usuário atualizado com sucesso.");
//            return Response.status(Status.CREATED)
//                    .entity(usuarioService.upgrade(usuario.getId(), usuarioDTO))
//                    .build();
//        } catch (Exception e) {
//            LOG.error("Erro ao fazer o upgrade de conta para o usuário " + login, e);
//            return Response.status(Status.INTERNAL_SERVER_ERROR)
//                    .entity("Erro ao fazer o upgrade de conta.")
//                    .build();
//        }
//    }
}