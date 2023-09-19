package br.unitins.ecommerce.dto.plataforma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PlataformaDTO (

    @NotBlank(message = "O campo nome deve ser informado.")
    @Size(max = 60, message = "O nome deve posssuir no máximo 60 caracteres.")
    String nome,

    String descricao,

    Integer anoLancamento,

    @NotNull
    Long idFabricante
)
    {

    
}
