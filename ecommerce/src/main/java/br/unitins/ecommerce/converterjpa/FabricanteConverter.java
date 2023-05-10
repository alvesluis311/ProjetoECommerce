package br.unitins.ecommerce.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import br.unitins.ecommerce.model.produto.plataforma.Fabricante;

@Converter(autoApply = true)
public class FabricanteConverter implements AttributeConverter<Fabricante, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Fabricante fabricante) {
        return fabricante == null ? null : fabricante.getId();
    }

    @Override
    public Fabricante convertToEntityAttribute(Integer id) {
        return Fabricante.valueOf(id);
    }
}
