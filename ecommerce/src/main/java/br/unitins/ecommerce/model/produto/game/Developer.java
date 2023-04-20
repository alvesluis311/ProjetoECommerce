package br.unitins.ecommerce.model.produto.game;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.unitins.ecommerce.model.DefaultEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
public class Developer extends DefaultEntity {

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false)
    private Integer fundacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getFundacao() {
        return fundacao;
    }

    public void setFundacao(Integer fundacao) {
        this.fundacao = fundacao;
    }


}
