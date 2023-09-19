package br.unitins.ecommerce.dto.game;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record GameDTO (

    @NotBlank(message = "O campo nome deve ser informado.")
    @Size(max = 60, message = "O nome deve posssuir no máximo 60 caracteres.")
    String nome,

    String descricao,

    @NotNull
    @Min(1)
    Double preco,

    @NotNull
    @Min(0)
    Integer estoque,

    String diretor,

    @NotNull
    Integer anoLancamento,

    @NotNull
    Long idDeveloper,
    
    @NotNull
    List<Long> idGeneros,

    @NotNull
    List<Long> idPlataformas
    )
    {

    }