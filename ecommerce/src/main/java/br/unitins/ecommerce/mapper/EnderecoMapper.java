package br.unitins.ecommerce.mapper;


import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.endereco.EnderecoRequest;
import br.unitins.ecommerce.dto.endereco.EnderecoResponse;
import br.unitins.ecommerce.model.endereco.Endereco;
import br.unitins.ecommerce.model.usuario.Usuario;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "jakarta", uses = MunicipioMapper.class)
public abstract class EnderecoMapper {
    public abstract Endereco toEntity(EnderecoForm form);

    public abstract Endereco toEntity(EnderecoRequest request);

    public abstract EnderecoResponse toResponse(Endereco endereco);

    public abstract List<Endereco> toEntityList(List<EnderecoForm> forms);

    public abstract List<EnderecoResponse> toResposeList(List<Endereco> listaEndereco);

    @AfterMapping
    protected void setEnderecoPrincipal(List<EnderecoForm> forms, @MappingTarget List<Endereco> listaEndereco) {
        listaEndereco.forEach(endereco -> {
            if (endereco.isPrincipal()) {
                listaEndereco.forEach(e -> e.setPrincipal(false));
                endereco.setPrincipal(true);
            }
        });
    }
}
