package br.unitins.ecommerce.dto.estado;

import br.unitins.ecommerce.model.endereco.Estado;

public record EstadoResponseDTO (
    Long id,
    String nome,
    String sigla
) {

    public static EstadoResponseDTO valueOf(Estado estado) {
        return new EstadoResponseDTO(
            estado.getId(), 
            estado.getNome(),
            estado.getSigla()
        );
    }

}