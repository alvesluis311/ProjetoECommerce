package br.unitins.ecommerce.resource;

import java.io.IOException;
import java.util.List;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

import br.unitins.ecommerce.application.Result;
import br.unitins.ecommerce.dto.game.GameDTO;
import br.unitins.ecommerce.dto.game.GameResponseDTO;
import br.unitins.ecommerce.form.ImageForm;
import br.unitins.ecommerce.service.file.FileService;
import br.unitins.ecommerce.service.game.GameService;

@Path("/games")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {

    @Inject
    GameService gameService;

    @Inject
    FileService fileService;

    @GET
    public List<GameResponseDTO> getAll() {
        return gameService.getAll();
    }

    @GET
    @Path("/{id}")
    public GameResponseDTO findById(@PathParam("id") Long id) {
        return gameService.findById(id);
    }

    @GET
    @Path("/download/{nomeImagem}")
    @RolesAllowed({"Admin","User"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {

        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));

        response.header("Content-Disposition", "attachment;filename="+nomeImagem);

        return response.build();
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
            gameService.update(id, dto);
            return Response
                    .status(Status.NO_CONTENT) // 204
                    .build();
        } catch(ConstraintViolationException e) {

            Result result = new Result(e.getConstraintViolations());
            
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }          
    }

    @PATCH
    @Path("/atualizar-imagem/{id}")
    @RolesAllowed({"Admin"})
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ImageForm form, @PathParam("id") Long id){

        String nomeImagem = "";

        try {

            nomeImagem = fileService.salvarImagemUsuario(form.getImagem(), form.getNomeImagem());
        } catch (IOException e) {

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.CONFLICT).entity(result).build();
        }

        gameService.update(id, nomeImagem);

        return Response.status(Status.NO_CONTENT).build();
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
    @Path("/searchByNome/{nome}")
    public List<GameResponseDTO> search(@PathParam("nome") String nome){
        return gameService.findByNome(nome);
        
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
    @Path("/filterByEntrePreco/{precoMin}/{precoMax}")
    public List<GameResponseDTO> filterByEntrePreco (@PathParam("precoMin") Double precoMin, @PathParam("precoMax") Double precoMax) {

        return gameService.filterByEntrePreco(precoMin, precoMax);
    }
    
}
