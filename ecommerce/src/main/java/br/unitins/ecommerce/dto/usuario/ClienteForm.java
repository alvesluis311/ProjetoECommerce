package br.unitins.ecommerce.dto.usuario;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.model.usuario.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ClienteForm extends UsuarioForm {
    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String cpf;

    @NotNull
    private Sexo sexo;

    @NotNull
    private LocalDate dataNascimento;

    @NotEmpty
    private List<TelefoneForm> listaTelefone;

    private List<EnderecoForm> listaEndereco;
}
