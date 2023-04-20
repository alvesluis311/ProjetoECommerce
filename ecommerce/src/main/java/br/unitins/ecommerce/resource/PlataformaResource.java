package br.unitins.ecommerce.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.plataforma.PlataformaDTO;
import br.unitins.ecommerce.dto.plataforma.PlataformaResponseDTO;
import br.unitins.ecommerce.service.plataforma.PlataformaService;

@Path("/plataformas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlataformaResource {

    @Inject
    PlataformaService plataformaService;

    @GET
    public List<PlataformaResponseDTO> getAll() {
        return plataformaService.getAll();
    }

    @GET
    @Path("/{id}")
    public PlataformaResponseDTO findById(@PathParam("id") Long id) {
        return plataformaService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response insert(PlataformaDTO dto) {
        try {
            PlataformaResponseDTO plataforma = plataformaService.create(dto);
            return Response.status(Status.CREATED).entity(plataforma).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("alter/{id}") // consulta ao ID; Entre chaves: significa que é um parametro
    @Consumes(MediaType.APPLICATION_JSON) //forma que o dado está vindo para ser consumido
    @Produces(MediaType.APPLICATION_JSON) // produz; retorna o metodo
    @Transactional // insert, delete e update
    public Response update(@PathParam("id") Long id, PlataformaDTO dto) {
        try {
            PlataformaResponseDTO plataforma = plataformaService.update(id, dto);
            return Response.ok(plataforma).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }      
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        plataformaService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }


    @GET
    @Path("/count")
    public long count(){
        return plataformaService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<PlataformaResponseDTO> search(@PathParam("nome") String nome){
        return plataformaService.findByNome(nome);
        
    }
    
}
