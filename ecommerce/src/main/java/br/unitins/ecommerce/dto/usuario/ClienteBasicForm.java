package br.unitins.ecommerce.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteBasicForm extends UsuarioForm {

    @NotBlank
    private String nomeCompleto;
    @NotBlank
    private String cpf;
    @NotNull
    private TelefoneForm telefone;

}
