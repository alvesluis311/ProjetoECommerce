package br.unitins.ecommerce.dto.usuario.dadospessoais;

import br.unitins.ecommerce.model.usuario.Sexo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DadosClienteForm extends DadosUsuarioForm {

    private Sexo sexo;

    private String cpf;

    private String nomeCompleto;

    private LocalDate dataNascimento;
}
