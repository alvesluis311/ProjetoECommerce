package br.unitins.ecommerce.dto.plataforma;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlataformaDTO (

    @NotBlank(message = "O campo nome deve ser informado.")
    @Size(max = 60, message = "O nome deve posssuir no máximo 60 caracteres.")
    String nome,

    String descricao,

    Integer anoLancamento,

    @Enumerated(EnumType.STRING)
    Integer fabricante
)
    {

    
}
