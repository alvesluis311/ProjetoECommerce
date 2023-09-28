package br.unitins.ecommerce.repository;

import java.util.List;

import br.unitins.ecommerce.model.produto.game.Estoque;
import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class EstoqueRepository implements PanacheRepository<Estoque> {
    
    public List<Estoque> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }

}
