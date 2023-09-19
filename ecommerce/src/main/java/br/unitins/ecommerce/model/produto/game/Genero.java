package br.unitins.ecommerce.model.produto.game;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import br.unitins.ecommerce.model.DefaultEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
public class Genero extends DefaultEntity {

    @Column(nullable = false, length = 120)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
  
}
