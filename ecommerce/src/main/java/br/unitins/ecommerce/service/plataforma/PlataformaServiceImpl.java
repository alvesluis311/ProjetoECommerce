package br.unitins.ecommerce.service.plataforma;

import br.unitins.ecommerce.dto.plataforma.PlataformaForm;
import br.unitins.ecommerce.dto.plataforma.PlataformaResponse;
import br.unitins.ecommerce.exception.NotFoundEntityException;
import br.unitins.ecommerce.mapper.PlataformaMapper;
import br.unitins.ecommerce.model.produto.plataforma.Plataforma;
import br.unitins.ecommerce.repository.PlataformaRepository;
import br.unitins.ecommerce.utils.BeanUtil;
import br.unitins.ecommerce.utils.RequestValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PlataformaServiceImpl implements PlataformaService {

    @Inject
    PlataformaRepository repository;


    @Inject
    PlataformaMapper mapper;

    @Inject
    RequestValidator requestValidator;

    @Override
    public List<PlataformaResponse> getAll() {
        List<Plataforma> list = repository.listAll();
        return mapper.toList(list);
    }

    public Plataforma findOrFailEntityById(Long usuarioId) {
        return repository.buscarPorId(usuarioId)
                .orElseThrow(() -> new NotFoundEntityException(String.format("Plataforma de id %d não encontrado.", usuarioId)));
    }


    @Override
    public PlataformaResponse finResponseById(Long id) {
        Plataforma plataforma = findOrFailEntityById(id);
        return mapper.toResponse(plataforma);
    }

    @Override
    @Transactional
    public void create(PlataformaForm form) {

        requestValidator.validateNonNullProperties(form);

        Plataforma plataforma = mapper.toEntity(form);

        repository.persist(plataforma);
    }

    @Override
    @Transactional
    public void update(Long id, PlataformaForm form) {
        requestValidator.validateNonNullProperties(form);
        Plataforma target = findOrFailEntityById(id);

        Plataforma source = mapper.toEntity(form);

        BeanUtil.copyNonNullProperties(source, target);

    }


    @Override
    @Transactional
    public void delete(Long id) {
        Plataforma plataforma = findOrFailEntityById(id);

        repository.delete(plataforma);
    }

    @Override
    public List<PlataformaResponse> findByNome(String nome) {
        List<Plataforma> list = repository.findByNome(nome);
        return mapper.toList(list);
    }

    @Override
    public long count() {
        return repository.count();
    }

}
