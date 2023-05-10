package br.unitins;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import br.unitins.ecommerce.dto.plataforma.PlataformaDTO;
import br.unitins.ecommerce.dto.plataforma.PlataformaResponseDTO;
import br.unitins.ecommerce.model.produto.plataforma.Fabricante;
import br.unitins.ecommerce.service.plataforma.PlataformaService;

@QuarkusTest
public class PlataformaResourceTest {

    @Inject
    PlataformaService plataformaService;

    
    @Test
    public void getAllTest() {

        given()
                .when().get("/plataformas")
                .then()
                .statusCode(200);
    }

    @Test
    public void insertTest() {

        PlataformaDTO plataforma = new PlataformaDTO(
            "PlayStation 4",
            "O PS4  é um console de jogos da Sony, que foi um grande sucesso e vendeu mais de 15 milhões de unidades em todo o mundo. Apresentou um grande salto em relação ao PS3", 
            2013, 
            1);

        given()
            .contentType(ContentType.JSON)
            .body(plataforma)
            .when().post("/plataformas")
            .then()
                .statusCode(201)
                .body("id", notNullValue(), "nome", is("PlayStation 4"),
                "descricao", is("O PS4  é um console de jogos da Sony, que foi um grande sucesso e vendeu mais de 15 milhões de unidades em todo o mundo. Apresentou um grande salto em relação ao PS3"),
                "anoLancamento", is(2013),
                "fabricante.label", is("Sony"));
    }

    @Test
    public void updateTest() {

        PlataformaDTO plataforma = new PlataformaDTO(
            "PlayStation 4",
            "O PS4  é um console de jogos da Sony, que foi um grande sucesso e vendeu mais de 15 milhões de unidades em todo o mundo. Apresentou um grande salto em relação ao PS3", 
            2013, 
            1);

        Long id = plataformaService.create(plataforma).id();

        PlataformaDTO plataformaUpdate = new PlataformaDTO(
            "PlayStation 3",
            "O PS3  é um console de jogos da Sony", 
            2006, 
            1);

        given()
          .contentType(ContentType.JSON)
          .body(plataformaUpdate)
          .when().put("/plataformas/alter/" + id)
          .then()
             .statusCode(204);

        PlataformaResponseDTO plataformaResponse = plataformaService.findById(id);

        assertThat(plataformaResponse.nome(), is("PlayStation 3"));
        assertThat(plataformaResponse.descricao(), is("O PS3  é um console de jogos da Sony"));
        assertThat(plataformaResponse.anoLancamento(), is(2006));
        assertThat(plataformaResponse.fabricante(), is(Fabricante.SONY));
    }

    @Test
    public void countTest() {

        given()
            .when().get("/plataformas/count")
            .then()
                .statusCode(200);
    }

    @Test
    public void findByNomeTest() {

        PlataformaDTO plataforma = new PlataformaDTO(
            "PlayStation 5",
            "O PS5 é a mais recente versão do console de jogos da Sony é o sucessor do PS4 e apresenta melhorias em relação ao seu antecessor.", 
            2020, 
            1);

        String nome = plataformaService.create(plataforma).nome();

        given()
            .when().get("/plataformas/search/" + nome)
            .then()
                .statusCode(200);
    }

}
// String nome,

//     String descricao,

//     Integer anoLancamento,

//     @Enumerated(EnumType.STRING)
//     Integer fabricante
