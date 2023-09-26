package br.unitins.ecommerce.dto.usuario.dadospessoais;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosClienteResponse extends DadosUsuarioResponse {
    private String nomeCompleto;

    private String cpf;

    private String Sexo;

    private String dataNascimento;

}
