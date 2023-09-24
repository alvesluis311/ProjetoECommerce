package br.unitins.ecommerce.resource;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.endereco.EnderecoResponse;
import br.unitins.ecommerce.dto.usuario.ClienteForm;
import br.unitins.ecommerce.dto.usuario.ClienteResponse;
import br.unitins.ecommerce.dto.usuario.TelefoneForm;
import br.unitins.ecommerce.dto.usuario.TelefoneResponse;
import br.unitins.ecommerce.service.endereco.EnderecoService;
import br.unitins.ecommerce.service.usuario.TelefoneService;
import br.unitins.ecommerce.service.usuario.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
public class ClienteResource {

    @Inject
    UsuarioService usuarioService;

    @Inject
    EnderecoService enderecoService;

    @Inject
    TelefoneService telefoneService;


    @POST
    //Acess admin
    public Response add(ClienteForm form) {
        usuarioService.add(form);
        return Response.status(Response.Status.CREATED).build();
    }


    @GET
    public Response getAll() {
       List<ClienteResponse> listaClientes = usuarioService.findAllClientes();
        return Response.status(listaClientes.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaClientes)
                .build();
    }


    @GET
    @Path("/{id}/enderecos/")
    public Response getAllEnderecos(@PathParam("id") Long id) {
        List<EnderecoResponse> listaEndereco = enderecoService.findAllEnderecosByUsuarioId(id);

        return Response.status(listaEndereco.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaEndereco)
                .build();
    }

    @GET
    @Path("/{id}/enderecos/{enderecoId}")
    public Response getEndereco(@PathParam("id") Long usuarioId, @PathParam("enderecoId") Long enderecoId) {
        EnderecoResponse enderecoResponse = enderecoService.findOrFailResponseById(usuarioId, enderecoId);
        return Response.ok(enderecoResponse).build();
    }

    @DELETE
    @Path("/{id}/enderecos/{enderecoId}")
    public Response deleteEndereco(@PathParam("id") Long usuarioId, @PathParam("enderecoId") Long enderecoId) {
        enderecoService.delete(usuarioId, enderecoId);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}/enderecos/{enderecoId}")
    public Response updateEndereco(@PathParam("id") Long usuarioId, @PathParam("enderecoId") Long enderecoId, EnderecoForm form) {
        EnderecoResponse response = enderecoService.update(usuarioId, enderecoId, form);
        return Response.ok(response).build();
    }

    @POST
    @Path("/{id}/enderecos/")
    public Response addEndereco(@PathParam("id") Long usuarioId, EnderecoForm form) {
        EnderecoResponse response = enderecoService.add(usuarioId, form);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}/telefones/")
    public Response getTelefones(@PathParam("id") Long id) {
        List<TelefoneResponse> listaTelefone = telefoneService.findTelefonesByUsuarioId(id);
        return Response.status(listaTelefone.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaTelefone)
                .build();
    }

    @GET
    @Path("/{id}/telefones/{telefoneId}")
    public Response getTelefone(@PathParam("id") Long id, @PathParam("telefoneId") Long telefoneId) {
        TelefoneResponse telefoneResponse = telefoneService.findOrFailResponseById(id, telefoneId);
        return Response.ok(telefoneResponse).build();
    }

    @POST
    @Path("/{id}/telefones/")
    public Response addTelefone(@PathParam("id") Long id, TelefoneForm form) {
        telefoneService.add(id, form);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}/telefones/{telefoneId}")
    public Response deleteTelefone(@PathParam("id") Long id, @PathParam("telefoneId") Long telefoneId) {
        telefoneService.deletar(id, telefoneId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/{id}/telefones/{telefoneId}")
    public Response updateTelefone(@PathParam("id") Long id, @PathParam("telefoneId") Long telefoneId, TelefoneForm form) {
        telefoneService.atualizar(id, telefoneId, form);
        return Response.ok().build();
    }
}
