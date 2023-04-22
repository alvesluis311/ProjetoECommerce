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
import br.unitins.ecommerce.dto.game.GameDTO;
import br.unitins.ecommerce.dto.game.GameResponseDTO;
import br.unitins.ecommerce.service.game.GameService;

@Path("/games")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {

    @Inject
    GameService gameService;

    @GET
    public List<GameResponseDTO> getAll() {
        return gameService.getAll();
    }

    @GET
    @Path("/{id}")
    public GameResponseDTO findById(@PathParam("id") Long id) {
        return gameService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response insert(GameDTO dto) {
        try {
            GameResponseDTO game = gameService.create(dto);
            return Response.status(Status.CREATED).entity(game).build();
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
    public Response update(@PathParam("id") Long id, GameDTO dto) {
        try {
            GameResponseDTO game = gameService.update(id, dto);
            return Response.ok(game).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }      
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        gameService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }


    @GET
    @Path("/count")
    public long count(){
        return gameService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<GameResponseDTO> search(@PathParam("nome") String nome){
        return gameService.findByNome(nome);
        
    }

    @GET
    @Path("/searchByPlataforma/{plataforma}")
    public List<GameResponseDTO> getByPlataforma (@PathParam("plataforma") String nomePlataforma) throws NullPointerException {

        return gameService.findByPlataforma(nomePlataforma);
    }

    @GET
    @Path("/filterByPrecoMin/{precoMin}")
    public List<GameResponseDTO> filterByPrecoMin (@PathParam("precoMin") Double preco) {

        return gameService.filterByPrecoMin(preco);
    }

    @GET
    @Path("/filterByPrecoMax/{precoMax}")
    public List<GameResponseDTO> filterByPrecoMax (@PathParam("precoMax") Double preco) {

        return gameService.filterByPrecoMax(preco);
    }

    @GET
    @Path("/filterByEntrePreco/{precoMin}&{precoMax}")
    public List<GameResponseDTO> filterByEntrePreco (@PathParam("precoMin") Double precoMin, @PathParam("precoMax") Double precoMax) {

        return gameService.filterByEntrePreco(precoMin, precoMax);
    }
    
}
