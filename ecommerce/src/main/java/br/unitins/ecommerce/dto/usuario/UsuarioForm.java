package br.unitins.ecommerce.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UsuarioForm {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    @Email
    private String email;

    @NotBlank
    private String nome;


    private Set<Long> listaPerfil;

}
