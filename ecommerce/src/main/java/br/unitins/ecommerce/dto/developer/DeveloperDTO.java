package br.unitins.ecommerce.dto.developer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DeveloperDTO (

    @NotBlank(message = "O campo nome deve ser informado.")
    @Size(max = 60, message = "O nome deve posssuir no máximo 60 caracteres.")
    String nome,

    @NotNull
    @Min(0)
    Integer fundacao
    )
    {

    }