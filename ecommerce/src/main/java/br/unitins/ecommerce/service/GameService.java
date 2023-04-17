package topicos1.unitins.projeto.service;

import java.util.List;

import topicos1.unitins.projeto.dto.GameDTO;
import topicos1.unitins.projeto.dto.GameResponseDTO;

public interface GameService {
    
    List<GameResponseDTO> getAll();

    GameResponseDTO findById(Long id);

    GameResponseDTO create(GameDTO gameDTO);

    GameResponseDTO update(Long id, GameDTO gameDTO);

    void delete(Long id);

    List<GameResponseDTO> findByNome(String nome);

    long count();

}
