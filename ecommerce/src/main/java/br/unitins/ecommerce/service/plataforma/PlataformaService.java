package br.unitins.ecommerce.service.plataforma;

import java.util.List;

import br.unitins.ecommerce.dto.plataforma.PlataformaForm;
import br.unitins.ecommerce.dto.plataforma.PlataformaResponse;
import br.unitins.ecommerce.model.produto.plataforma.Plataforma;

public interface PlataformaService {

    // Metodos basicos

    List<PlataformaResponse> getAll();

    Plataforma findOrFailEntityById(Long usuarioId);

    PlataformaResponse finResponseById(Long id);

    void create(PlataformaForm form);

    void update(Long id, PlataformaForm form);

    void delete(Long id);

    long count();

    // Metodos extras

    List<PlataformaResponse> findByNome(String nome);

}
