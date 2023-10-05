package br.unitins.ecommerce.dto.game;

import br.unitins.ecommerce.dto.developer.DeveloperInfo;
import br.unitins.ecommerce.dto.genero.GeneroInfo;
import br.unitins.ecommerce.dto.plataforma.PlataformaInfo;

import java.util.List;

public record GameResponseDTO(
    Long id,
    String nome,
    String descricao,
    String nomeImagem,
    Double preco,
    Integer estoque,
    String diretor,
    Integer anoLancamento,
    DeveloperInfo developer,
    List<GeneroInfo> generos,
    List<PlataformaInfo> plataformas
) {
    
}
