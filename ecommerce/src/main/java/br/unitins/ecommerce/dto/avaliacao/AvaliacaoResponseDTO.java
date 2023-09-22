package br.unitins.ecommerce.dto.avaliacao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.unitins.ecommerce.model.produto.avaliacao.Avaliacao;
import br.unitins.ecommerce.model.produto.avaliacao.Estrela;

public record AvaliacaoResponseDTO(
    Long id,
    String comentario,
    LocalDate data,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Estrela estrela,

    Map<String, Object> game,
    Map<String, Object> usuario
) {
    
    public AvaliacaoResponseDTO (Avaliacao avaliacao) {

        this(avaliacao.getId(),
            avaliacao.getComentario() != null?
                                            avaliacao.getComentario()
                                            : null,
            avaliacao.getData(),
            avaliacao.getEstrela(),
            viewGame(avaliacao.getGame().getId(),
                        avaliacao.getGame().getNome()),
            viewUsuario(avaliacao.getUsuario().getId(),
                        avaliacao.getUsuario().getLogin(),
                    ""));
    }

    public static Map<String, Object> viewGame (Long id, String nome) {

        Map<String, Object> game = new HashMap<>();

        game.put("id", id);
        game.put("nome", nome);

        return game;
    }

    public static Map<String, Object> viewUsuario (Long id, String login, String email) {

        Map<String, Object> usuario = new HashMap<>();

        usuario.put("id", id);
        usuario.put("login", login);
        usuario.put("email", email);

        return usuario;
    }
}