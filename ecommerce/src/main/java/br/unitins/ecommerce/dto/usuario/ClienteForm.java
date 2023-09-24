package br.unitins.ecommerce.dto.usuario;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.model.usuario.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteForm extends UsuarioForm {
    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String cpf;

    @NotBlank
    private String dataNascimento;

    @NotNull
    private Sexo sexo;

    @NotEmpty
    private List<TelefoneForm> listaTelefone;

    private List<EnderecoForm> listaEndereco;
}
