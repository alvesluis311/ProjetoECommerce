package br.unitins.ecommerce.mapper;


import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.model.usuario.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta", uses = {TelefoneMapper.class, MunicipioMapper.class, PerfilMapper.class})
public interface ClienteMapper {

    Cliente toEntity(SignUserForm form);

    Cliente toEntity(ClienteForm form);

    Cliente toEntity(ClienteUpdate form);

    ClienteResponse toResponse(Cliente cliente);

    List<ClienteResponse> toListResponse(List<Cliente> clientes);

}
