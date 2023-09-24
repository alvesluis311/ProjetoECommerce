package br.unitins.ecommerce.service.endereco;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.endereco.EnderecoResponse;
import br.unitins.ecommerce.model.endereco.Endereco;

import java.util.List;

public interface EnderecoService {
    Endereco buscarOuFalharEntidadePorId(Long enderecoId);

    EnderecoResponse findOrFailResponseByUsuarioIdAndEnderecoId(Long usuarioId, Long enderecoId);

    List<EnderecoResponse> findAllEnderecosByUsuarioId(Long usuarioId);

    List<EnderecoResponse> findAllEnderecosByUsuarioId(String login);

    EnderecoResponse add(Long usuarioId, EnderecoForm endereco);

    EnderecoResponse update(Long usuarioId, Long enderecoId, EnderecoForm form);

    void delete(Long usuarioId, Long enderecoId);
}
