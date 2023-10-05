package br.unitins.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.unitins.ecommerce.dto.game.GameDTO;
import br.unitins.ecommerce.dto.game.GameResponseDTO;
import br.unitins.ecommerce.model.produto.game.Game;

@Mapper(componentModel = "jakarta")
public interface GameMapper {

    GameResponseDTO toResponse(Game game);

    List<GameResponseDTO> toListResponse(List<Game> games);

    Game toEntity(GameDTO gameDTO);
    
}
