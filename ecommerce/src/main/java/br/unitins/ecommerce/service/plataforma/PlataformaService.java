package br.unitins.ecommerce.service.plataforma;

import java.util.List;

import br.unitins.ecommerce.dto.plataforma.PlataformaDTO;
import br.unitins.ecommerce.dto.plataforma.PlataformaResponseDTO;

public interface PlataformaService {
    
    // Metodos basicos
    
    List<PlataformaResponseDTO> getAll();

    PlataformaResponseDTO findById(Long id);

    PlataformaResponseDTO create(PlataformaDTO plataformaDTO);

    PlataformaResponseDTO update(Long id, PlataformaDTO plataformaDTO);

    void delete(Long id);

    long count();

    // Metodos extras

    List<PlataformaResponseDTO> findByNome(String nome);

}
