package br.unitins.ecommerce.dto.fabricante;

import br.unitins.ecommerce.model.produto.plataforma.Fabricante;

public record FabricanteResponseDTO(
    Long id,
    String nome
) {
    public FabricanteResponseDTO(Fabricante fabricante) {
        this(
            fabricante.getId(),
            fabricante.getNome());
    }

}

