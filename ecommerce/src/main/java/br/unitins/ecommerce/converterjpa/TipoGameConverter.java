package br.unitins.ecommerce.converterjpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.ecommerce.model.produto.game.TipoGame;

@Converter(autoApply = true)
public class TipoGameConverter implements AttributeConverter<TipoGame, Integer>{

    @Override
    public Integer convertToDatabaseColumn(TipoGame tipoGame) {
        return tipoGame == null ? null : tipoGame.getId();
    }

    @Override
    public TipoGame convertToEntityAttribute(Integer id) {
        return TipoGame.valueOf(id);
    }
}
