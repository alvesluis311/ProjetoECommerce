package topicos1.unitins.projeto.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import topicos1.unitins.projeto.model.produto.game.Game;

@ApplicationScoped
public class GameRepository implements PanacheRepository<Game> {
    
    public List<Game> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }

}
