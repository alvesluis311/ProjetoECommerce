package topicos1.unitins.projeto.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import topicos1.unitins.projeto.model.produto.Plataforma;

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
