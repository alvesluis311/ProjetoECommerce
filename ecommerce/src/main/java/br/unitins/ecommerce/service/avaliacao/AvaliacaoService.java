package br.unitins.ecommerce.service.avaliacao;

import java.util.List;

import br.unitins.ecommerce.dto.avaliacao.AvaliacaoDTO;
import br.unitins.ecommerce.dto.avaliacao.AvaliacaoResponseDTO;
import br.unitins.ecommerce.model.produto.game.Game;

public interface AvaliacaoService {
    
    // Metodos basicos

    List<AvaliacaoResponseDTO> getAll();
    
    AvaliacaoResponseDTO getById(Long id);

    AvaliacaoResponseDTO insert(AvaliacaoDTO avaliacaDto);

    AvaliacaoResponseDTO update(Long id, AvaliacaoDTO avaliacaDto);

    void delete(Long id);

    public void delete(Game game);

    // Metodos extras

    Long count();

    List<AvaliacaoResponseDTO> getByYear(Integer year);
}
