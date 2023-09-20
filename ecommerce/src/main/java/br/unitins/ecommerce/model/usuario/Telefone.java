package br.unitins.ecommerce.model.usuario;

import br.unitins.ecommerce.model.DefaultEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Telefone extends DefaultEntity {

    private int codigoArea;

    private String numero;

    private boolean principal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return codigoArea == telefone.codigoArea && Objects.equals(numero, telefone.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoArea, numero);
    }
}
