package br.unitins.ecommerce.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import br.unitins.ecommerce.model.produto.game.Developer;
import br.unitins.ecommerce.model.produto.game.Game;
import br.unitins.ecommerce.model.produto.game.Genero;
import br.unitins.ecommerce.model.produto.plataforma.Plataforma;

@ApplicationScoped
public class GameRepository implements PanacheRepository<Game> {

    public PanacheQuery<Game> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }

    public List<Game> findByPlataforma(Plataforma plataforma) {

        if (plataforma == null)
            return null;

        return find("FROM Game WHERE plataforma = ?1", plataforma).list();
    }

    public List<Game> findByDeveloper(Developer developer) {

        if (developer == null)
            return null;

        return find("FROM Game WHERE developer = ?1", developer).list();
    }

    public List<Game> findByGenero(Genero genero) {
        if (genero == null)
            return null;

        return find("SELECT g FROM Game g JOIN g.generos ge WHERE ge = ?1", genero).list();
    }

    public List<Game> filterByPrecoMaximo(Double preco) {

        if (preco == null)
            return null;

        return find("FROM Game WHERE preco < ?1", preco).list();
    }

    public List<Game> filterByPrecoMinimo(Double preco) {

        if (preco == null)
            return null;

        return find("FROM Game WHERE preco > ?1", preco).list();
    }

    public List<Game> filterByEntrePreco(Double precoMin, Double precoMax) {

        if (precoMin == null || precoMax == null)
            return null;

        return find("FROM Game WHERE preco > ?1 AND preco < ?2", precoMin, precoMax).list();
    }

}
