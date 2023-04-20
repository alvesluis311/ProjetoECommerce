package br.unitins.ecommerce.dto.developer;

import br.unitins.ecommerce.model.produto.game.Developer;

public record DeveloperResponseDTO(
    Long id,
    String nome,
    Integer fundacao
    
) {
    public DeveloperResponseDTO(Developer developer) {
        this(
            developer.getId(),
            developer.getNome(),
            developer.getFundacao());
    }
}

