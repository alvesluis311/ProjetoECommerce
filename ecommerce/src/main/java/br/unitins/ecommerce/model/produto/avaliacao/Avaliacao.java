package br.unitins.ecommerce.model.produto.avaliacao;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import br.unitins.ecommerce.model.DefaultEntity;
import br.unitins.ecommerce.model.produto.game.Game;
import br.unitins.ecommerce.model.usuario.Usuario;

@Entity
public class Avaliacao extends DefaultEntity {

    private String comentario;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private Estrela estrela;

    @ManyToOne
    @JoinColumn(name = "id_game", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Estrela getEstrela() {
        return estrela;
    }

    public void setEstrela(Estrela estrela) {
        this.estrela = estrela;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
