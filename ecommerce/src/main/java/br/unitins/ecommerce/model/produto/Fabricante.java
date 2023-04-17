package br.unitins.ecommerce.model.produto;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Fabricante {

    SONY(1, "Sony"),
    MICROSOFT(2, "Microsoft"),
    NINTENDO(3, "Nintendo");

    private int id;
    private String label;

    private Fabricante(int id, String label){
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }
    public String getLabel() {
        return label;
    }

    public static Fabricante valueOf (Integer id){
        if (id == null){
            return null;
        }

        for (Fabricante fabricante : Fabricante.values()) {
            if(id.equals(fabricante.getId()))
            return fabricante;
        }
        throw new IllegalArgumentException("ID inválido:" + id);
    }

}

