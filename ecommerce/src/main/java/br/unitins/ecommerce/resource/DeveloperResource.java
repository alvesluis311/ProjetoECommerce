package br.unitins.ecommerce.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
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
import br.unitins.ecommerce.dto.developer.DeveloperDTO;
import br.unitins.ecommerce.dto.developer.DeveloperResponseDTO;
import br.unitins.ecommerce.service.developer.DeveloperService;

@Path("/developers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DeveloperResource {

    @Inject
    DeveloperService developerService;

    @GET
    public List<DeveloperResponseDTO> getAll() {
        return developerService.getAll();
    }

    @GET
    @Path("/{id}")
    public DeveloperResponseDTO findById(@PathParam("id") Long id) {
        return developerService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response insert(DeveloperDTO dto) {
        try {
            DeveloperResponseDTO developer = developerService.create(dto);
            return Response.status(Status.CREATED).entity(developer).build();
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
    public Response update(@PathParam("id") Long id, DeveloperDTO dto) {
        try {
            developerService.update(id, dto);
            return Response
                    .status(Status.NO_CONTENT) // 204
                    .build();
        } catch(ConstraintViolationException e) {

            Result result = new Result(e.getConstraintViolations());
            
            return Response.status(Status.NOT_FOUND).entity(result).build();    
        }
    }

    @GET
    @Path("/count")
    public long count(){
        return developerService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<DeveloperResponseDTO> search(@PathParam("nome") String nome){
        return developerService.findByNome(nome);
        
    }
    
}
