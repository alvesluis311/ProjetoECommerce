package br.unitins.ecommerce.service.usuario;


import br.unitins.ecommerce.dto.usuario.TelefoneForm;
import br.unitins.ecommerce.dto.usuario.TelefoneResponse;
import br.unitins.ecommerce.exception.ConflictException;
import br.unitins.ecommerce.exception.NotFoundEntityException;
import br.unitins.ecommerce.mapper.TelefoneMapper;
import br.unitins.ecommerce.model.usuario.Cliente;
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

    public Telefone findOrFailEntityById(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new NotFoundEntityException(String.format("Telefone de id %d não encontrado.", id)));
    }


    public TelefoneResponse findOrFailResponseById(Long usuarioId, Long id) {
        Cliente cliente = (Cliente) usuarioService.findOrFailEntityById(usuarioId);
        Telefone telefone = findOrFailEntityById(id);

        if (!cliente.getListaTelefone().contains(telefone)) {
            throw new ConflictException(String.format("Telefone não pertence ao usuário %s.", usuarioId));
        }

        return mapper.toResponse(findOrFailEntityById(id));
    }

    public List<TelefoneResponse> findTelefonesByUsuarioId(Long usuarioId) {
        Cliente cliente = (Cliente) usuarioService.findOrFailEntityById(usuarioId);

        return cliente.getListaTelefone().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }



    @Transactional
    public void add(Long usuarioId, TelefoneForm form) {
        validator.validate(form);

        Cliente cliente = (Cliente) usuarioService.findOrFailEntityById(usuarioId);

        Telefone telefone = mapper.toEntity(form);

        repository.persist(telefone);

        cliente.addTelefone(telefone);
    }

    @Transactional
    public void atualizar(Long telefoneId, Long usuarioId, TelefoneForm form) {
        validator.validate(form);

        Cliente cliente = (Cliente) usuarioService.findOrFailEntityById(usuarioId);

        if (form.isPrincipal()) {
            cliente.getListaTelefone().forEach(e -> e.setPrincipal(false));
        }

        Telefone target = findOrFailEntityById(telefoneId);

        Telefone source = mapper.toEntity(form);

        if (!cliente.getListaTelefone().contains(target)) {
            throw new ConflictException(String.format("Telefone com código %s, não pertence ao usuário com código %s.", telefoneId, usuarioId));
        }

        BeanUtil.copyNonNullProperties(source, target);

    }

    @Transactional
    public void deletar(Long id, Long usuarioId) {
        Cliente cliente = (Cliente) usuarioService.findOrFailEntityById(usuarioId);

        Telefone telefone = findOrFailEntityById(id);

        cliente.removeTelefone(telefone);

        repository.delete(telefone);
    }

}
