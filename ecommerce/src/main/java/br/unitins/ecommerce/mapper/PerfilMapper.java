package br.unitins.ecommerce.mapper;

import br.unitins.ecommerce.model.endereco.Municipio;
import br.unitins.ecommerce.model.usuario.Perfil;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface PerfilMapper {

    default Perfil toEnum(Integer id) {
        return Perfil.valueOf(id);
    }

    default Integer toInteger(Perfil perfil) {
        return perfil.getId();
    }

}
