package br.unitins.ecommerce.repository;

import br.unitins.ecommerce.model.compra.ItemCompra;
import br.unitins.ecommerce.model.produto.game.Game;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemCompraRepository implements PanacheRepository<ItemCompra> {
    
    public ItemCompra findByGame (Game game) {

        if (game == null)
            return null;

        return find("FROM ItemCompra WHERE game = ?1", game).firstResult();
    }
}