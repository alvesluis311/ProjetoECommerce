package br.unitins.ecommerce.dto.game;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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