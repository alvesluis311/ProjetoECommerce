package br.unitins.ecommerce.service.genero;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.ecommerce.dto.genero.GeneroResponseDTO;
import br.unitins.ecommerce.dto.genero.GeneroDTO;
import br.unitins.ecommerce.model.produto.game.Genero;
import br.unitins.ecommerce.repository.GeneroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GeneroServiceImpl implements GeneroService {

    @Inject
    GeneroRepository generoRepository;

    @Inject
    Validator validator;

    @Override
    public List<GeneroResponseDTO> getAll() {
        List<Genero> list = generoRepository.listAll();
        return list.stream().map(GeneroResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public GeneroResponseDTO getById(Long id) {
        Genero genero = generoRepository.findById(id);
        if (genero == null)
            throw new NotFoundException("Genero não encontrado.");
        return new GeneroResponseDTO(genero);
    }

    @Override
    @Transactional
    public GeneroResponseDTO create(GeneroDTO generoDTO) throws ConstraintViolationException {
        validar(generoDTO);

        Genero entity = new Genero();
        entity.setNome(generoDTO.nome());

        generoRepository.persist(entity);

        return new GeneroResponseDTO(entity);
    }

    @Override
    @Transactional
    public GeneroResponseDTO update(Long id, GeneroDTO generoDTO) throws ConstraintViolationException{
        validar(generoDTO);
   
        Genero entity = generoRepository.findById(id);
        entity.setNome(generoDTO.nome());

        return new GeneroResponseDTO(entity);
    }

    private void validar(GeneroDTO generoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<GeneroDTO>> violations = validator.validate(generoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Genero genero = generoRepository.findById(id);

        if (generoRepository.isPersistent(genero))
            generoRepository.deleteById(id);
    }

    @Override
    public List<GeneroResponseDTO> findByNome(String nome) {
        List<Genero> list = generoRepository.findByNome(nome);
        return list.stream().map(GeneroResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return generoRepository.count();
    }

    

}
