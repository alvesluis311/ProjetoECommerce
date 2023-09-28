package br.unitins.ecommerce.dto.plataforma;

import br.unitins.ecommerce.dto.fabricante.FabricanteResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlataformaResponse {

    private Long id;

    private String nome;

    private String descricao;

    private String anoLancamento;

    private FabricanteResponse fabricante;
}
