package br.unitins.ecommerce.dto.endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoResponse {

    private Long id;

    private boolean principal;

    private String logradouro;

    private String bairro;

    private String numero;

    private String complemento;

    private String cep;

    private MunicipioResponse municipio;

}
