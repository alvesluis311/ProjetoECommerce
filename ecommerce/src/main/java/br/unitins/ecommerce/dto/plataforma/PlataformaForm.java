package br.unitins.ecommerce.dto.plataforma;

import br.unitins.ecommerce.dto.fabricante.FabricanteInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlataformaForm {
    @NotBlank(message = "O campo nome deve ser informado.")
    @Size(max = 60, message = "O nome deve posssuir no máximo 60 caracteres.")
    private  String nome;

    @NotBlank(message = "O campo nome deve ser informado.")
    private  String descricao;

    @NotBlank(message = "O campo nome deve ser informado.")
    private  String anoLancamento;

    @NotNull
    private  FabricanteInput fabricante;
}
