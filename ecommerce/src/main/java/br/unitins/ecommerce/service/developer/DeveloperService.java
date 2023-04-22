package br.unitins.ecommerce.service.developer;

import java.util.List;

import br.unitins.ecommerce.dto.developer.DeveloperDTO;
import br.unitins.ecommerce.dto.developer.DeveloperResponseDTO;

public interface DeveloperService {

    // Metodos basicos
    
    List<DeveloperResponseDTO> getAll();

    DeveloperResponseDTO findById(Long id);

    DeveloperResponseDTO create(DeveloperDTO developerDTO);

    DeveloperResponseDTO update(Long id, DeveloperDTO developerDTO);

    long count();

    // Metodos extras

    List<DeveloperResponseDTO> findByNome(String nome);



}
