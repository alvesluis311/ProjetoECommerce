package topicos1.unitins.projeto.converterjpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import topicos1.unitins.projeto.model.produto.game.Developer;

@Converter(autoApply = true)
public class DeveloperConverter implements AttributeConverter<Developer, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Developer developer) {
        return developer == null ? null : developer.getId();
    }

    @Override
    public Developer convertToEntityAttribute(Integer id) {
        return Developer.valueOf(id);
    }
}
