package br.unitins.ecommerce.dto.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefoneResponse {
    private Long id;

    private int codigoArea;

    private String numero;

    private boolean principal;
}
