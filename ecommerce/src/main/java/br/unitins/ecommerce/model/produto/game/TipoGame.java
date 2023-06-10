package br.unitins.ecommerce.model.produto.game;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoGame {

    TRIPLE_A(1, "Triple_A"),
    INDIE(2, "Indie/Independente");

    private int id;
    private String label;

    TipoGame(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoGame valueOf(Integer id) throws IllegalArgumentException {
        if (id == null) {
            return null;
        }

        for (TipoGame tipogame : TipoGame.values()) {
            if (id.equals(tipogame.getId())) {
                return tipogame;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

}

