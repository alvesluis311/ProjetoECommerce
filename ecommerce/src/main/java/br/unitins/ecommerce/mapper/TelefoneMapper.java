package br.unitins.ecommerce.mapper;


import br.unitins.ecommerce.dto.usuario.TelefoneForm;
import br.unitins.ecommerce.dto.usuario.TelefoneResponse;
import br.unitins.ecommerce.model.usuario.Telefone;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "jakarta")
public abstract class TelefoneMapper {

    public abstract Telefone toEntity(TelefoneForm form);

    public abstract TelefoneResponse toResponse(Telefone telefone);

    public abstract List<Telefone> toEntityList(List<TelefoneForm> forms);

    @AfterMapping
    protected void setTelefonePrincipal(List<TelefoneForm> telefoneDTOs, @MappingTarget List<Telefone> telefones) {
        telefones.forEach(telefone -> {
            if (telefone.isPrincipal()) {
                telefones.forEach(t -> t.setPrincipal(false));
                telefone.setPrincipal(true);
            }
        });
    }
}
