package br.unitins.ecommerce.service;

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

import br.unitins.ecommerce.dto.game.PlataformaDTO;
import br.unitins.ecommerce.dto.game.PlataformaResponseDTO;
import br.unitins.ecommerce.model.produto.Fabricante;
import br.unitins.ecommerce.model.produto.Plataforma;
import br.unitins.ecommerce.repository.PlataformaRepository;

@ApplicationScoped
public class PlataformaServiceImpl implements PlataformaService {

    @Inject
    PlataformaRepository plataformaRepository;

    @Inject
    Validator validator;

    @Override
    public List<PlataformaResponseDTO> getAll() {
        List<Plataforma> list = plataformaRepository.listAll();
        return list.stream().map(PlataformaResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public PlataformaResponseDTO findById(Long id) {
        Plataforma plataforma = plataformaRepository.findById(id);
        if (plataforma == null)
            throw new NotFoundException("Video Game não encontrado.");
        return new PlataformaResponseDTO(plataforma);
    }

    @Override
    @Transactional
    public PlataformaResponseDTO create(PlataformaDTO plataformaDTO) throws ConstraintViolationException {
        validar(plataformaDTO);

        Plataforma entity = new Plataforma();
        entity.setNome(plataformaDTO.nome());
        entity.setDescricao(plataformaDTO.descricao());
        entity.setAnoLancamento(plataformaDTO.anoLancamento());
        entity.setFabricante(Fabricante.valueOf(plataformaDTO.fabricante()));
        plataformaRepository.persist(entity);

        return new PlataformaResponseDTO(entity);
    }

    @Override
    @Transactional
    public PlataformaResponseDTO update(Long id, PlataformaDTO plataformaDTO) throws ConstraintViolationException{
        validar(plataformaDTO);
   
        Plataforma entity = plataformaRepository.findById(id);
        entity.setNome(plataformaDTO.nome());
        entity.setDescricao(plataformaDTO.descricao());
        entity.setAnoLancamento(plataformaDTO.anoLancamento());
        entity.setFabricante(Fabricante.valueOf(plataformaDTO.fabricante()));

        return new PlataformaResponseDTO(entity);
    }

    private void validar(PlataformaDTO plataformaDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<PlataformaDTO>> violations = validator.validate(plataformaDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);


    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Plataforma plataforma = plataformaRepository.findById(id);

        if (plataformaRepository.isPersistent(plataforma))
            plataformaRepository.deleteById(id);
    }

    @Override
    public List<PlataformaResponseDTO> findByNome(String nome) {
        List<Plataforma> list = plataformaRepository.findByNome(nome);
        return list.stream().map(PlataformaResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return plataformaRepository.count();
    }

}
