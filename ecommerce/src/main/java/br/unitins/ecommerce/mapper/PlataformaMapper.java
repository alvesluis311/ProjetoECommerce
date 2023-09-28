package br.unitins.ecommerce.mapper;

import br.unitins.ecommerce.dto.plataforma.PlataformaForm;
import br.unitins.ecommerce.dto.plataforma.PlataformaResponse;
import br.unitins.ecommerce.model.produto.plataforma.Plataforma;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface PlataformaMapper {
    Plataforma toEntity(PlataformaForm form);
    PlataformaResponse toResponse(Plataforma plataforma);

    List<PlataformaResponse> toList(List<Plataforma> plataformas);

}
