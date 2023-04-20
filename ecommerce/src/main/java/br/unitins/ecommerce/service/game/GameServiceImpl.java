package br.unitins.ecommerce.service.game;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import br.unitins.ecommerce.dto.game.GameDTO;
import br.unitins.ecommerce.dto.game.GameResponseDTO;
import br.unitins.ecommerce.model.produto.game.Game;
import br.unitins.ecommerce.model.produto.game.TipoGame;
import br.unitins.ecommerce.repository.DeveloperRepository;
import br.unitins.ecommerce.repository.GameRepository;
import br.unitins.ecommerce.repository.PlataformaRepository;

@ApplicationScoped
public class GameServiceImpl implements GameService {

    @Inject
    GameRepository gameRepository;

    @Inject
    PlataformaRepository plataformaRepository;

    @Inject
    DeveloperRepository developerRepository;

    @Inject
    Validator validator;

    @Override
    public List<GameResponseDTO> getAll() {
        List<Game> list = gameRepository.listAll();
        return list.stream().map(GameResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public GameResponseDTO findById(Long id) {
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
        entity.setTipoGame(TipoGame.valueOf(gameDTO.tipoGame()));
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
        entity.setTipoGame(TipoGame.valueOf(gameDTO.tipoGame()));
        entity.setPlataformas(plataformaRepository
        .findById(gameDTO.idPlataformas()));

        return new GameResponseDTO(entity);
    }

    private void validar(GameDTO gameDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<GameDTO>> violations = validator.validate(gameDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Game game = gameRepository.findById(id);

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

}
