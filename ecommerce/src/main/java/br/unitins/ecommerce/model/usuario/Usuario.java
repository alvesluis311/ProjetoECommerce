package br.unitins.ecommerce.model.usuario;

import br.unitins.ecommerce.model.DefaultEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends DefaultEntity {

    private String login;

    private String senha;

    private String email;

    private String nome;

    @ElementCollection
    @CollectionTable(name = "perfis", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"))
    @Column(name = "perfil", length = 30)
    private Set<Perfil> listaPerfil;


    public void addPerfil(Perfil perfil) {
        if (this.listaPerfil == null)
            this.listaPerfil = new HashSet<>();

        this.listaPerfil.add(perfil);
    }

    public void setLevelAcessUser() {
        addPerfil(Perfil.ADMIN);
    }

}
