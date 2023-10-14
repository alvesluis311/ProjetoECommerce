package br.unitins.ecommerce.service.compra;

import java.time.LocalDate;
import java.util.List;

import br.unitins.ecommerce.dto.compra.CartaoCreditoDTO;
import br.unitins.ecommerce.dto.compra.CompraResponseDTO;
import br.unitins.ecommerce.dto.compra.ItemCompraDTO;
import br.unitins.ecommerce.model.compra.Compra;
import br.unitins.ecommerce.model.compra.ItemCompra;
import br.unitins.ecommerce.model.pagamento.BandeiraCartao;
import br.unitins.ecommerce.model.pagamento.BoletoBancario;
import br.unitins.ecommerce.model.pagamento.CartaoCredito;
import br.unitins.ecommerce.model.pagamento.Pix;
import br.unitins.ecommerce.model.produto.game.Game;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.repository.BoletoBancarioRepository;
import br.unitins.ecommerce.repository.CartaoCreditoRepository;
import br.unitins.ecommerce.repository.CompraRepository;
import br.unitins.ecommerce.repository.ItemCompraRepository;
import br.unitins.ecommerce.repository.PixRepository;
import br.unitins.ecommerce.repository.UsuarioRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CompraImplService implements CompraService {

    @Inject
    CompraRepository compraRepository;

    @Inject
    ItemCompraRepository itemCompraRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PanacheRepository<? extends Game> gameRepository;

    @Inject
    BoletoBancarioRepository boletoBancarioRepository;

    @Inject
    PixRepository pixRepository;

    @Inject
    CartaoCreditoRepository cartaoCreditoRepository;

    @Override
    public List<CompraResponseDTO> getAll(Long idUsuario) {

        List<Compra> list = compraRepository.findByUsuarioWhereIsFinished(usuarioRepository.findById(idUsuario));

        if (list == null)
            throw new NullPointerException("compra não encontrada");

        return list.stream().map(CompraResponseDTO::new).toList();
    }

    @Override
    public CompraResponseDTO getCompraEmAndamento(Long idUsuario) {

        Compra compra = compraRepository.findByUsuarioWhereIsNotFinished(usuarioRepository.findById(idUsuario));

        if (compra == null)
            throw new NullPointerException("Nenhuma compra em andamento");

        return new CompraResponseDTO(compra);
    }

    @Override
    @Transactional
    public void insertItemIntoCompra(Long idUsuario, ItemCompraDTO itemCompraDTO) throws NullPointerException {

        Game game = validar(itemCompraDTO);

        Compra compra = validar(idUsuario);

        Integer indice = validar(game, compra.getItemCompra());

        ItemCompra itemCompra;

        if (indice != null) {

            itemCompra = compra.getItemCompra().get(indice);

            itemCompra.updateQuantidade(itemCompraDTO.quantidade());

            compra.plusTotalCompra(itemCompra.getPrecoUnitario() * itemCompraDTO.quantidade());
        }

        else {

            itemCompra = new ItemCompra(game, itemCompraDTO.quantidade());

            itemCompraRepository.persist(itemCompra);

            compra.setItemCompra(itemCompra);

            compra.plusTotalCompra(itemCompra.getPrecoUnitario() * itemCompra.getQuantidade());
        }
    }

    @Override
    @Transactional
    public void removeItemCompra(Long idUsuario, Long idGame) {

        Compra compra = compraRepository.findByUsuarioWhereIsNotFinished(usuarioRepository.findById(idUsuario));

        if (compra == null)
            throw new NullPointerException("Não há nenhuma compra em andamento");

        ItemCompra itemCompra = itemCompraRepository.findByGame(gameRepository.findById(idGame));

        compra.minusTotalCompra(itemCompra.getPrecoUnitario() * itemCompra.getQuantidade());

        compra.getItemCompra().remove(itemCompra);
    }

    @Override
    @Transactional
    public void cancelarCompra(Long idUsuario) {

        Compra compra = compraRepository.findByUsuarioWhereIsNotFinished(usuarioRepository.findById(idUsuario));

        if (compra == null)
            throw new NullPointerException("Não há nenhuma compra em andamento");

        for (ItemCompra itemCompra : compra.getItemCompra()) {

            itemCompraRepository.delete(itemCompra);
        }

        compraRepository.delete(compra);
    }

    @Override
    public void finishCompra(Long idCompra) throws NullPointerException {

        // Compra compra = compraRepository.findById(idCompra);

        // compra.setDataCompra(LocalDate.now());

        // for (ItemCompra itemCompra : compra.getItemCompra()) {

        // if (itemCompra.getGame().getEstoque().getQtdDisponivel() <
        // itemCompra.getQuantidade())
        // throw new NullPointerException("quantidade em estoque insuficiente para a
        // quantidade requisitada. Não é possível finalizar a compra");

        // else
        // itemCompra.getGame().getEstoque().minusEstoque(itemCompra.getQuantidade());
        // }

        // compra.setIfConcluida(true);
    }

    @Override
    @Transactional
    public void efetuarPagamentoBoleto(Long idUsuario) throws NullPointerException {

        Usuario usuario = usuarioRepository.findById(idUsuario);

        Compra compra = validar(usuario);

        if (compra.getPagamento() == null)
            throw new NullPointerException("Não foi efetuado nenhum pagamento");

        finishCompra(compra.getId());
    }

    @Override
    @Transactional
    public void efetuarPagamentoPix(Long idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario);

        Compra compra = validar(usuario);

        if (compra.getPagamento() == null)
            throw new NullPointerException("Não foi efetuado nenhum pagamento");

        finishCompra(compra.getId());
    }

    @Override
    @Transactional
    public void efetuarPagamentoCartaoCredito(Long idUsuario, CartaoCreditoDTO cartaoCreditoDTO) {

        Usuario usuario = usuarioRepository.findById(idUsuario);

        Compra compra = validar(usuario);

        if (compra.getPagamento() == null)
            throw new NullPointerException("Não foi efetuado nenhum pagamento");

        finishCompra(compra.getId());
    }

    private Compra validar(Usuario usuario) throws NullPointerException {

        Compra compra = compraRepository.findByUsuarioWhereIsNotFinished(usuario);

        if (compra == null)
            throw new NullPointerException("Não há nenhuma compra em andamento");

        if (compra.getItemCompra().size() == 0)
            throw new NullPointerException("Não há nenhum item dentro do carrinho");

        return compra;
    }

    private Integer validar(Game game, List<ItemCompra> listaItens) {

        for (int i = 0; i < listaItens.size(); i++) {

            if (listaItens.get(i).contains(game))
                return i;
        }

        return null;
    }

    private Compra validar(Long idUsuario) {

        Compra compra = compraRepository.findByUsuarioWhereIsNotFinished(usuarioRepository.findById(idUsuario));

        if (compra == null) {

            Compra newCompra = new Compra(usuarioRepository.findById(idUsuario));

            compraRepository.persist(newCompra);

            return newCompra;
        }

        else
            return compra;
    }

    private Game validar(ItemCompraDTO itemCompraDTO) throws NullPointerException {

        Game game = gameRepository.findById(itemCompraDTO.idGame());
        return game;
        // if (game.getEstoque().getQtdDisponivel() > itemCompraDTO.quantidade())
        // return game;

        // else
        // throw new NullPointerException("quantidade em estoque insuficiente para a
        // quantidade requisitada");
    }
}