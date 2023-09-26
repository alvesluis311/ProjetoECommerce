package br.unitins.ecommerce.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteUpdate extends UsuarioForm {
    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String cpf;
}
