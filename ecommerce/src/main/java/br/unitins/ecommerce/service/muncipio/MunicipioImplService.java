package br.unitins.ecommerce.service.muncipio;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.ecommerce.exception.NotFoundEntityException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

import br.unitins.ecommerce.dto.municipio.MunicipioDTO;
import br.unitins.ecommerce.dto.municipio.MunicipioResponseDTO;
import br.unitins.ecommerce.model.endereco.Estado;
import br.unitins.ecommerce.model.endereco.Municipio;
import br.unitins.ecommerce.repository.EstadoRepository;
import br.unitins.ecommerce.repository.MunicipioRepository;

@ApplicationScoped
public class MunicipioImplService implements MunicipioService {
    @Inject
    Validator validator;

    @Inject
    MunicipioRepository municipioRepository;

    @Inject
    EstadoRepository estadoRepository;

    @Override
    public List<MunicipioResponseDTO> getAll() {
        
        return municipioRepository.findAll()
                                    .stream()
                                    .map(MunicipioResponseDTO::new)
                                    .toList();
    }

    public Municipio buscarOuFalharEntidadePorId(Long id) {
        return municipioRepository.buscarPorId(id)
                .orElseThrow(() -> new NotFoundEntityException(String.format("Municipio de id %d não encontrado.", id)));
    }

    @Override
    public MunicipioResponseDTO getById(Long id) throws NotFoundException {
        
        Municipio municipio = municipioRepository.findById(id);

        if (municipio == null)
            throw new NotFoundException("Não encontrado");

        return new MunicipioResponseDTO(municipio);
    }

    @Override
    @Transactional
    public MunicipioResponseDTO insert(MunicipioDTO municipioDto) throws ConstraintViolationException {
        
        validar(municipioDto);

        Municipio entity = new Municipio();

        entity.setNome(municipioDto.nome());

        entity.setEstado(estadoRepository.findById(municipioDto.idestado()));

        municipioRepository.persist(entity);

        return new MunicipioResponseDTO(entity);
    }

    @Override
    @Transactional
    public MunicipioResponseDTO update(Long id, MunicipioDTO municipioDto) throws ConstraintViolationException, NotFoundException {
        
        validar(municipioDto);

        Municipio entity = municipioRepository.findById(id);

        if (entity == null)
            throw new NotFoundException("Número fora das opções disponíveis");

        entity.setNome(municipioDto.nome());

        entity.setEstado(estadoRepository.findById(municipioDto.idestado()));

        return new MunicipioResponseDTO(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws IllegalArgumentException, NotFoundException {
        
        if (id == null)
            throw new IllegalArgumentException("Número inválido");

        Municipio municipio = municipioRepository.findById(id);

        if (municipioRepository.isPersistent(municipio))
            municipioRepository.delete(municipio);

        else
            throw new NotFoundException("Nenhum municipio encontrado");
    }

    @Override
    public Long count() {

        return municipioRepository.count();
    }

    @Override
    public List<MunicipioResponseDTO> getByNome(String nome) throws NullPointerException {
        
        List<Municipio> list = municipioRepository.findByNome(nome);

        if (list == null)
            throw new NullPointerException("nenhum municipio encontrado");

        return list.stream()
                    .map(MunicipioResponseDTO::new)
                    .collect(Collectors.toList());
    }


    // @Override
    // public List<MunicipioResponseDTO> getByNomeEstado(String nomeEstado) throws NullPointerException {
        
    //     List<Estado> estados = estadoRepository.findByNome(nomeEstado);

    //     if (estados == null)
    //         throw new NullPointerException("nenhum estado com esse nome encontrado");

    //     List<Municipio> list = municipioRepository.findByEstado(estados.get(0));

    //     if (list == null)
    //         throw new NullPointerException("nenhum municipio encontrado");
        
    //     return list.stream()
    //                 .map(MunicipioResponseDTO::new)
    //                 .collect(Collectors.toList());
    // }

    @Override
    public List<MunicipioResponseDTO> getBySiglaEstado(String siglaEstado) throws NullPointerException {
        
        List<Estado> estados = estadoRepository.findBySigla(siglaEstado);

        if (estados == null)
            throw new NullPointerException("nenhum estado com esse nome encontrado");

        List<Municipio> list = municipioRepository.findByEstado(estados.get(0));

        if (list == null)
            throw new NullPointerException("nenhum municipio encontrado");
        
        return list.stream()
                    .map(MunicipioResponseDTO::new)
                    .collect(Collectors.toList());
    }
    
    private void validar(MunicipioDTO municipioDTO) throws ConstraintViolationException {

        Set<ConstraintViolation<MunicipioDTO>> violations = validator.validate(municipioDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
