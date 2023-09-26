package br.unitins.ecommerce.dto.usuario.dadospessoais;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosUsuarioResponse {
    private String nome;

    private String login;

    private String email;
}
