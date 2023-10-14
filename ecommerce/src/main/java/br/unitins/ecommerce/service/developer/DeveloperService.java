package br.unitins.ecommerce.service.developer;

import java.util.List;

import br.unitins.ecommerce.dto.developer.DeveloperDTO;
import br.unitins.ecommerce.dto.developer.DeveloperResponseDTO;

public interface DeveloperService {

    // Metodos basicos
    
    List<DeveloperResponseDTO> getAll(int page, int pageSize);

    DeveloperResponseDTO getById(Long id);

    DeveloperResponseDTO create(DeveloperDTO developerDTO);

    DeveloperResponseDTO update(Long id, DeveloperDTO developerDTO);

    void delete(Long id);

    long count();

    // Metodos extras

    List<DeveloperResponseDTO> findByNome(String nome, int page, int pageSize);

    long countByNome(String nome);

}
