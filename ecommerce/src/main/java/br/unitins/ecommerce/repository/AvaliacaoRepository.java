package br.unitins.ecommerce.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import br.unitins.ecommerce.model.produto.game.Game;
import br.unitins.ecommerce.model.produto.avaliacao.Avaliacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AvaliacaoRepository implements PanacheRepository<Avaliacao> {
    
    public List<Avaliacao> findByYear(Integer year) {

        if (year == null)
            return null;

        return find("FROM Avaliacao WHERE YEAR(data) = ?1", year).list();
    }

    public List<Avaliacao> findByGame(Game game) {

        if (game == null)
            return null;

        return find("FROM Avaliacao WHERE game = ?1", game).list();
    }
}
