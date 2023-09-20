package br.unitins.ecommerce.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelefoneForm {
    @NotNull
    private int codigoArea;

    @NotBlank
    private String numero;

    private boolean principal;
}