package br.unitins.ecommerce.service.game;

import java.util.List;

import br.unitins.ecommerce.dto.game.GameDTO;
import br.unitins.ecommerce.dto.game.GameResponseDTO;

public interface GameService {

    // Metodos basicos
    
    List<GameResponseDTO> getAll();

    GameResponseDTO getById(Long id);

    GameResponseDTO create(GameDTO gameDTO);

    GameResponseDTO update(Long id, GameDTO gameDTO);

    void update(Long id, String nomeImagem);

    void delete(Long id);

    long count();

    // Metodos extras

    List<GameResponseDTO> findByNome(String nome);

    List<GameResponseDTO> getByDeveloper(Long id);

    List<GameResponseDTO> getByGenero(Long id);

    // metodos de filtragem

    List<GameResponseDTO> filterByPrecoMin(Double preco);

    List<GameResponseDTO> filterByPrecoMax(Double preco);

    List<GameResponseDTO> filterByEntrePreco(Double precoMin, Double precoMax);


}
