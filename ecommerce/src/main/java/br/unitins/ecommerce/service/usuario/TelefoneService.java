package br.unitins.ecommerce.service.usuario;

import br.unitins.ecommerce.dto.usuario.TelefoneForm;
import br.unitins.ecommerce.dto.usuario.TelefoneResponse;
import br.unitins.ecommerce.model.usuario.Telefone;

import java.util.List;

public interface TelefoneService {
    Telefone buscarOuFalharEntidadePorId(Long telefoneId);

    TelefoneResponse buscarOuFalharResponseId(Long usuarioId, Long telefoneId);

    List<TelefoneResponse> buscarListaTelefoneResponse(Long usuarioId);

    List<TelefoneResponse> buscarListaTelefoneResponse(String login);

    TelefoneResponse cadastrar(Long usuarioId, TelefoneForm endereco);

    TelefoneResponse atualizar(Long usuarioId, Long telefoneId, TelefoneForm form);

    void deletar(Long usuarioId, Long telefoneId);
}
