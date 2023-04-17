package topicos1.unitins.projeto.service;

import java.util.List;

import topicos1.unitins.projeto.dto.PlataformaDTO;
import topicos1.unitins.projeto.dto.PlataformaResponseDTO;

public interface PlataformaService {
    
    List<PlataformaResponseDTO> getAll();

    PlataformaResponseDTO findById(Long id);

    PlataformaResponseDTO create(PlataformaDTO plataformaDTO);

    PlataformaResponseDTO update(Long id, PlataformaDTO plataformaDTO);

    void delete(Long id);

    List<PlataformaResponseDTO> findByNome(String nome);

    long count();

}
