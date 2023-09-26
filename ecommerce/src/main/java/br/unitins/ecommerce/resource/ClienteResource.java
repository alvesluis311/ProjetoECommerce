package br.unitins.ecommerce.resource;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.endereco.EnderecoResponse;
import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.service.usuario.ClienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
public class ClienteResource {

    @Inject
    ClienteService clienteService;


    @GET
    public Response getAll() {
        List<ClienteResponse> listaClientes = clienteService.findAll();
        return Response.status(listaClientes.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaClientes)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        ClienteResponse response = clienteService.findResponseById(id);
        return Response.ok(response).build();
    }

    @POST
    public Response insert(ClienteForm form) {
        clienteService.insert(form);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ClienteUpdate form) {
        clienteService.update(id, form);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    public Response delete(Long id) {
        clienteService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}/enderecos/")
    public Response getAllEnderecos(@PathParam("id") Long id) {
        List<EnderecoResponse> listaEndereco = clienteService.findAllEnderecos(id);

        return Response.status(listaEndereco.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaEndereco)
                .build();
    }

    @GET
    @Path("/{id}/enderecos/{enderecoId}")
    public Response getEndereco(@PathParam("id") Long id, @PathParam("enderecoId") Long enderecoId) {
        EnderecoResponse enderecoResponse = clienteService.findEndereco(id, enderecoId);
        return Response.ok(enderecoResponse).build();
    }

    @DELETE
    @Path("/{id}/enderecos/{enderecoId}")
    public Response deleteEndereco(@PathParam("id") Long usuarioId, @PathParam("enderecoId") Long enderecoId) {
        clienteService.deleteEndereco(usuarioId, enderecoId);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}/enderecos/{enderecoId}")
    public Response updateEndereco(@PathParam("id") Long usuarioId, @PathParam("enderecoId") Long enderecoId, EnderecoForm form) {
        clienteService.updateEndereco(usuarioId, enderecoId, form);
        return Response.ok().build();
    }

    @POST
    @Path("/{id}/enderecos/")
    public Response insertEndereco(@PathParam("id") Long id, EnderecoForm form) {

        clienteService.insertEndereco(id, form);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}/telefones/")
    public Response getAllTelefones(@PathParam("id") Long id) {
        List<TelefoneResponse> listaTelefone = clienteService.findAllTelefones(id);
        return Response.status(listaTelefone.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaTelefone)
                .build();
    }

    @GET
    @Path("/{id}/telefones/{telefoneId}")
    public Response getTelefone(@PathParam("id") Long id, @PathParam("telefoneId") Long telefoneId) {
        TelefoneResponse telefoneResponse = clienteService.findTelefone(id, telefoneId);
        return Response.ok(telefoneResponse).build();
    }

    @POST
    @Path("/{id}/telefones/")
    public Response addTelefone(@PathParam("id") Long id, TelefoneForm form) {
        clienteService.insertTelefone(id, form);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}/telefones/{telefoneId}")
    public Response deleteTelefone(@PathParam("id") Long id, @PathParam("telefoneId") Long telefoneId) {
        clienteService.deleteTelefone(id, telefoneId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/{id}/telefones/{telefoneId}")
    public Response updateTelefone(@PathParam("id") Long id, @PathParam("telefoneId") Long telefoneId, TelefoneForm form) {
        clienteService.updateTelefone(id, telefoneId, form);
        return Response.ok().build();
    }
}
