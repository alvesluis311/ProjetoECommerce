package br.unitins.ecommerce.dto.usuario.dadospessoais;

import br.unitins.ecommerce.model.usuario.Usuario;

public record DadosPessoaisResponseDTO(
    String login,
    String nome,
    String email,
    String cpf,
    String nomeImagem,
    String sexo
) {
    
}