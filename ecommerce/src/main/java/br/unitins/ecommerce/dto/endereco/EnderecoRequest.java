package br.unitins.ecommerce.dto.endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequest {
    private boolean principal;

    private String logradouro;

    private String bairro;

    private String numero;

    private String complemento;

    private String cep;

    private Long municipio;

}
