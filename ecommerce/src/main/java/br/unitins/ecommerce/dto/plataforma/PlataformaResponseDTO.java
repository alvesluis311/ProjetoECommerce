package br.unitins.ecommerce.dto.plataforma;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.unitins.ecommerce.model.produto.plataforma.Fabricante;
import br.unitins.ecommerce.model.produto.plataforma.Plataforma;

public record PlataformaResponseDTO (

    Long id,
    String nome,
    String descricao,
    Integer anoLancamento,  

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Fabricante fabricante
    )
{
    public PlataformaResponseDTO(Plataforma plataforma) {
        this(plataforma.getId(),
        plataforma.getNome(),
        plataforma.getDescricao(),
        plataforma.getAnoLancamento(),
        plataforma.getFabricante());
    }


}
   