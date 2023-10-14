package br.unitins.ecommerce.service.game;

import java.util.List;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;
import br.unitins.ecommerce.dto.game.GameResponseDTO;
import br.unitins.ecommerce.mapper.GameMapper;
import br.unitins.ecommerce.dto.game.GameDTO;
import br.unitins.ecommerce.model.produto.game.Developer;
import br.unitins.ecommerce.model.produto.game.Estoque;
import br.unitins.ecommerce.model.produto.game.Game;
import br.unitins.ecommerce.model.produto.game.Genero;
import br.unitins.ecommerce.repository.DeveloperRepository;
import br.unitins.ecommerce.repository.EstoqueRepository;
import br.unitins.ecommerce.repository.GameRepository;
import br.unitins.ecommerce.repository.GeneroRepository;
import br.unitins.ecommerce.repository.PlataformaRepository;
import br.unitins.ecommerce.service.avaliacao.AvaliacaoService;
import br.unitins.ecommerce.service.usuario.UsuarioService;
import br.unitins.ecommerce.utils.BeanUtil;

@ApplicationScoped
public class GameServiceImpl implements GameService {

    @Inject
    GameRepository gameRepository;

    @Inject
    PlataformaRepository plataformaRepository;

    @Inject
    GeneroRepository generoRepository;

    @Inject
    EstoqueRepository estoqueRepository;

    @Inject
    DeveloperRepository developerRepository;

    @Inject
    Validator validator;

    @Inject
    UsuarioService usuarioService;

    @Inject
    AvaliacaoService avaliacaoService;

    @Inject
    GameMapper gameMapper;

    @Override
    public List<GameResponseDTO> getAll(int page, int pageSize) {
        List<Game> list = gameRepository.findAll().page(page, pageSize).list();
        return gameMapper.toListResponse(list);
    }

    @Override
    public GameResponseDTO getById(Long id) {
        Game game = gameRepository.findById(id);
        if (game == null)
            throw new NotFoundException("Game não encontrado.");
        return gameMapper.toResponse(game);
    }

    @Override
    @Transactional
    public GameResponseDTO create(GameDTO gameDTO) throws ConstraintViolationException {
        validar(gameDTO);

        Game entity = gameMapper.toEntity(gameDTO);

        gameRepository.persist(entity);
        System.out.println();
        gameDTO.generos().forEach(g -> System.out.println(g.getId()));

        return gameMapper.toResponse(entity);
    }

    @Override
    @Transactional
    public GameResponseDTO update(Long id, GameDTO gameDTO) throws ConstraintViolationException{
        validar(gameDTO);
   
        Game target = gameRepository.findById(id);
        Game source = gameMapper.toEntity(gameDTO);
        BeanUtil.copyNonNullProperties(source, target);

        return gameMapper.toResponse(target);
    }

    private void validar(GameDTO gameDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<GameDTO>> violations = validator.validate(gameDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void update(Long id, String nomeImagem) {

        Game entity = gameRepository.findById(id);

        if (entity == null)
            throw new NullPointerException("Nenhum game encontrado");

        entity.setNomeImagem(nomeImagem);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Game game = gameRepository.findById(id);

        avaliacaoService.delete(game);


        if (gameRepository.isPersistent(game))
            gameRepository.deleteById(id);
    }

    @Override
    public List<GameResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Game> list = gameRepository.findByNome(nome).page(page, pageSize).list();
        return gameMapper.toListResponse(list);
    }

    @Override
    public List<GameResponseDTO> getByDeveloper(Long id) throws NullPointerException {

        Developer developer = developerRepository.findById(id);

        if (developer == null)
            throw new NullPointerException("Nenhum desenvolvedor encontrado");

        List<Game> list = gameRepository.findByDeveloper(developer);

        return gameMapper.toListResponse(list);
    }

    @Override
    public List<GameResponseDTO> getByGenero(Long id) throws NullPointerException {

        Genero genero = generoRepository.findById(id);

        if (genero == null)
            throw new NullPointerException("Nenhum gênero encontrado");

        List<Game> list = gameRepository.findByGenero(genero);

        return gameMapper.toListResponse(list);
    }

    @Override
    public long count() {
        return gameRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return gameRepository.findByNome(nome).count();
    }

    @Override
    public List<GameResponseDTO> filterByPrecoMin(Double preco) throws NullPointerException {
        
        List<Game> list = gameRepository.filterByPrecoMinimo(preco);

        if (list == null)
            throw new NullPointerException("Nenhum game encontrado");

        return gameMapper.toListResponse(list);
    }

    @Override
    public List<GameResponseDTO> filterByPrecoMax(Double preco) {
        
        List<Game> list = gameRepository.filterByPrecoMaximo(preco);

        if (list == null)
            throw new NullPointerException("Nenhum Café encontrada");

        return gameMapper.toListResponse(list);
    }

    @Override
    public List<GameResponseDTO> filterByEntrePreco(Double precoMin, Double precoMax) {
        
        List<Game> list = gameRepository.filterByEntrePreco(precoMin, precoMax);

        if (list == null)
            throw new NullPointerException("Nenhum Café encontrada");

        return gameMapper.toListResponse(list);
    }

    

}
