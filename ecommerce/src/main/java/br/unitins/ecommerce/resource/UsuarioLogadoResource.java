package br.unitins.ecommerce.resource;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.endereco.EnderecoResponse;
import br.unitins.ecommerce.dto.usuario.SenhaDTO;
import br.unitins.ecommerce.dto.usuario.TelefoneForm;
import br.unitins.ecommerce.dto.usuario.TelefoneResponse;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosClienteForm;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosUsuarioForm;
import br.unitins.ecommerce.model.usuario.Cliente;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.service.file.FileService;
import br.unitins.ecommerce.service.usuario.ClienteService;
import br.unitins.ecommerce.service.usuario.UsuarioService;
import jakarta.annotation.security.PermitAll;
//import jakarta.annotation.security.RolesAllowed;
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
    ClienteService clienteService;

    @Inject
    JsonWebToken tokenJwt;

    @Inject
    FileService fileService;

    private static final Logger LOG = Logger.getLogger(UsuarioLogadoResource.class);

    @GET
    @PermitAll
    //@RolesAllowed({"User", "User_Basic", "Admin"})
    public Response getPersonalData() {

        String login = tokenJwt.getSubject();

        var dadosPessoais = usuarioService.findPersonalData(login);

        return Response.ok(dadosPessoais).build();
    }


    @PATCH
    @Path("/dados-pessoais")
    @PermitAll
    //@RolesAllowed({"User", "User_Basic"})
    public Response updatePersonalDataUser(DadosClienteForm form) {

        String login = tokenJwt.getSubject();

        Cliente cliente = clienteService.findByLogin(login);

        usuarioService.merge(cliente.getId(), form);

        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/dados-pessoais-admin")
    @PermitAll
    //@RolesAllowed({"Admin"})
    public Response updatePersonalDataAdmin(DadosUsuarioForm form) {

        String login = tokenJwt.getSubject();

        Usuario usuario = usuarioService.findByLogin(login);

        usuarioService.merge(usuario.getId(), form);

        return Response.status(Status.NO_CONTENT).build();
    }


    @GET
    @Path("/enderecos/{id}")
    @PermitAll
    //@RolesAllowed({"User"})
    public Response getEndereco(@PathParam("id") Long id) {
        String login = tokenJwt.getSubject();

        Cliente cliente = clienteService.findByLogin(login);

        EnderecoResponse response = clienteService.findEndereco(cliente.getId(), id);

        return Response.status(Status.OK).entity(response).build();
    }


    @GET
    @Path("/enderecos")
    @PermitAll
    // @RolesAllowed({"User"})
    public Response getAllEnderecos() {
        String login = tokenJwt.getSubject();

        Cliente cliente = clienteService.findByLogin(login);

        List<EnderecoResponse> listaEndereco = clienteService.findAllEnderecos(cliente.getId());

        return Response.status(listaEndereco.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaEndereco)
                .build();

    }

    @POST
    @Path("/enderecos")
    @PermitAll
    //@RolesAllowed({"User_Basic", "User"})
    public Response addEndereco(EnderecoForm form) {
        String login = tokenJwt.getSubject();

        Cliente cliente = clienteService.findByLogin(login);

        clienteService.insertEndereco(cliente.getId(), form);

        return Response.status(Status.CREATED).build();
    }


    @PUT
    @Path("/enderecos/{id}")
    @PermitAll
    // @RolesAllowed({"User"})
    public Response updateEndereco(@PathParam("id") Long id, EnderecoForm form) {
        String login = tokenJwt.getSubject();

        Cliente cliente = clienteService.findByLogin(login);

        clienteService.updateEndereco(cliente.getId(), id, form);

        return Response.status(Status.OK).build();
    }

    @DELETE
    @Path("/enderecos/{id}")
    @PermitAll
    // @RolesAllowed({"User"})
    public Response deleteEndereco(@PathParam("id") Long id) {
        String login = tokenJwt.getSubject();

        Cliente cliente = clienteService.findByLogin(login);

        clienteService.deleteEndereco(cliente.getId(), id);

        return Response.status(Status.NO_CONTENT).build();
    }


    @GET
    @Path("/telefones")
    @PermitAll
    // @RolesAllowed({"User_Basic", "User"})
    public Response getTelefones() {
        String login = tokenJwt.getSubject();
        Cliente cliente = clienteService.findByLogin(login);

        List<TelefoneResponse> listaTelefone = clienteService.findAllTelefones(cliente.getId());

        return Response.status(listaTelefone.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaTelefone)
                .build();
    }


    @GET
    @Path("/telefones/{id}/")
    @PermitAll
    //@RolesAllowed({"User_Basic", "User"})
    public Response getTelefone(@PathParam("id") Long id) {
        String login = tokenJwt.getSubject();
        Cliente cliente = clienteService.findByLogin(login);

        TelefoneResponse telefoneResponse = clienteService.findTelefone(cliente.getId(), id);
        return Response.ok(telefoneResponse).build();
    }

    @POST
    @Path("/telefones")
    @PermitAll
    //@RolesAllowed({"User_Basic", "User"})
    public Response addTelefone(TelefoneForm form) {
        String login = tokenJwt.getSubject();
        Cliente cliente = clienteService.findByLogin(login);

        clienteService.insertTelefone(cliente.getId(), form);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/telefones/{id}")
    @PermitAll
    //@RolesAllowed({"User_Basic", "User"})
    public Response deleteTelefone(@PathParam("id") Long id) {
        String login = tokenJwt.getSubject();
        Cliente cliente = clienteService.findByLogin(login);

        clienteService.deleteTelefone(cliente.getId(), id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/telefones/{id}")
    @PermitAll
    //@RolesAllowed({"User_Basic", "User"})
    public Response updateTelefone(@PathParam("id") Long id, TelefoneForm form) {
        String login = tokenJwt.getSubject();
        Cliente cliente = clienteService.findByLogin(login);
        clienteService.updateTelefone(cliente.getId(), id, form);
        return Response.ok().build();
    }


    @GET
    @Path("/download/{nomeImagem}")
    @PermitAll
    // @RolesAllowed({"Admin", "User"})
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


    @PATCH
    @Path("/senha")
    @PermitAll
    //@RolesAllowed({"User_Basic", "User", "Admin"})
    public Response updateSenha(SenhaDTO senhaDTO) {
        String login = tokenJwt.getSubject();
        usuarioService.updatePassword(login, senhaDTO);
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