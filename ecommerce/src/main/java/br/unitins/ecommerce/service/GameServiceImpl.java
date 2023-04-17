package topicos1.unitins.projeto.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
// import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import topicos1.unitins.projeto.dto.GameDTO;
import topicos1.unitins.projeto.dto.GameResponseDTO;
import topicos1.unitins.projeto.model.produto.game.Developer;
import topicos1.unitins.projeto.model.produto.game.Game;
// import br.unitins.topicos1.repository.PlataformaRepository;
import topicos1.unitins.projeto.repository.GameRepository;
import topicos1.unitins.projeto.repository.PlataformaRepository;

@ApplicationScoped
public class GameServiceImpl implements GameService {

    @Inject
    GameRepository gameRepository;

    @Inject
    PlataformaRepository plataformaRepository;

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
        entity.setNome(gameDTO.getNome());
        entity.setDescricao(gameDTO.getDescricao());
        entity.setPreco(gameDTO.getPreco());
        entity.setEstoque(gameDTO.getEstoque());
        entity.setDiretor(gameDTO.getDiretor());
        entity.setAnoLancamento(gameDTO.getEstoque());
        entity.setDeveloper(Developer.valueOf(gameDTO.getDeveloper()));
        entity.setPlataforma(plataformaRepository.findByNome(gameDTO.getIdPlataforma()));

        gameRepository.persist(entity);

        return new GameResponseDTO(entity);
    }

    @Override
    @Transactional
    public GameResponseDTO update(Long id, GameDTO gameDTO) throws ConstraintViolationException{
        validar(gameDTO);
   
        Game entity = gameRepository.findById(id);
        entity.setNome(gameDTO.getNome());
        entity.setDescricao(gameDTO.getDescricao());
        entity.setPreco(gameDTO.getPreco());
        entity.setEstoque(gameDTO.getEstoque());
        entity.setDiretor(gameDTO.getDiretor());
        entity.setAnoLancamento(gameDTO.getEstoque());
        entity.setDeveloper(Developer.valueOf(gameDTO.getDeveloper()));
        entity.setPlataforma(plataformaRepository.findByNome(gameDTO.getIdPlataforma()));

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
