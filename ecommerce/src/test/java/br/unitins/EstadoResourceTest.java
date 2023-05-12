package br.unitins;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashSet;
import java.util.Set;

import jakarta.inject.Inject;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import br.unitins.ecommerce.dto.endereco.EnderecoDTO;
import br.unitins.ecommerce.dto.estado.EstadoDTO;
import br.unitins.ecommerce.dto.estado.EstadoResponseDTO;
import br.unitins.ecommerce.dto.telefone.TelefoneDTO;
import br.unitins.ecommerce.dto.usuario.PessoaFisicaDTO;
import br.unitins.ecommerce.dto.usuario.UsuarioDTO;
import br.unitins.ecommerce.model.usuario.Perfil;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.service.estado.EstadoService;
import br.unitins.ecommerce.service.hash.HashService;
import br.unitins.ecommerce.service.token.TokenJwtService;
import br.unitins.ecommerce.service.usuario.UsuarioService;

@QuarkusTest
public class EstadoResourceTest {
    
    @Inject
    EstadoService estadoService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    HashService hashService;

    @Inject
    TokenJwtService tokenJwtService;
    

    @Test
    public void getAllTest() {
        
        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO(
            "Danilo Da Silva",
            "89912402394",
            "Danil789@unitins.br",
            1);

        EnderecoDTO enderecoDTO = new EnderecoDTO(
            "Avenida Tocantins",
            "Setor Bueno",
            "8780",
            "apto 3",
            "77780-920",
            4l);

        TelefoneDTO telefonePrincipalDTO = new TelefoneDTO("067", "98467-8901");

        TelefoneDTO telefoneOpcionalDTO = new TelefoneDTO("067", "4002-8922");

        UsuarioDTO usuarioDto = new UsuarioDTO(
                "Danilo",
                "senha1234",
                pessoaFisicaDTO,
                enderecoDTO,
                telefonePrincipalDTO,
                telefoneOpcionalDTO);

        usuarioService.insert(usuarioDto);    

        Usuario usuario = usuarioService.getByLoginAndSenha("Danilo", "senha1234");

        Set<Perfil> perfis = new HashSet<>();

        perfis.add(Perfil.ADMIN);

        usuario.setPerfis(perfis);

        String token = tokenJwtService.generateJwt(usuario);

        given()
            .header("Authorization", "Bearer " + token) 
                .when().get("/estados")
                .then()
                .statusCode(200);
    }

    @Test
    public void insertTest() {

        EstadoDTO estado = new EstadoDTO(
                "Roraima", 
                "RR");

        given()
            .contentType(ContentType.JSON)
            .body(estado)
            .when().post("/estados")
            .then()
                .statusCode(201)
                .body("id", notNullValue(), "nome", is("Roraima"),
                "sigla", is("RR"));
    }

    @Test
    public void updateTest() {

        EstadoDTO estado = new EstadoDTO(
            "Roraima", 
            "RR");

        Long id = estadoService.insert(estado).id();

        EstadoDTO estadoUpdate = new EstadoDTO(
            "Mato Grosso",
            "MT"
        );

        given()
          .contentType(ContentType.JSON)
          .body(estadoUpdate)
          .when().put("/estados/" + id)
          .then()
             .statusCode(204);

        EstadoResponseDTO estadoResponse = estadoService.getById(id);

        assertThat(estadoResponse.nome(), is("Mato Grosso"));
        assertThat(estadoResponse.sigla(), is("MT"));
    }

    @Test
    public void deleteTest() {

        EstadoDTO estado = new EstadoDTO(
            "Roraima", 
            "RR"
        );

        Long id = estadoService.insert(estado).id();

        given()
          .when().delete("/estados/" + id)
          .then()
             .statusCode(204);

        EstadoResponseDTO estadoResponse = null;

        try {
            
            estadoResponse =  estadoService.getById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(estadoResponse);   
        }
    }

    @Test
    public void countTest() {
        
        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO(
            "Danilo Da Silva",
            "89012376794",
            "DaniloDaSilv@unitins.br",
            1);

        EnderecoDTO enderecoDTO = new EnderecoDTO(
            "Avenida Tocantins",
            "Setor Bueno",
            "8780",
            "apto 3",
            "77780-920",
            4l);

        TelefoneDTO telefonePrincipalDTO = new TelefoneDTO("067", "98467-8901");

        TelefoneDTO telefoneOpcionalDTO = new TelefoneDTO("067", "4002-8922");

        UsuarioDTO usuarioDto = new UsuarioDTO(
          "Danilo",
                "senha1234",
                pessoaFisicaDTO,
                enderecoDTO,
                telefonePrincipalDTO,
                telefoneOpcionalDTO);

        String nome = usuarioService.insert(usuarioDto).nome();

        Usuario usuario = usuarioService.getByLoginAndSenha("Danilo", "senha1234");

        Set<Perfil> perfis = new HashSet<>();

        perfis.add(Perfil.ADMIN);

        usuario.setPerfis(perfis);

        String token = tokenJwtService.generateJwt(usuario);

        given()
            .header("Authorization", "Bearer " + token)
            .when().get("/estados/count")
            .then()
                .statusCode(200);
    }

    @Test
    public void getByIdTest() {

        EstadoDTO estado = new EstadoDTO(
            "Roraima", 
            "RR"
        );

        Long id = estadoService.insert(estado).id();

        given()
            .when().get("/estados/" + id)
            .then()
                .statusCode(200);
    }

    @Test
    public void getByNomeTest() {

        EstadoDTO estado = new EstadoDTO(
            "Roraima",
            "RR"
        );

        String nome = estadoService.insert(estado).nome();

        given()
            .when().get("/estados/searchByNome/" + nome)
            .then()
                .statusCode(200);
    }

    @Test
    public void getBySiglaTest() {

        EstadoDTO estado = new EstadoDTO(
            "Roraima",
            "RR"
        );

        String sigla = estadoService.insert(estado).sigla();

        given()
            .when().get("/estados/searchBySigla/" + sigla)
            .then()
                .statusCode(200);
    }
}
