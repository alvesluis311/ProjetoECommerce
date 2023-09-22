package br.unitins.ecommerce.model.usuario;

import br.unitins.ecommerce.model.DefaultEntity;
import br.unitins.ecommerce.model.endereco.Endereco;
import br.unitins.ecommerce.model.produto.game.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Usuario extends DefaultEntity {

    private String login;

    private String nome;

    private String email;

    private String senha;

    private String cpf;

    @ElementCollection
    @CollectionTable(name = "perfis", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"))
    @Column(name = "perfil", length = 30)
    private Set<Perfil> listaPerfil;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private List<Telefone> listaTelefone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private List<Endereco> listaEndereco;

    @ManyToMany
    @JoinTable(name = "lista_desejo",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private List<Game> produtos;

    public void addPerfil(Perfil perfil) {
        if (this.listaPerfil == null)
            this.listaPerfil = new HashSet<>();

        this.listaPerfil.add(perfil);
    }

    public void addTelefone(Telefone telefone) {
        if (this.listaTelefone == null) {
            this.listaTelefone = new ArrayList<>();
        }
        if (this.listaTelefone.isEmpty()) {
            listaTelefone = new ArrayList<>();
            telefone.setPrincipal(true);
        } else {
            if (telefone.isPrincipal()) {
                this.listaTelefone.forEach(t -> t.setPrincipal(false));
            }
        }
        listaTelefone.add(telefone);
    }
    public void removeTelefone(Telefone telefone) {
        this.listaTelefone.remove(telefone);

        if (this.getListaTelefone().size() == 1) {
            Telefone t = this.getListaTelefone().get(0);
            t.setPrincipal(true);
        }
    }

    public void addEndereco(Endereco endereco) {
        if (this.listaEndereco == null) {
            this.listaEndereco = new ArrayList<>();
        }
        if (this.listaEndereco.isEmpty()) {
            endereco.setPrincipal(true);
        } else {
            if (endereco.isPrincipal()) {
                this.listaEndereco.forEach(e -> e.setPrincipal(false));
            }
        }
        listaEndereco.add(endereco);
    }

    public void removeEndereco(Endereco endereco) {
        this.listaEndereco.remove(endereco);

        if (this.getListaEndereco().size() == 1) {
            Endereco e = this.getListaEndereco().get(0);
            e.setPrincipal(true);
        }
    }

    public Endereco getEnderecoPrincipal() {
        return this.listaEndereco.stream()
                .filter(Endereco::isPrincipal)
                .findFirst()
                .orElse(null);
    }

    public Telefone getTelefonePrincipal() {
        return this.listaTelefone.stream()
                .filter(Telefone::isPrincipal)
                .findFirst()
                .orElse(null);
    }

}
