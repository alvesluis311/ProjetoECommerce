package br.unitins.ecommerce.service.fabricante;

import java.util.List;

import br.unitins.ecommerce.dto.fabricante.FabricanteDTO;
import br.unitins.ecommerce.dto.fabricante.FabricanteResponseDTO;

public interface FabricanteService {

    // Metodos basicos
    
    List<FabricanteResponseDTO> getAll();

    FabricanteResponseDTO getById(Long id);

    FabricanteResponseDTO create(FabricanteDTO fabricanteDTO);

    FabricanteResponseDTO update(Long id, FabricanteDTO fabricanteDTO);

    void delete(Long id);

    long count();

    // Metodos extras

    List<FabricanteResponseDTO> findByNome(String nome);

}
