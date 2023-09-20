package br.unitins.ecommerce.service.game;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

import br.unitins.ecommerce.dto.game.GameDTO;
import br.unitins.ecommerce.dto.game.GameResponseDTO;
import br.unitins.ecommerce.model.produto.game.Game;
import br.unitins.ecommerce.repository.DeveloperRepository;
import br.unitins.ecommerce.repository.GameRepository;
import br.unitins.ecommerce.repository.GeneroRepository;
import br.unitins.ecommerce.repository.PlataformaRepository;
import br.unitins.ecommerce.service.avaliacao.AvaliacaoService;
import br.unitins.ecommerce.service.usuario.UsuarioService;

@ApplicationScoped
public class GameServiceImpl implements GameService {

    @Inject
    GameRepository gameRepository;

    @Inject
    PlataformaRepository plataformaRepository;

    @Inject
    GeneroRepository generoRepository;

    @Inject
    DeveloperRepository developerRepository;

    @Inject
    Validator validator;

    @Inject
    UsuarioService usuarioService;

    @Inject
    AvaliacaoService avaliacaoService;

    @Override
    public List<GameResponseDTO> getAll() {
        List<Game> list = gameRepository.listAll();
        return list.stream().map(GameResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public GameResponseDTO getById(Long id) {
        Game game = gameRepository.findById(id);
        if (game == null)
            throw new NotFoundException("Game não encontrado.");
        return new GameResponseDTO(game);
    }

    @Override
    @Transactional
    public GameResponseDTO create(GameDTO gameDTO) throws ConstraintViolationException {
        validar(gameDTO);

        Game entity = new Game();
        entity.setNome(gameDTO.nome());
        entity.setDescricao(gameDTO.descricao());
        entity.setPreco(gameDTO.preco());
        entity.setEstoque(gameDTO.estoque());
        entity.setDiretor(gameDTO.diretor());
        entity.setAnoLancamento(gameDTO.anoLancamento());
        entity.setDeveloper(developerRepository.findById(gameDTO.idDeveloper()));
        entity.setGeneros(generoRepository
        .findById(gameDTO.idGeneros()));
        entity.setPlataformas(plataformaRepository
        .findById(gameDTO.idPlataformas()));

        gameRepository.persist(entity);

        return new GameResponseDTO(entity);
    }

    @Override
    @Transactional
    public GameResponseDTO update(Long id, GameDTO gameDTO) throws ConstraintViolationException{
        validar(gameDTO);
   
        Game entity = gameRepository.findById(id);
        entity.setNome(gameDTO.nome());
        entity.setDescricao(gameDTO.descricao());
        entity.setPreco(gameDTO.preco());
        entity.setEstoque(gameDTO.estoque());
        entity.setDiretor(gameDTO.diretor());
        entity.setAnoLancamento(gameDTO.anoLancamento());
        entity.setDeveloper(developerRepository.findById(gameDTO.idDeveloper()));
        entity.setGeneros(generoRepository.findById(gameDTO.idGeneros()));
        entity.setPlataformas(plataformaRepository.findById(gameDTO.idPlataformas()));

        return new GameResponseDTO(entity);
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
    public List<GameResponseDTO> findByNome(String nome) {
        List<Game> list = gameRepository.findByNome(nome);
        return list.stream().map(GameResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return gameRepository.count();
    }

    

    @Override
    public List<GameResponseDTO> filterByPrecoMin(Double preco) throws NullPointerException {
        
        List<Game> list = gameRepository.filterByPrecoMinimo(preco);

        if (list == null)
            throw new NullPointerException("Nenhum Café encontrada");

        return list.stream()
                    .map(GameResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public List<GameResponseDTO> filterByPrecoMax(Double preco) {
        
        List<Game> list = gameRepository.filterByPrecoMaximo(preco);

        if (list == null)
            throw new NullPointerException("Nenhum Café encontrada");

        return list.stream()
                    .map(GameResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public List<GameResponseDTO> filterByEntrePreco(Double precoMin, Double precoMax) {
        
        List<Game> list = gameRepository.filterByEntrePreco(precoMin, precoMax);

        if (list == null)
            throw new NullPointerException("Nenhum Café encontrada");

        return list.stream()
                    .map(GameResponseDTO::new)
                    .collect(Collectors.toList());
    }

}
