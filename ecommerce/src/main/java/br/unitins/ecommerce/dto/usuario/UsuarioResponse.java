package br.unitins.ecommerce.dto.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {
    private Long id;
    private String nome;
    private String login;
    private String email;
}
