package br.unitins.ecommerce.service.game;

import java.util.List;

import br.unitins.ecommerce.dto.game.GameDTO;
import br.unitins.ecommerce.dto.game.GameResponseDTO;

public interface GameService {

    // Metodos basicos
    
    List<GameResponseDTO> getAll();

    GameResponseDTO findById(Long id);

    GameResponseDTO create(GameDTO gameDTO);

    GameResponseDTO update(Long id, GameDTO gameDTO);

    void delete(Long id);

    long count();

    // Metodos extras

    List<GameResponseDTO> findByNome(String nome);



}
