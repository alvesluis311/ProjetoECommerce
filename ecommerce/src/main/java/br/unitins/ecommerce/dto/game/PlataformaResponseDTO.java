package br.unitins.ecommerce.dto.game;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.unitins.ecommerce.model.produto.Plataforma;

public record PlataformaResponseDTO (

    Long id,
    String nome,
    String descricao,
    Integer anoLancamento,  

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String fabricante
    )
{
    public PlataformaResponseDTO(Plataforma plataforma) {
        this(plataforma.getId(),
        plataforma.getNome(),
        plataforma.getDescricao(),
        plataforma.getAnoLancamento(),
        plataforma.getFabricante().getLabel());
    }


}
   