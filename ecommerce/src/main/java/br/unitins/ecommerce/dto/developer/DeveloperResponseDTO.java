package br.unitins.ecommerce.dto.developer;

import br.unitins.ecommerce.model.produto.game.Developer;

public record DeveloperResponseDTO(
    Long id,
    String nome,
    Integer fundacao
    
) {
    public static DeveloperResponseDTO valueOf(Developer developer) {
        return new DeveloperResponseDTO(
            developer.getId(),
            developer.getNome(),
            developer.getFundacao());
    }
}

