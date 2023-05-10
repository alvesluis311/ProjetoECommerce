package br.unitins.ecommerce.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import br.unitins.ecommerce.model.usuario.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {
    
    public List<Usuario> findByNome (String nome) {

        if (nome == null)
            return null;

        return find("FROM Usuario WHERE UPPER(UNACCENT(nome)) LIKE UNACCENT(?1)", "%" + nome.toUpperCase() + "%").list();
    }
    public Usuario findByLoginAndSenha(String login, String senha){
        if (login == null || senha == null)
            return null;

        return find("login = ?1 AND senha = ?2 ", login, senha).firstResult();
    }
}
