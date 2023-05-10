package br.unitins.ecommerce.service.plataforma;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
// import javax.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

import br.unitins.ecommerce.dto.plataforma.PlataformaDTO;
import br.unitins.ecommerce.dto.plataforma.PlataformaResponseDTO;
import br.unitins.ecommerce.model.produto.plataforma.Fabricante;
import br.unitins.ecommerce.model.produto.plataforma.Plataforma;
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
    public List<PlataformaResponseDTO> findByNome(String nome) {
        List<Plataforma> list = plataformaRepository.findByNome(nome);

        if (list == null)
            throw new NullPointerException("nenhuma plataforma encontrada");

        return list.stream()
                    .map(PlataformaResponseDTO::new)
                    .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return plataformaRepository.count();
    }

}
