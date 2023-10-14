package br.unitins.ecommerce.service.developer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.ecommerce.dto.developer.DeveloperResponseDTO;
import br.unitins.ecommerce.dto.developer.DeveloperDTO;
import br.unitins.ecommerce.model.produto.game.Developer;
import br.unitins.ecommerce.repository.DeveloperRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DeveloperServiceImpl implements DeveloperService {

    @Inject
    DeveloperRepository developerRepository;

    @Inject
    Validator validator;

    @Override
    public List<DeveloperResponseDTO> getAll(int page, int pageSize) {
        List<Developer> list = developerRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> DeveloperResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public DeveloperResponseDTO getById(Long id) {
        Developer developer = developerRepository.findById(id);
        if (developer == null)
            throw new NotFoundException("Developer não encontrado.");
        return DeveloperResponseDTO.valueOf(developer);
    }

    @Override
    @Transactional
    public DeveloperResponseDTO create(DeveloperDTO developerDTO) throws ConstraintViolationException {
        validar(developerDTO);

        Developer entity = new Developer();
        entity.setNome(developerDTO.nome());
        entity.setFundacao(developerDTO.fundacao());

        developerRepository.persist(entity);

        return DeveloperResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public DeveloperResponseDTO update(Long id, DeveloperDTO developerDTO) throws ConstraintViolationException{
        validar(developerDTO);
   
        Developer entity = developerRepository.findById(id);
        entity.setNome(developerDTO.nome());
        entity.setFundacao(developerDTO.fundacao());

        return DeveloperResponseDTO.valueOf(entity);
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
    public List<DeveloperResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Developer> list = developerRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream().map(e -> DeveloperResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return developerRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return developerRepository.findByNome(nome).count();
    }

}
