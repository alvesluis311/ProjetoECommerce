package br.unitins.ecommerce.service.estado;

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

import br.unitins.ecommerce.dto.estado.EstadoDTO;
import br.unitins.ecommerce.dto.estado.EstadoResponseDTO;
import br.unitins.ecommerce.model.endereco.Estado;
import br.unitins.ecommerce.repository.EstadoRepository;

@ApplicationScoped
public class EstadoImplService implements EstadoService {

    @Inject
    EstadoRepository estadoRepository;

    @Inject
    Validator validator;

    @Override
    public List<EstadoResponseDTO> getAll(int page, int pageSize) {

        List<Estado> list = estadoRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> EstadoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public EstadoResponseDTO findById(Long id) {
        Estado estado = estadoRepository.findById(id);
        if (estado == null)
            throw new NotFoundException("Estado não encontrado.");
        return EstadoResponseDTO.valueOf(estado);
    }

    @Override
    @Transactional
    public EstadoResponseDTO create(EstadoDTO estadoDTO) throws ConstraintViolationException {
        validar(estadoDTO);

        Estado entity = new Estado();
        entity.setNome(estadoDTO.nome());
        entity.setSigla(estadoDTO.sigla());

        estadoRepository.persist(entity);

        return EstadoResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public EstadoResponseDTO update(Long id, EstadoDTO estadoDTO) throws ConstraintViolationException{
        validar(estadoDTO);
   
        Estado entity = estadoRepository.findById(id);

        entity.setNome(estadoDTO.nome());
        entity.setSigla(estadoDTO.sigla());

        return EstadoResponseDTO.valueOf(entity);
    }

    private void validar(EstadoDTO estadoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<EstadoDTO>> violations = validator.validate(estadoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public List<EstadoResponseDTO> getByNome(String nome, int page, int pageSize) {
        List<Estado> list = estadoRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream().map(e -> EstadoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public Long count() {

        return estadoRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return estadoRepository.findByNome(nome).count();
    }
}