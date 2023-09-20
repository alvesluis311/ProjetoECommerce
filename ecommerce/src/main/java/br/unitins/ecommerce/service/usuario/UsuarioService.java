package br.unitins.ecommerce.service.usuario;

import java.util.List;

import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.model.usuario.Usuario;

public interface UsuarioService {
    Usuario buscarOuFalharEntidadePorId(Long usuarioId);

    UsuarioResponse buscarOuFalharResponsePorId(Long usuarioId);

    List<UsuarioResponse> buscarListaUsuario();

    UsuarioResponse cadastrar(UsuarioForm form);

    UsuarioResponse atualizar(Long usuarioId, UsuarioRequest request);

    UsuarioResponse atualizarParcial(Long usuarioId, UsuarioPatch patch);

    void deletar(Long usuarioId);

    Usuario buscarPorLoginESenha(String login, String senha);


    Usuario buscarPorLogin(String login);
}