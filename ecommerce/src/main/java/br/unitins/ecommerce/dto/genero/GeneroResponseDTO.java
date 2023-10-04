package br.unitins.ecommerce.dto.genero;

import br.unitins.ecommerce.dto.genero.GeneroResponseDTO;
import br.unitins.ecommerce.model.produto.game.Genero;

public record GeneroResponseDTO(
    Long id,
    String nome
) {
    public static GeneroResponseDTO valueOf(Genero genero) {
        return new GeneroResponseDTO(
            genero.getId(), 
            genero.getNome()
        );
    }

}

