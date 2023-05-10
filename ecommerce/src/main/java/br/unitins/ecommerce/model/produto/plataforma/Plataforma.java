package br.unitins.ecommerce.model.produto.plataforma;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import br.unitins.ecommerce.model.DefaultEntity;

@Entity
public class Plataforma extends DefaultEntity {

   
    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    //@Column(nullable = false)
    private Integer anoLancamento;

    @Column(nullable = false)
    private Fabricante fabricante;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

}

