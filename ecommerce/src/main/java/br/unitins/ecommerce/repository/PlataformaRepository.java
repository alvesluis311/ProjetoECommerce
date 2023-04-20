package br.unitins.ecommerce.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.unitins.ecommerce.model.produto.plataforma.Plataforma;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PlataformaRepository implements PanacheRepository<Plataforma> {
    
    public List<Plataforma> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }

    public List<Plataforma> findById(List<Long> idPlataforma) {
        return list("id in ?1", idPlataforma);
    }

}
