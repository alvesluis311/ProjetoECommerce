package br.unitins.ecommerce.service;

import java.util.List;

import br.unitins.ecommerce.dto.game.DeveloperDTO;
import br.unitins.ecommerce.dto.game.DeveloperResponseDTO;

public interface DeveloperService {

    // Metodos basicos
    
    List<DeveloperResponseDTO> getAll();

    DeveloperResponseDTO findById(Long id);

    DeveloperResponseDTO create(DeveloperDTO developerDTO);

    DeveloperResponseDTO update(Long id, DeveloperDTO developerDTO);

    void delete(Long id);

    long count();

    // Metodos extras

    List<DeveloperResponseDTO> findByNome(String nome);



}
