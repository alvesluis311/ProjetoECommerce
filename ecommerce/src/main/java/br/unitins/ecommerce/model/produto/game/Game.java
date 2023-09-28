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
import jakarta.persistence.OneToOne;
import br.unitins.ecommerce.model.DefaultEntity;
import br.unitins.ecommerce.model.produto.plataforma.Plataforma;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
public class Game extends DefaultEntity {

    @Column(nullable = false)
    private String nome;

    private String nomeImagem;

    private String descricao;

    @Column(nullable = false)
    private Double preco;

    @OneToOne
    @JoinColumn(name = "id_estoque", unique = true)
    private Estoque estoque;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

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

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }


}
