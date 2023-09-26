package br.unitins.ecommerce.service.usuario;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.endereco.EnderecoResponse;
import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.model.usuario.Cliente;

import java.util.List;

public interface ClienteService {

    List<ClienteResponse> findAll();

    ClienteResponse findResponseById(Long id);

    Cliente findByLogin(String login);

    String signIn(SignUserForm form);

    void insert(ClienteForm form);

    void update(Long id, ClienteUpdate update);

    void delete(Long id);

    void insertEndereco(Long id, EnderecoForm form);

    EnderecoResponse findEndereco(Long id, Long enderecoId);

    List<EnderecoResponse> findAllEnderecos(Long id);

    void updateEndereco(Long id, Long enderecoId, EnderecoForm form);

    void deleteEndereco(Long id, Long enderecoId);

    TelefoneResponse findTelefone(Long id, Long telefoneId);

    List<TelefoneResponse> findAllTelefones(Long id);

    void insertTelefone(Long id, TelefoneForm form);

    void updateTelefone(Long id, Long telefoneId, TelefoneForm form);

    void deleteTelefone(Long id, Long telefoneId);


}
