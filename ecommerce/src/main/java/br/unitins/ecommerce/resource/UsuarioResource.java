package br.unitins.ecommerce.resource;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.endereco.EnderecoResponse;
import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.service.endereco.EnderecoService;
import br.unitins.ecommerce.service.usuario.TelefoneService;
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

    @Inject
    EnderecoService enderecoService;

    @Inject
    TelefoneService telefoneService;

    @GET
    @Path("/{id}")
    public Response buscarUsuario(@PathParam("id") Long id) {
        UsuarioResponse anuncioResponse = usuarioService.buscarOuFalharResponsePorId(id);
        return Response.ok(anuncioResponse).build();
    }

    @GET
    public Response buscarListaUsuario() {
        List<UsuarioResponse> listaUsuario = usuarioService.buscarListaUsuario();

        return Response.status(listaUsuario.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaUsuario)
                .build();
    }

    @POST
    public Response adicionar(UsuarioForm form) {
        UsuarioResponse response = usuarioService.cadastrar(form);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, UsuarioRequest request) {
        UsuarioResponse response = usuarioService.atualizar(id, request);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @PATCH
    @Path("/{id}")
    public Response atualizarParcial(@PathParam("id") Long id, UsuarioPatch request) {
        UsuarioResponse response = usuarioService.atualizarParcial(id, request);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        usuarioService.deletar(id);
        return Response.ok().build();
    }


    @GET
    @Path("/{id}/enderecos/")
    public Response buscarListaEndereco(@PathParam("id") Long id) {
        List<EnderecoResponse> listaEndereco = enderecoService.buscarListaEnderecoResponse(id);
        return Response.status(listaEndereco.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaEndereco)
                .build();
    }

    @GET
    @Path("/{id}/enderecos/{enderecoId}")
    public Response buscarEndereco(@PathParam("id") Long id, @PathParam("enderecoId") Long enderecoId) {
        EnderecoResponse enderecoResponse = enderecoService.buscarOuFalharResponseId(id, enderecoId);
        return Response.ok(enderecoResponse).build();
    }

    @DELETE
    @Path("/{id}/enderecos/{enderecoId}")
    public Response deletarEndereco(@PathParam("id") Long id, @PathParam("enderecoId") Long enderecoId) {
        enderecoService.deletar(id, enderecoId);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}/enderecos/{enderecoId}")
    public Response atualizarEndereco(@PathParam("id") Long id, @PathParam("enderecoId") Long enderecoId, EnderecoForm form) {
        EnderecoResponse response = enderecoService.atualizar(id, enderecoId, form);
        return Response.ok(response).build();
    }

    @POST
    @Path("/{id}/enderecos/")
    public Response adicionarEndereco(@PathParam("id") Long id, EnderecoForm form) {
        EnderecoResponse response = enderecoService.cadastrar(id, form);
        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}/telefones/")
    public Response buscarListaTelefones(@PathParam("id") Long id) {
        List<TelefoneResponse> listaTelefone = telefoneService.buscarListaTelefoneResponse(id);
        return Response.status(listaTelefone.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaTelefone)
                .build();
    }

    @GET
    @Path("/{id}/telefones/{telefoneId}")
    public Response buscarTelefone(@PathParam("id") Long id, @PathParam("telefoneId") Long telefoneId) {
        TelefoneResponse telefoneResponse = telefoneService.buscarOuFalharResponseId(id, telefoneId);
        return Response.ok(telefoneResponse).build();
    }

    @POST
    @Path("/{id}/telefones/")
    public Response adicionarTelefone(@PathParam("id") Long id, TelefoneForm form) {
        TelefoneResponse response = telefoneService.cadastrar(id, form);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}/telefones/{telefoneId}")
    public Response deletarTelefone(@PathParam("id") Long id, @PathParam("telefoneId") Long telefoneId) {
        telefoneService.deletar(id, telefoneId);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}/telefones/{telefoneId}")
    public Response atualizarTelefone(@PathParam("id") Long id, @PathParam("telefoneId") Long telefoneId, TelefoneForm form) {
        TelefoneResponse response = telefoneService.atualizar(id, telefoneId, form);
        return Response.ok(response).build();
    }



}
