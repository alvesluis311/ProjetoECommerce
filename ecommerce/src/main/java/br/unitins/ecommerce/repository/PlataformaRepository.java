package br.unitins.ecommerce.repository;

import br.unitins.ecommerce.model.produto.plataforma.Plataforma;
import br.unitins.ecommerce.model.usuario.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PlataformaRepository implements PanacheRepository<Plataforma> {

    public Optional<Plataforma> buscarPorId(Long id) {
        return find("id = ?1", id)
                .firstResultOptional();
    }

    public List<Plataforma> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%").list();
    }

    public List<Plataforma> findById(List<Long> idPlataforma) {
        return list("id in ?1", idPlataforma);
    }

}
