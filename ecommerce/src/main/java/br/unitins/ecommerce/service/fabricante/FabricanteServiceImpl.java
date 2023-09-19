package br.unitins.ecommerce.service.fabricante;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.ecommerce.dto.fabricante.FabricanteResponseDTO;
import br.unitins.ecommerce.dto.fabricante.FabricanteDTO;
import br.unitins.ecommerce.model.produto.plataforma.Fabricante;
import br.unitins.ecommerce.repository.FabricanteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FabricanteServiceImpl implements FabricanteService {

    @Inject
    FabricanteRepository fabricanteRepository;

    @Inject
    Validator validator;

    @Override
    public List<FabricanteResponseDTO> getAll() {
        List<Fabricante> list = fabricanteRepository.listAll();
        return list.stream().map(FabricanteResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public FabricanteResponseDTO getById(Long id) {
        Fabricante fabricante = fabricanteRepository.findById(id);
        if (fabricante == null)
            throw new NotFoundException("Fabricante não encontrado.");
        return new FabricanteResponseDTO(fabricante);
    }

    @Override
    @Transactional
    public FabricanteResponseDTO create(FabricanteDTO fabricanteDTO) throws ConstraintViolationException {
        validar(fabricanteDTO);

        Fabricante entity = new Fabricante();
        entity.setNome(fabricanteDTO.nome());

        fabricanteRepository.persist(entity);

        return new FabricanteResponseDTO(entity);
    }

    @Override
    @Transactional
    public FabricanteResponseDTO update(Long id, FabricanteDTO fabricanteDTO) throws ConstraintViolationException{
        validar(fabricanteDTO);
   
        Fabricante entity = fabricanteRepository.findById(id);
        entity.setNome(fabricanteDTO.nome());

        return new FabricanteResponseDTO(entity);
    }

    private void validar(FabricanteDTO fabricanteDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<FabricanteDTO>> violations = validator.validate(fabricanteDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Fabricante fabricante = fabricanteRepository.findById(id);

        if (fabricanteRepository.isPersistent(fabricante))
            fabricanteRepository.deleteById(id);
    }

    @Override
    public List<FabricanteResponseDTO> findByNome(String nome) {
        List<Fabricante> list = fabricanteRepository.findByNome(nome);
        return list.stream().map(FabricanteResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return fabricanteRepository.count();
    }

    

}
