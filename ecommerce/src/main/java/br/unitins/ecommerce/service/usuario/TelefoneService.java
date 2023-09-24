package br.unitins.ecommerce.service.usuario;

import br.unitins.ecommerce.dto.usuario.TelefoneForm;
import br.unitins.ecommerce.dto.usuario.TelefoneResponse;
import br.unitins.ecommerce.model.usuario.Telefone;

import java.util.List;

public interface TelefoneService {
    Telefone findOrFailEntityById(Long telefoneId);

    TelefoneResponse findOrFailResponseById(Long usuarioId, Long telefoneId);

    List<TelefoneResponse> findTelefonesByUsuarioId(Long usuarioId);

    void add(Long usuarioId, TelefoneForm form);

    void atualizar(Long usuarioId, Long telefoneId, TelefoneForm form);

    void deletar(Long usuarioId, Long telefoneId);
}
