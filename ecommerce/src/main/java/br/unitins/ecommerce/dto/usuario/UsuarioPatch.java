package br.unitins.ecommerce.dto.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioPatch {

    private String login;

    private String senha;

    private String email;

    private String nome;


}
