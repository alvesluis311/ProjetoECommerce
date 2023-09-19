package br.unitins.ecommerce.model.produto.game;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

import br.unitins.ecommerce.model.produto.Produto;
import br.unitins.ecommerce.model.produto.plataforma.Plataforma;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
@PrimaryKeyJoinColumn(name = "id") 
public class Game extends Produto {

    @Column(nullable = false, length = 120)
    private String diretor;

    @Column(nullable = false)
    private Integer anoLancamento;

    @ManyToOne
    @JoinColumn(name = "id_developer")
    private Developer developer;

    @ManyToMany
    @JoinTable(name = "generos_do_jogo",
    joinColumns = @JoinColumn(name = "id_game"),
    inverseJoinColumns = @JoinColumn(name = "id_genero"))
    private List<Genero> generos;

    @ManyToMany
    @JoinTable(name = "plataformas_de_jogo",
    joinColumns = @JoinColumn(name = "id_game"),
    inverseJoinColumns = @JoinColumn(name = "id_plataforma"))
    private List<Plataforma> plataformas;

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public List<Plataforma> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(List<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }


}
