package br.unitins.ecommerce.dto.plataforma;

import br.unitins.ecommerce.model.produto.plataforma.Plataforma;

public record PlataformaResponseDTO (

    Long id,
    String nome,
    String descricao,
    Integer anoLancamento,  
    String idFabricante
    )
{
    public PlataformaResponseDTO(Plataforma plataforma) {
        this(plataforma.getId(),
        plataforma.getNome(),
        plataforma.getDescricao(),
        plataforma.getAnoLancamento(),
        plataforma.getFabricante().getNome());
    }


}
   