package br.unitins.ecommerce.service.endereco;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.endereco.EnderecoResponse;
import br.unitins.ecommerce.model.endereco.Endereco;

import java.util.List;

public interface EnderecoService {
    Endereco buscarOuFalharEntidadePorId(Long enderecoId);

    EnderecoResponse buscarOuFalharResponseId(Long usuarioId, Long enderecoId);

    List<EnderecoResponse> buscarListaEnderecoResponse(Long usuarioId);

    EnderecoResponse cadastrar(Long usuarioId, EnderecoForm endereco);

    EnderecoResponse atualizar(Long usuarioId, Long enderecoId, EnderecoForm form);

    void cadastrar(Endereco endereco);

    void deletar(Long usuarioId, Long enderecoId);
}
