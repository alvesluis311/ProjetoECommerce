package br.unitins.ecommerce.dto.game;

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

