package br.unitins.ecommerce.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import br.unitins.ecommerce.model.produto.Plataforma;

@ApplicationScoped
public class PlataformaRepository implements PanacheRepository<Plataforma> {
    
    public List<Plataforma> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }

    public List<Plataforma> findByNome(List<Long> idPlataforma) {
        return null;
    }

}
