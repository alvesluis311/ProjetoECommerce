package br.unitins.ecommerce.service.usuario;

import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosClienteForm;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosUsuarioForm;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosUsuarioResponse;
import br.unitins.ecommerce.model.usuario.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario findOrFailEntityById(Long usuarioId);

    UsuarioResponse findOrFailResponseById(Long usuarioId);

    List<UsuarioResponse> findAll();

    void insert(UsuarioForm form);

    void update(Long usuarioId, UsuarioRequest request);

    void merge(Long usuarioId, UsuarioPatch patch);

    void delete(Long usuarioId);

    Usuario findByLoginAndSenha(String login, String senha);

    Usuario findByLogin(String login);

    void updatePassword(String login, SenhaDTO dto);

    DadosUsuarioResponse findPersonalData(String login);

    public void merge(Long usuarioId, DadosClienteForm form);

    public void merge(Long usuarioId, DadosUsuarioForm form);

}