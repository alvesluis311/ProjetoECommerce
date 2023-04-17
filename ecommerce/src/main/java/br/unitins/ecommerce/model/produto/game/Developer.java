package br.unitins.ecommerce.model.produto.game;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Developer {
    FROM_SOFTWARE(1, "From Software"),
    EA(2, "Eletronic Arts"),
    TEAM_CHERRY(3, "Team Cherry"),
    ANNAPURNA(4, "Annapurna Interactive"),
    UBISOFT(5, "Ubisoft"),
    NAUGHTY_DOG(6, "Naughty Dog"),
    SUPERMASSIVE_GAMES(7, "Supermassive Games"),
    SANTA_MONICA_STUDIO(8, "Santa Monica Studio"),
    CAPCOM(9, "Capcom"),
    SUPERGIANT(10, "Supergiant Games"),
    KOJIMA_PRODUCTIONS(11, "Kojima Productions"),
    NOMADA_STUDIO(12, "Nomada Studio");

    private int id;
    private String label;

    private Developer(int id, String label){
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }
    public String getLabel() {
        return label;
    }

    public static Developer valueOf (Integer id){
        if (id == null){
            return null;
        }

        for (Developer developer : Developer.values()) {
            if(id.equals(developer.getId()))
            return developer;
        }
        throw new IllegalArgumentException("ID inválido:" + id);
    }

}
