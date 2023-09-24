package br.unitins.ecommerce.dto.usuario;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.model.usuario.Sexo;
import jakarta.validation.constraints.NotBlank;
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

    private List<TelefoneForm> listaTelefone;

    private List<EnderecoForm> listaEndereco;
}
