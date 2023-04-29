package br.unitins;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import br.unitins.ecommerce.dto.game.GameDTO;
import br.unitins.ecommerce.dto.game.GameResponseDTO;
import br.unitins.ecommerce.model.produto.game.TipoGame;
import br.unitins.ecommerce.service.game.GameService;

@QuarkusTest
public class GameResourceTest {

    @Inject
    GameService gameService;

    @Test
    public void getAllTest() {

        given()
                .when().get("/games")
                .then()
                .statusCode(200);
    }

    @Test
    public void insertTest() {

        List<Long> plataformas = new ArrayList<>();
        plataformas.add(1l);
        plataformas.add(2l);
        plataformas.add(3l);
        plataformas.add(4l);
        plataformas.add(5l);
        plataformas.add(6l);
        plataformas.add(7l);

        GameDTO game = new GameDTO(
                "Hollow Knight",
                "Jogo indie de plataforma e ação com atmosfera sombria e desafiadora. Exploração de um vasto mundo interconectado, com combate preciso, chefes desafiadores e uma história rica em lore.",
                62.5,
                30,
                "Cristopher Larkin",
                2022,
                6l,
                2,
                plataformas);

        given()
                .contentType(ContentType.JSON)
                .body(game)
                .when().post("/games")
                .then()
                .statusCode(201)
                .body("id", notNullValue(), "nome", is("Hollow Knight"),
                        "descricao",
                        is("Jogo indie de plataforma e ação com atmosfera sombria e desafiadora. Exploração de um vasto mundo interconectado, com combate preciso, chefes desafiadores e uma história rica em lore."),
                        "preco", is(62.5f),
                        "estoque", is(30),
                        "diretor", is("Cristopher Larkin"),
                        "anoLancamento", is(2022),
                        "developer", is("Team Cherry"),
                        "tipoGame.label", is("Indie/Independente"),
                        "plataformas.get(0)", is("PlayStation 4"),
                        "plataformas.get(1)", is("PlayStation 5"),
                        "plataformas.get(2)", is("Xbox One"),
                        "plataformas.get(3)", is("Xbox Series S"),
                        "plataformas.get(4)", is("Xbox Series X"),
                        "plataformas.get(5)", is("Nintendo Switch"),
                        "plataformas.get(6)", is("PC"));
    }

    @Test
    public void updateTest() {

        List<Long> plataformas = new ArrayList<>();
        plataformas.add(1l);
        plataformas.add(2l);

        GameDTO game = new GameDTO(
            "Hollow Knight",
            "Jogo indie de plataforma e ação com atmosfera sombria e desafiadora. Exploração de um vasto mundo interconectado, com combate preciso, chefes desafiadores e uma história rica em lore.",
            62.5,
            30,
            "Cristopher Larkin",
            2022,
            6l,
            2,
            plataformas);

        Long id = gameService.create(game).id();

        List<Long> newPlataformas = new ArrayList<>();
        newPlataformas.add(1l);
        newPlataformas.add(2l);

        GameDTO newGame = new GameDTO(
                "God of War Ragnarok",
                "Sequência do aclamado jogo de ação da Sony Santa Monica. Continuação da históriade Kratos e Atreus em Midgard, com batalhas épicas, novo sistema de equipamentos e progressão de personagem.",
                299.9,
                30,
                "Eric Williams",
                2022,
                2l,
                1,
                newPlataformas);

        given()
          .contentType(ContentType.JSON)
          .body(newGame)
          .when().put("/games/alter/" + id)
          .then()
             .statusCode(204);

        GameResponseDTO gameResponse = gameService.findById(id);

        assertThat(gameResponse.nome(), is("God of War Ragnarok"));
        assertThat(gameResponse.descricao(), is("Sequência do aclamado jogo de ação da Sony Santa Monica. Continuação da históriade Kratos e Atreus em Midgard, com batalhas épicas, novo sistema de equipamentos e progressão de personagem."));
        assertThat(gameResponse.preco(), is(299.9));
        assertThat(gameResponse.estoque(), is(30));
        assertThat(gameResponse.diretor(), is("Eric Williams"));
        assertThat(gameResponse.anoLancamento(), is(2022));
        assertThat(gameResponse.developer(), is("Santa Monica Studio"));
        assertThat(gameResponse.tipoGame(), is(TipoGame.TRIPLE_A));
        assertThat(gameResponse.plataformas().get(0), is("PlayStation 4"));
        assertThat(gameResponse.plataformas().get(1), is("PlayStation 5"));
    }

    @Test
    public void deleteTest() {

        List<Long> plataformas = new ArrayList<>();
        plataformas.add(1l);
        plataformas.add(2l);

        GameDTO game = new GameDTO(
            "Hollow Knight",
            "Jogo indie de plataforma e ação com atmosfera sombria e desafiadora. Exploração de um vasto mundo interconectado, com combate preciso, chefes desafiadores e uma história rica em lore.",
            62.5,
            30,
            "Cristopher Larkin",
            2022,
            6l,
            2,
            plataformas);

        Long id = gameService.create(game).id();

        given()
          .when().delete("/games/" + id)
          .then()
             .statusCode(204);

        GameResponseDTO gameResponse = null;

        try {
            
            gameResponse =  gameService.findById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(gameResponse);   
        }
    }

    @Test
    public void countTest() {

        given()
            .when().get("/games/count")
            .then()
                .statusCode(200);
    }

    @Test
    public void findByIdTest() {

        List<Long> plataformas = new ArrayList<>();
        plataformas.add(1l);
        plataformas.add(2l);

        GameDTO game = new GameDTO(
                "God of War Ragnarok",
                "Sequência do aclamado jogo de ação da Sony Santa Monica. Continuação da históriade Kratos e Atreus em Midgard, com batalhas épicas, novo sistema de equipamentos e progressão de personagem.",
                299.9,
                30,
                "Eric Williams",
                2022,
                2l,
                1,
                plataformas);

        Long id = gameService.create(game).id();

        given()
            .when().get("/games/" + id)
            .then()
                .statusCode(200);
    }

    @Test
    public void searchByNomeTest() {

        List<Long> plataformas = new ArrayList<>();
        plataformas.add(1l);
        plataformas.add(2l);

        GameDTO game = new GameDTO(
                "God of War Ragnarok",
                "Sequência do aclamado jogo de ação da Sony Santa Monica. Continuação da históriade Kratos e Atreus em Midgard, com batalhas épicas, novo sistema de equipamentos e progressão de personagem.",
                299.9,
                30,
                "Eric Williams",
                2022,
                2l,
                1,
                plataformas);

        String nome = gameService.create(game).nome();

        given()
            .when().get("/games/searchByNome/" + nome)
            .then()
                .statusCode(200);
    }

    @Test
    public void filterByPrecoMinTest() {

        Double precoMin = 1200.0;

        given()
            .when().get("/games/filterByPrecoMin/" + precoMin)
            .then()
                .statusCode(200);
    }

    @Test
    public void filterByPrecoMaxTest() {

        Double precoMax = 1400.0;

        given()
            .pathParam("precoMax", precoMax)
            .when()
            .get("/games/filterByPrecoMax/{precoMax}")
            .then()
                .statusCode(200);
    }

    @Test
    public void filterByEntrePrecoTest() {

        Double precoMin = 1200.0;
        Double precoMax = 2000.0;

        given()
            .pathParam("precoMin", precoMin)
            .pathParam("precoMax", precoMax)
            .when()
            .get("/games/filterByEntrePreco/{precoMin}/{precoMax}")
            .then()
                .statusCode(200);
    }

}
