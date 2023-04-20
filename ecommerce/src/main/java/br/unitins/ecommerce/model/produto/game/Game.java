package br.unitins.ecommerce.model.produto.game;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import br.unitins.ecommerce.model.produto.Plataforma;
import br.unitins.ecommerce.model.produto.Produto;

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

    @Column(nullable = false)
    private TipoGame tipoGame;

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

    public TipoGame getTipoGame() {
        return tipoGame;
    }

    public void setTipoGame(TipoGame tipoGame) {
        this.tipoGame = tipoGame;
    }


}
