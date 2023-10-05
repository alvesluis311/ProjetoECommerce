package br.unitins.ecommerce.dto.game;

import jakarta.validation.constraints.Min;

public record EstoqueForm(

    @Min(0)
    Integer qtdDisponivel
) {
    
}
