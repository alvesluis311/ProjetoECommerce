package br.unitins.ecommerce.mapper;

import br.unitins.ecommerce.model.endereco.Municipio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface MunicipioMapper {
    Municipio longToEntity(Long id);

}
