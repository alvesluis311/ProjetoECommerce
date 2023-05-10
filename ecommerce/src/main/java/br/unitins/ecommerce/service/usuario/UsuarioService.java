package br.unitins.ecommerce.service.usuario;

import java.util.List;

import br.unitins.ecommerce.dto.usuario.UsuarioDTO;
import br.unitins.ecommerce.dto.usuario.UsuarioResponseDTO;
import br.unitins.ecommerce.dto.usuario.listadesejo.ListaDesejoDTO;
import br.unitins.ecommerce.dto.usuario.listadesejo.ListaDesejoResponseDTO;
import br.unitins.ecommerce.model.produto.Produto;
import br.unitins.ecommerce.model.usuario.Usuario;

public interface UsuarioService {
    
    // Metodos basicos

    List<UsuarioResponseDTO> getAll();
    
    UsuarioResponseDTO getById(Long id);

    Usuario findByLoginAndSenha(String login, String senha);

    UsuarioResponseDTO insert(UsuarioDTO usuarioDto);

    UsuarioResponseDTO update(Long id, UsuarioDTO usuarioDto);

    void delete(Long id);

    void insertListaDesejo(ListaDesejoDTO listaDto);

    ListaDesejoResponseDTO getListaDesejo(Long id);

    void deleteProdutoFromListaDesejo(Long id, Long idProduto);

    void deleteProdutoFromListaDesejo(Produto produto);

    // Metodos extras

    Long count();

    List<UsuarioResponseDTO> getByNome(String nome);

    Integer countListaDesejo(Long id);
}
