package topicos1.unitins.projeto.model.produto.game;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import topicos1.unitins.projeto.model.produto.Plataforma;
import topicos1.unitins.projeto.model.produto.Produto;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
@PrimaryKeyJoinColumn(name = "id") 
public class Game extends Produto {

    @Column(nullable = false, length = 120)
    private String diretor;

    @Column(nullable = false)
    private Integer anoLancamento;

    @Column(nullable = false)
    private Developer developer;

    @ManyToMany
    @JoinTable(name = "plataforma_de_jogo",
    joinColumns = @JoinColumn(name = "id_game"),
    inverseJoinColumns = @JoinColumn(name = "id_plataforma"))
    private List<Plataforma> plataforma;

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

    public List<Plataforma> getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(List<Plataforma> plataforma) {
        this.plataforma = plataforma;
    }

}
