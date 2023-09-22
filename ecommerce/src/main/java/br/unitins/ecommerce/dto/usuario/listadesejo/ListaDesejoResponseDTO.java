package br.unitins.ecommerce.dto.usuario.listadesejo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unitins.ecommerce.dto.avaliacao.AvaliacaoResponseDTO;
import br.unitins.ecommerce.model.produto.game.Game;
import br.unitins.ecommerce.model.usuario.Usuario;

public record ListaDesejoResponseDTO(
    Map<String, Object> usuario,
    List<Map<String, Object>> games
) {


    private static List<Map<String, Object>> viewGames (List<Game> lista) {

        List<Map<String, Object>> listaGames = new ArrayList<>();

        for (Game games : lista) {
            
            Map<String, Object> game = new HashMap<>();

            game = AvaliacaoResponseDTO.viewGame(games.getId(), games.getNome());

            listaGames.add(game);
        }

        return listaGames;
    }
}