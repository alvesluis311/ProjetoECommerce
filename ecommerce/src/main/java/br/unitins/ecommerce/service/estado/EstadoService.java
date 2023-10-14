package br.unitins.ecommerce.service.estado;

import java.util.List;

import br.unitins.ecommerce.dto.estado.EstadoDTO;
import br.unitins.ecommerce.dto.estado.EstadoResponseDTO;

public interface EstadoService {

    List<EstadoResponseDTO> getAll(int page, int pageSize);

    EstadoResponseDTO findById(Long id);

    EstadoResponseDTO create(EstadoDTO dto);

    EstadoResponseDTO update(Long id, EstadoDTO dto);

    void delete(Long id);

    // recursos extras

    List<EstadoResponseDTO> getByNome(String nome, int page, int pageSize);

    Long count();

    long countByNome(String nome);
}
