package br.unitins.ecommerce.dto.plataforma;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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