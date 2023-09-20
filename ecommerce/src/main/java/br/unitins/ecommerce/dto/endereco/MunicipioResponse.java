package br.unitins.ecommerce.dto.endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MunicipioResponse {

    private Long id;

    private String nome;

    private EstadoResponse estado;
}
