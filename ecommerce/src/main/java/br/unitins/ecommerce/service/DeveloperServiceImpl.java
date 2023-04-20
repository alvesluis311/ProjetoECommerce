package br.unitins.ecommerce.service;

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

import br.unitins.ecommerce.dto.game.DeveloperDTO;
import br.unitins.ecommerce.dto.game.DeveloperResponseDTO;
import br.unitins.ecommerce.model.produto.game.Developer;
import br.unitins.ecommerce.repository.DeveloperRepository;
import br.unitins.ecommerce.repository.PlataformaRepository;

@ApplicationScoped
public class DeveloperServiceImpl implements DeveloperService {

    @Inject
    DeveloperRepository developerRepository;

    @Inject
    PlataformaRepository plataformaRepository;

    @Inject
    Validator validator;

    @Override
    public List<DeveloperResponseDTO> getAll() {
        List<Developer> list = developerRepository.listAll();
        return list.stream().map(DeveloperResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public DeveloperResponseDTO findById(Long id) {
        Developer developer = developerRepository.findById(id);
        if (developer == null)
            throw new NotFoundException("Developer não encontrado.");
        return new DeveloperResponseDTO(developer);
    }

    @Override
    @Transactional
    public DeveloperResponseDTO create(DeveloperDTO developerDTO) throws ConstraintViolationException {
        validar(developerDTO);

        Developer entity = new Developer();
        entity.setNome(developerDTO.nome());
        entity.setFundacao(developerDTO.fundacao());

        developerRepository.persist(entity);

        return new DeveloperResponseDTO(entity);
    }

    @Override
    @Transactional
    public DeveloperResponseDTO update(Long id, DeveloperDTO developerDTO) throws ConstraintViolationException{
        validar(developerDTO);
   
        Developer entity = developerRepository.findById(id);
        entity.setNome(developerDTO.nome());
        entity.setFundacao(developerDTO.fundacao());

        return new DeveloperResponseDTO(entity);
    }

    private void validar(DeveloperDTO developerDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<DeveloperDTO>> violations = validator.validate(developerDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Developer developer = developerRepository.findById(id);

        if (developerRepository.isPersistent(developer))
            developerRepository.deleteById(id);
    }

    @Override
    public List<DeveloperResponseDTO> findByNome(String nome) {
        List<Developer> list = developerRepository.findByNome(nome);
        return list.stream().map(DeveloperResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return developerRepository.count();
    }

}
