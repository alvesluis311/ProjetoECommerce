package br.unitins.ecommerce.dto.usuario;


public record UsuarioResponse(
        Long id,
        String nome,
        String login,
        String email,
        String cpf,
        String nomeImagem
) {
}
