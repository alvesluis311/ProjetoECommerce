package br.unitins.ecommerce.mapper;


import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosPessoaisResponse;
import br.unitins.ecommerce.model.usuario.Cliente;
import br.unitins.ecommerce.model.usuario.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import org.mapstruct.SubclassMappings;

import java.util.List;

@Mapper(componentModel = "jakarta", uses = {TelefoneMapper.class, MunicipioMapper.class, PerfilMapper.class})
public interface  UsuarioMapper {

    @SubclassMappings({
            @SubclassMapping(source = ClienteBasicForm.class, target = Cliente.class),
            @SubclassMapping(source = ClienteForm.class, target = Cliente.class),
    })
    Usuario toEntity(UsuarioForm form);
    Usuario toEntity(UsuarioBasicoForm form);
    Usuario toEntity(UsuarioRequest request);
    Usuario toEntity(UsuarioPatch request);


    UsuarioResponse toResponse(Usuario usuario);

    DadosPessoaisResponse dadosPessoaisToResponse(Usuario usuario);
    DadosPessoaisResponse dadosPessoaisToResponse(Cliente cliente);

    List<UsuarioResponse> toListResponse(List<Usuario> listaUsuario);


}
