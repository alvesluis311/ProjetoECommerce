package br.unitins.ecommerce.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import br.unitins.ecommerce.model.produto.game.Genero;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class GeneroRepository implements PanacheRepository<Genero> {
    
    public PanacheQuery<Genero> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%");
    }

    public List<Genero> findById(List<Long> idGenero) {
        return list("id in ?1", idGenero);
    }

}
