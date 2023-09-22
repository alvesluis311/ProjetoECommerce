package br.unitins.ecommerce.model.compra;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import br.unitins.ecommerce.model.DefaultEntity;
import br.unitins.ecommerce.model.produto.game.Game;

@Entity
public class ItemCompra extends DefaultEntity {

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double precoUnitario;

    @ManyToOne
    @JoinColumn(name = "id_game", nullable = false)
    private Game game;

    public ItemCompra (Game game, Integer quantidade) {

        this.game = game;
        this.precoUnitario = game.getPreco();
        this.quantidade = quantidade;
    }

    public ItemCompra() {
        
    }

    public boolean contains(Game game) {

        if (this.game.getId() == game.getId())
            return true;
        
        else
            return false;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void updateQuantidade(Integer quantidade) {

        this.quantidade += quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double preco) {
        this.precoUnitario = preco;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

}