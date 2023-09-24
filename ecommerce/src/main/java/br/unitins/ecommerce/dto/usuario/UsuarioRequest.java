package br.unitins.ecommerce.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    @NotBlank
    private String email;

    @NotBlank
    private String nome;

}
