package br.unitins.ecommerce.dto.fabricante;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FabricanteDTO (

    @NotBlank(message = "O campo nome deve ser informado.")
    @Size(max = 60, message = "O nome deve posssuir no máximo 60 caracteres.")
    String nome
    )
    {

    }