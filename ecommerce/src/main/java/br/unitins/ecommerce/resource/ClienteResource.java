package br.unitins.ecommerce.resource;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.endereco.EnderecoResponse;
import br.unitins.ecommerce.dto.usuario.ClienteForm;
import br.unitins.ecommerce.service.endereco.EnderecoService;
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

//    @Inject
//    TelefoneService telefoneService;


    @POST
    //Acess admin
    public Response add(ClienteForm form) {
        usuarioService.add(form);
        return Response.ok().build();
    }


    @GET
    @Path("/{id}/enderecos/")
    public Response findAllEnderecosByUsuarioId(@PathParam("id") Long id) {
        List<EnderecoResponse> listaEndereco = enderecoService.findAllEnderecosByUsuarioId(id);

        return Response.status(listaEndereco.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
                .entity(listaEndereco)
                .build();
    }

    @GET
    @Path("/{id}/enderecos/{enderecoId}")
    public Response findById(@PathParam("id") Long usuarioId, @PathParam("enderecoId") Long enderecoId) {
        EnderecoResponse enderecoResponse = enderecoService.findOrFailResponseByUsuarioIdAndEnderecoId(usuarioId, enderecoId);
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
        return Response.ok(response).build();
    }

//    @GET
//    @Path("/{id}/telefones/")
//    public Response buscarListaTelefones(@PathParam("id") Long id) {
//        List<TelefoneResponse> listaTelefone = telefoneService.buscarListaTelefoneResponse(id);
//        return Response.status(listaTelefone.isEmpty() ? Response.Status.NO_CONTENT : Response.Status.OK)
//                .entity(listaTelefone)
//                .build();
//    }
//
//    @GET
//    @Path("/{id}/telefones/{telefoneId}")
//    public Response buscarTelefone(@PathParam("id") Long id, @PathParam("telefoneId") Long telefoneId) {
//        TelefoneResponse telefoneResponse = telefoneService.buscarOuFalharResponseId(id, telefoneId);
//        return Response.ok(telefoneResponse).build();
//    }
//
//    @POST
//    @Path("/{id}/telefones/")
//    public Response adicionarTelefone(@PathParam("id") Long id, TelefoneForm form) {
//        TelefoneResponse response = telefoneService.cadastrar(id, form);
//        return Response.ok(response).build();
//    }
//
//    @DELETE
//    @Path("/{id}/telefones/{telefoneId}")
//    public Response deletarTelefone(@PathParam("id") Long id, @PathParam("telefoneId") Long telefoneId) {
//        telefoneService.deletar(id, telefoneId);
//        return Response.ok().build();
//    }
//
//    @PUT
//    @Path("/{id}/telefones/{telefoneId}")
//    public Response atualizarTelefone(@PathParam("id") Long id, @PathParam("telefoneId") Long telefoneId, TelefoneForm form) {
//        TelefoneResponse response = telefoneService.atualizar(id, telefoneId, form);
//        return Response.ok(response).build();
//    }
}
