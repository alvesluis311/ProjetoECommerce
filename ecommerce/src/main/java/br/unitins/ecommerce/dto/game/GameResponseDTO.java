package br.unitins.ecommerce.dto.game;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;
import java.util.stream.Collectors;

import br.unitins.ecommerce.model.produto.Plataforma;
import br.unitins.ecommerce.model.produto.game.Game;

public record GameResponseDTO(
    Long id,
    String nome,
    String descricao,
    Double preco,
    Integer estoque,
    String diretor,
    Integer anoLancamento,
    String developer,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String tipoGame,

    Map<String, Object> plataformas
) {
    public GameResponseDTO(Game game) {
        this(
            game.getId(),
            game.getNome(),
            game.getDescricao(),
            game.getPreco(),
            game.getEstoque(),
            game.getDiretor(),
            game.getAnoLancamento(),
            game.getDeveloper().getNome(),
            game.getTipoGame().getLabel(),
            Map.of("nomes", game.getPlataformas().stream()
            .map(Plataforma::getNome).collect(Collectors.toList()))
        );
    }
}

