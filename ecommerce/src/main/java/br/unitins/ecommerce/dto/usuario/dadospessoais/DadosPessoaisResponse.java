package br.unitins.ecommerce.dto.usuario.dadospessoais;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosPessoaisResponse {

    private String nome;

    private String nomeCompleto;

    private String email;

    private String cpf;

    private String dataNascimento;

    private String sexo;
}
