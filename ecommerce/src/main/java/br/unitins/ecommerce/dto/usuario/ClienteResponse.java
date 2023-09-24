package br.unitins.ecommerce.dto.usuario;

import br.unitins.ecommerce.model.usuario.Sexo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponse extends UsuarioResponse {

    private String nomeCompleto;

    private String cpf;

    private String dataNascimento;

    private Sexo sexo;
}
