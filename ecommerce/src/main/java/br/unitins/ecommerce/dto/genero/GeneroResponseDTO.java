package br.unitins.ecommerce.dto.genero;

import br.unitins.ecommerce.model.produto.game.Genero;

public record GeneroResponseDTO(
    Long id,
    String nome
) {
    public GeneroResponseDTO(Genero genero) {
        this(
            genero.getId(),
            genero.getNome());
    }

}

