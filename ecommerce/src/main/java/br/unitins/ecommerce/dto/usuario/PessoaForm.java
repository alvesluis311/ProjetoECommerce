package br.unitins.ecommerce.dto.usuario;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PessoaForm {

    @NotBlank
    private String apelido;

    @NotBlank
    private String cpf;

    @NotEmpty
    private List<TelefoneForm> listaTelefone;

    @NotEmpty
    private List<EnderecoForm> listaEndereco;
}
