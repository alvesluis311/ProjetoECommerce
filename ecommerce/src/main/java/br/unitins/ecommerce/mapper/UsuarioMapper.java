package br.unitins.ecommerce.mapper;


import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.model.usuario.Perfil;
import br.unitins.ecommerce.model.usuario.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta", uses = {TelefoneMapper.class, MunicipioMapper.class, PerfilMapper.class})
public interface  UsuarioMapper {

    Usuario toEntity(UsuarioForm form);
    Usuario toEntity(UsuarioBasicoForm form);
    Usuario toEntity(UsuarioRequest request);
    Usuario toEntity(UsuarioPatch request);
    UsuarioResponse toResponse(Usuario usuario);
    List<UsuarioResponse> toListResponse(List<Usuario> listaUsuario);


}
