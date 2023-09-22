package br.unitins.ecommerce.resource;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.endereco.EnderecoResponse;
import br.unitins.ecommerce.dto.usuario.SenhaDTO;
import br.unitins.ecommerce.dto.usuario.TelefoneResponse;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.service.endereco.EnderecoService;
import br.unitins.ecommerce.service.file.FileService;
import br.unitins.ecommerce.service.usuario.TelefoneService;
import br.unitins.ecommerce.service.usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/perfil")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResource {

    @Inject
    UsuarioService usuarioService;

    @Inject
    TelefoneService telefoneService;

    @Inject
    EnderecoService enderecoService;

    @Inject
    JsonWebToken tokenJwt;

    @Inject
    FileService fileService;

    private static final Logger LOG = Logger.getLogger(UsuarioLogadoResource.class);

    @GET
    @Path("/dados-pessoais")
    @RolesAllowed({"User", "User_Basic"})
    public Response getDadosPessoais() {

        String login = tokenJwt.getSubject();

        return Response.ok(usuarioService.buscarDadosPessoais(login)).build();
    }

    @GET
    @Path("/telefones")
    @RolesAllowed({"User"})
    public Response getTelefones() {
        String login = tokenJwt.getSubject();

        List<TelefoneResponse> listaTelefone = telefoneService.buscarListaTelefoneResponse(login);

        return Response.status(listaTelefone.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaTelefone)
                .build();
    }

    @GET
    @Path("/endereco")
    @RolesAllowed({"User"})
    public Response getEndereco() {
        String login = tokenJwt.getSubject();

        List<EnderecoResponse> listaEndereco = enderecoService.buscarListaEnderecoResponse(login);

        return Response.status(listaEndereco.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaEndereco)
                .build();

    }

    @GET
    @Path("/download/{nomeImagem}")
    @RolesAllowed({"Admin", "User"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {

        try {
            ResponseBuilder response = Response.ok(fileService.download(nomeImagem));

            response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
            LOG.infof("Download do arquivo %s concluído com sucesso.", nomeImagem);

            return response
                    .build();

        } catch (Exception e) {
            LOG.errorf("Erro ao realizar o download do arquivo: %s", nomeImagem, e);

            return Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }


//    @PATCH
//    @Path("/dados-pessoais")
//    @RolesAllowed({"User"})
//    public Response updateDadosPessoais(DadosPessoaisDTO dadosPessoaisDTO) {
//
//        try {
//            String login = tokenJwt.getSubject();
//
//            Usuario usuario = usuarioService.buscarPorLogin(login);
//
//            usuarioService.atualizar(usuario.getId(), dadosPessoaisDTO);
//            LOG.info("Dados pessoais atualizados com sucesso.");
//
//            return Response.status(Status.NO_CONTENT).build();
//
//        } catch (Exception e) {
//            LOG.error("Erro ao atualizar dados pessoais do usuário.", e);
//
//            return Response
//                    .status(Status.INTERNAL_SERVER_ERROR)
//                    .build();
//        }
//    }

    @PATCH
    @Path("/senha")
    @RolesAllowed({"User"})
    public Response updateSenha(SenhaDTO senhaDTO) {
        String login = tokenJwt.getSubject();
        usuarioService.alterarSenha(login, senhaDTO);

        return Response.status(Status.NO_CONTENT).build();

    }

    @PUT
    @Path("/endereco/{id}")
    @RolesAllowed({"User"})
    public Response updateEndereco(@PathParam("id") Long id, EnderecoForm form) {
        String login = tokenJwt.getSubject();

        Usuario usuario = usuarioService.buscarPorLogin(login);

        enderecoService.atualizar(usuario.getId(), id, form);

        LOG.info("Endereço atualizado com sucesso.");

        return Response.status(Status.NO_CONTENT).build();
    }

//    @PATCH
//    @Path("/atualizar-imagem")
//    @RolesAllowed({"Admin", "User"})
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response salvarImagem(@MultipartForm ImageForm form) {
//
//        String nomeImagem = "";
//
//        try {
//            nomeImagem = fileService.salvarImagemUsuario(form.getImagem(), form.getNomeImagem());
//            LOG.infof("Imagem salva com sucesso: %s", nomeImagem);
//        } catch (IOException e) {
//            LOG.error("Erro ao salvar a imagem do produto.", e);
//            Result result = new Result(e.getMessage(), false);
//
//            return Response
//                    .status(Status.CONFLICT)
//                    .entity(result)
//                    .build();
//        }
//
//        // obtendo o login a partir do token
//        String login = tokenJwt.getSubject();
//
//        Usuario usuario = usuarioService.buscarPorLogin(login);
//
//        usuarioService.update(usuario.getId(), nomeImagem);
//
//        return Response
//                .status(Status.NO_CONTENT)
//                .build();
//    }
}