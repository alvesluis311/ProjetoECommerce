package br.unitins.ecommerce.service.usuario;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosPessoaisResponse;
import br.unitins.ecommerce.model.usuario.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario findOrFailEntityById(Long usuarioId);

    UsuarioResponse findOrFailResponseById(Long usuarioId);

    List<UsuarioResponse> findAllUsers();

    void add(UsuarioForm form);

    void addProfiles(Long id , List<Integer> perfis);

    UsuarioResponse update(Long usuarioId, UsuarioRequest request);

    UsuarioResponse merge(Long usuarioId, UsuarioPatch patch);

    void delete(Long usuarioId);

    Usuario findByLoginAndSenha(String login, String senha);


    Usuario findByLogin(String login);

    DadosPessoaisResponse buscarDadosPessoais(String login);

    void updatePassword(String login, SenhaDTO dto);

    void addAdresses(String login, List<EnderecoForm> forms);
}