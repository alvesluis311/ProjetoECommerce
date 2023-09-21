package br.unitins.ecommerce.dto.usuario.dadospessoais;


public record DadosPessoaisResponseDTO(
    String login,
    String nome,
    String email,
    String cpf,
    String nomeImagem,
    String sexo
) {
    
}