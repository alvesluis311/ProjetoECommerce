package br.unitins.ecommerce.mapper;


import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosClienteForm;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosClienteResponse;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosUsuarioForm;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosUsuarioResponse;
import br.unitins.ecommerce.model.usuario.Cliente;
import br.unitins.ecommerce.model.usuario.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "jakarta", uses = {TelefoneMapper.class, MunicipioMapper.class, PerfilMapper.class})
public interface UsuarioMapper {


    Usuario toEntity(UsuarioForm form);

    Usuario toEntity(UsuarioRequest request);

    Usuario toEntity(UsuarioPatch request);

    UsuarioResponse toResponse(Usuario usuario);

    DadosUsuarioResponse dadosPessoaisToResponse(Usuario usuario);

    DadosClienteResponse dadosPessoaisToResponse(Cliente cliente);

    Usuario dadosPessoaisToEntity(DadosUsuarioForm form);

    Cliente dadosPessoaisToEntity(DadosClienteForm form);

    List<UsuarioResponse> toListResponse(List<Usuario> listaUsuario);

}
