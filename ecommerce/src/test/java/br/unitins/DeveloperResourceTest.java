package br.unitins;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import br.unitins.ecommerce.dto.developer.DeveloperDTO;
import br.unitins.ecommerce.dto.developer.DeveloperResponseDTO;
import br.unitins.ecommerce.service.developer.DeveloperService;

@QuarkusTest
public class DeveloperResourceTest {

    @Inject
    DeveloperService developerService;

    @Test
    public void getAllTest() {

        given()
                .when().get("/developers")
                .then()
                .statusCode(200);
    }

    @Test
    public void insertTest() {

        DeveloperDTO developer = new DeveloperDTO(
            "Team Cherry",
            2004);

        given()
            .contentType(ContentType.JSON)
            .body(developer)
            .when().post("/developers")
            .then()
                .statusCode(201)
                .body("id", notNullValue(), "nome", is("Team Cherry"),
                "fundacao", is(2004));
    }

    @Test
    public void updateTest() {

        DeveloperDTO developer = new DeveloperDTO(
            "Team Cherry",
            2004);

        Long id = developerService.create(developer).id();

        DeveloperDTO developerUpdate = new DeveloperDTO(
            "Team Cherry 2.0",
            2023);

        given()
          .contentType(ContentType.JSON)
          .body(developerUpdate)
          .when().put("/developers/alter/" + id)
          .then()
             .statusCode(204);

        DeveloperResponseDTO developerResponse = developerService.findById(id);

        assertThat(developerResponse.nome(), is("Team Cherry 2.0"));
        assertThat(developerResponse.fundacao(), is(2023));
    }

    @Test
    public void countTest() {

        given()
            .when().get("/developers/count")
            .then()
                .statusCode(200);
    }

    @Test
    public void findByNomeTest() {

        DeveloperDTO developer = new DeveloperDTO(
            "Team Cherry",
            2004);

        String nome = developerService.create(developer).nome();

        given()
            .when().get("/developers/search/" + nome)
            .then()
                .statusCode(200);
    }
}
