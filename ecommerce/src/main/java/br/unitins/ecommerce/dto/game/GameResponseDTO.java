package br.unitins.ecommerce.dto.game;

import com.fasterxml.jackson.annotation.JsonInclude;

// import java.util.HashMap;
// import java.util.Map;

import br.unitins.ecommerce.model.produto.game.Game;

public record GameResponseDTO (

    Long id,
    String nome,
    String descricao,
    Double preco,
    String estoque,
    String diretor,
    Integer anoLancamento,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String developer
   )
   {
    public GameResponseDTO(Game game) {
        this(game.getId(),
            game.getNome(),
            game.getDescricao(),
            game.getPreco(),
            game.getEstoque() > 0? "Disponível" : "Estoque esgotado",
            game.getDiretor(),
            game.getAnoLancamento(),
            game.getDeveloper().getLabel()
        );
    }
    
    
}
