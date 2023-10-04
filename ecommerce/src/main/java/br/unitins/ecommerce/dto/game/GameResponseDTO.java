package br.unitins.ecommerce.dto.game;

import br.unitins.ecommerce.model.produto.game.Game;
import br.unitins.ecommerce.model.produto.game.Genero;
import br.unitins.ecommerce.model.produto.plataforma.Plataforma;

import java.util.ArrayList;
import java.util.List;

public record GameResponseDTO(
    Long id,
    String nome,
    String descricao,
    String nomeImagem,
    Double preco,
    Integer estoque,
    String diretor,
    Integer anoLancamento,
    String developer,
    List<String> generos,
    List<String> plataformas
) {
    public static GameResponseDTO valueOf(Game game) {
        return new GameResponseDTO(
            game.getId(),
            game.getNome(),
            game.getDescricao(),
            game.getNomeImagem(),
            game.getPreco(),
            game.getEstoque().getQtdDisponivel(),
            game.getDiretor(),
            game.getAnoLancamento(),
            game.getDeveloper().getNome(),
            viewGeneros(game.getGeneros()),
            viewPlataformas(game.getPlataformas()));
    }

    private static List<String> viewPlataformas (List<Plataforma> lista) {

        List<String> listaPlataformas = new ArrayList<>();

        for (Plataforma plataformas : lista) {

            listaPlataformas.add(plataformas.getNome());
        }

        return listaPlataformas;
    }

    private static List<String> viewGeneros (List<Genero> lista) {

        List<String> listaGeneros = new ArrayList<>();

        for (Genero generos : lista) {

            listaGeneros.add(generos.getNome());
        }

        return listaGeneros;
    }
}
