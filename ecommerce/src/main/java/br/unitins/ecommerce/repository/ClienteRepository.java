package br.unitins.ecommerce.repository;

import br.unitins.ecommerce.model.usuario.Cliente;
import br.unitins.ecommerce.model.usuario.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public Optional<Cliente> findEntityById(Long id) {
        return find("id = ?1 ", id)
                .firstResultOptional();
    }


    public Optional<Cliente> findByLogin(String login) {
        return find("login = ?1 ", login)
                .firstResultOptional();
    }

    public Optional<Cliente> findByEmail(String email) {
        return find("email = ?1 ", email)
                .firstResultOptional();
    }

    public Optional<Cliente> findByCpf(String cpf) {
        return find("cpf = ?1 ", cpf)
                .firstResultOptional();
    }
}
