package br.unitins.ecommerce.service.usuario;


import br.unitins.ecommerce.dto.usuario.TelefoneForm;
import br.unitins.ecommerce.dto.usuario.TelefoneResponse;
import br.unitins.ecommerce.exception.ConflictException;
import br.unitins.ecommerce.exception.NotFoundEntityException;
import br.unitins.ecommerce.mapper.TelefoneMapper;
import br.unitins.ecommerce.model.usuario.Telefone;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.repository.TelefoneRepository;
import br.unitins.ecommerce.utils.BeanUtil;
import br.unitins.ecommerce.utils.RequestValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TelefoneServiceImpl implements TelefoneService {
    @Inject
    TelefoneRepository repository;

    @Inject
    UsuarioService usuarioService;

    @Inject
    TelefoneMapper mapper;

    @Inject
    RequestValidator validator;

    public Telefone buscarOuFalharEntidadePorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new NotFoundEntityException(String.format("Telefone de id %d não encontrado.", id)));
    }

    public TelefoneResponse buscarOuFalharResponseId(Long id) {
        return mapper.toResponse(buscarOuFalharEntidadePorId(id));
    }

    public TelefoneResponse buscarOuFalharResponseId(Long usuarioId, Long id) {
        Usuario usuario = usuarioService.buscarOuFalharEntidadePorId(usuarioId);
        Telefone telefone = buscarOuFalharEntidadePorId(id);

        if (!usuario.getListaTelefone().contains(telefone)) {
            throw new ConflictException(String.format("Telefone não pertence ao usuário %s.", usuarioId));
        }

        return mapper.toResponse(buscarOuFalharEntidadePorId(id));
    }

    public List<TelefoneResponse> buscarListaTelefoneResponse(Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalharEntidadePorId(usuarioId);

        return usuario.getListaTelefone().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public void cadastrar(Telefone telefone) {
        repository.persist(telefone);
    }

    @Transactional
    public TelefoneResponse cadastrar(Long usuarioId, TelefoneForm form) {
        validator.validate(form);

        Usuario usuario = usuarioService.buscarOuFalharEntidadePorId(usuarioId);

        Telefone telefone = mapper.toEntity(form);

        repository.persist(telefone);

        usuario.addTelefone(telefone);

        return buscarOuFalharResponseId(telefone.getId());
    }

    @Transactional
    public TelefoneResponse atualizar(Long telefoneId, Long usuarioId, TelefoneForm form) {
        validator.validate(form);

        Usuario usuario = usuarioService.buscarOuFalharEntidadePorId(usuarioId);

        if (form.isPrincipal()) {
            usuario.getListaTelefone().forEach(e -> e.setPrincipal(false));
        }

        Telefone target = buscarOuFalharEntidadePorId(telefoneId);

        Telefone source = mapper.toEntity(form);

        if (!usuario.getListaTelefone().contains(target)) {
            throw new ConflictException(String.format("Telefone com código %s, não pertence ao usuário com código %s.", telefoneId, usuarioId));
        }

        BeanUtil.copyNonNullProperties(source, target);

        return buscarOuFalharResponseId(target.getId());
    }

    @Transactional
    public void deletar(Long id, Long usuarioId) {
        Usuario usuario = usuarioService.buscarOuFalharEntidadePorId(usuarioId);

        Telefone telefone = buscarOuFalharEntidadePorId(id);

        usuario.removeTelefone(telefone);

        repository.delete(telefone);
    }

}
