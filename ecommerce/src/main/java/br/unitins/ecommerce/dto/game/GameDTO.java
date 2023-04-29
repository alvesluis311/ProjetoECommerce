package br.unitins.ecommerce.dto.game;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    Integer tipoGame,

    @NotNull
    List<Long> idPlataformas
    )
    {

    }