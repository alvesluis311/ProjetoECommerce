package br.unitins.ecommerce.dto.endereco;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoForm {
    @NotBlank(message = "O campo logradouro não pode estar vazio")
    private String logradouro;

    @NotBlank(message = "O campo bairro não pode estar vazio")
    private String bairro;

    @NotBlank(message = "O campo número não pode estar vazio")
    private String numero;

    private String complemento;

    @NotBlank(message = "O campo cep não pode estar vazio")
    private  String cep;

    @Min(1)
    private Long municipio;

    private boolean principal;

}
