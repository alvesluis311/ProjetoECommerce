package br.unitins.ecommerce.dto.usuario;


public record UsuarioBasicoResponseDTO(
        Long id,
        String login,
        String nome,
        String email
) {


}