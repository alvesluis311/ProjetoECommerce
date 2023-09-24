package br.unitins.ecommerce.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.unitins.ecommerce.model.usuario.Cliente;
import jakarta.enterprise.context.ApplicationScoped;

import br.unitins.ecommerce.model.usuario.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public List<Usuario> findByNome(String nomePessoaFisica) {

        if (nomePessoaFisica == null)
            return null;

        return find("FROM Usuario WHERE UNACCENT(UPPER(pessoaFisica.nome)) LIKE UNACCENT(?1)", "%" + nomePessoaFisica.toUpperCase() + "%").list();
    }

    public Usuario findByLoginAndSenha(String login, String senha) {

        if (login == null || senha == null)
            return null;

        return find("login = ?1 AND senha = ?2 ", login, senha).firstResult();
    }


    public List<Cliente> findAllClientes() {
        return list("FROM Usuario")
                .stream()
                .filter(u -> u instanceof Cliente)
                .map(u -> (Cliente) u)
                .collect(Collectors.toList());
    }



    public Optional<Usuario> findByLogin(String login) {
        return find("login = ?1 ", login)
                .firstResultOptional();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return find("id = ?1", id)
                .firstResultOptional();
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return find("email = ?1", email)
                .firstResultOptional();
    }

    public Optional<Usuario> buscarPorCpf(String cpf) {
        return find("cpf = ?1", cpf)
                .firstResultOptional();
    }
}
