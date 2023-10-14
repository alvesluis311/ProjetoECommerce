package br.unitins.ecommerce.dto.game;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.unitins.ecommerce.dto.Input;


public record GameDTO (

    @NotBlank(message = "O campo nome deve ser informado.")
    @Size(max = 60, message = "O nome deve posssuir no máximo 60 caracteres.")
    String nome,

    String descricao,

    @NotNull
    @Min(1)
    Double preco,

    @NotNull
    Integer estoque,

    String diretor,

    @NotNull
    Integer anoLancamento,

    @NotNull
    Input developer,
    
    @NotEmpty
    List<Input> generos,

    @NotEmpty
    List<Input> plataformas
    )
    {

    }