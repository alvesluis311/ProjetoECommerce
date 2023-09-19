package br.unitins.ecommerce.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import br.unitins.ecommerce.model.produto.plataforma.Fabricante;

@ApplicationScoped
public class FabricanteRepository implements PanacheRepository<Fabricante> {
    
    public List<Fabricante> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }

}
