package br.unitins.ecommerce.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import br.unitins.ecommerce.model.produto.game.Developer;

@ApplicationScoped
public class DeveloperRepository implements PanacheRepository<Developer> {
    
    public List<Developer> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }

}
