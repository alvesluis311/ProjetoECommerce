package br.unitins.ecommerce.service.genero;

import java.util.List;

import br.unitins.ecommerce.dto.genero.GeneroDTO;
import br.unitins.ecommerce.dto.genero.GeneroResponseDTO;

public interface GeneroService {

    // Metodos basicos
    
    List<GeneroResponseDTO> getAll(int page, int pageSize);

    GeneroResponseDTO getById(Long id);

    GeneroResponseDTO create(GeneroDTO generoDTO);

    GeneroResponseDTO update(Long id, GeneroDTO generoDTO);

    void delete(Long id);

    long count();

    // Metodos extras

    List<GeneroResponseDTO> findByNome(String nome, int page, int pageSize);

    long countByNome(String nome);

}
