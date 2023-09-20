package br.unitins.ecommerce.dto.usuario;


import br.unitins.ecommerce.dto.endereco.EnderecoResponse;

import java.util.List;

public record UsuarioResponse(
        Long id,
        String nome,
        String login,
        String email,
        String cpf,
        String nomeImagem
) {
}
