package br.unitins.ecommerce.service.endereco;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.endereco.EnderecoResponse;
import br.unitins.ecommerce.exception.ConflictException;
import br.unitins.ecommerce.exception.NotFoundEntityException;
import br.unitins.ecommerce.mapper.EnderecoMapper;
import br.unitins.ecommerce.model.endereco.Endereco;
import br.unitins.ecommerce.model.endereco.Municipio;
import br.unitins.ecommerce.model.usuario.Cliente;
import br.unitins.ecommerce.repository.EnderecoRepository;
import br.unitins.ecommerce.service.muncipio.MunicipioService;
import br.unitins.ecommerce.service.usuario.UsuarioService;
import br.unitins.ecommerce.utils.BeanUtil;
import br.unitins.ecommerce.utils.RequestValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {
    @Inject
    EnderecoMapper mapper;

    @Inject
    RequestValidator validator;

    @Inject
    EnderecoRepository repository;

    @Inject
    MunicipioService municipioService;

    @Inject
    UsuarioService usuarioService;

    public Endereco buscarOuFalharEntidadePorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new NotFoundEntityException(String.format("Endereco de id %d não encontrado.", id)));
    }

    public EnderecoResponse buscarOuFalharResponseId(Long id) {
        return mapper.toResponse(buscarOuFalharEntidadePorId(id));
    }

    public EnderecoResponse findOrFailResponseByUsuarioIdAndEnderecoId(Long usuarioId, Long id) {
        Cliente usuario = (Cliente) usuarioService.findOrFailEntityById(usuarioId);
        Endereco endereco = buscarOuFalharEntidadePorId(id);

        if (!usuario.getListaEndereco().contains(endereco)) {
            throw new ConflictException(String.format("Endereço não pertence ao usuário %s.", usuarioId));
        }

        return mapper.toResponse(buscarOuFalharEntidadePorId(id));
    }

    public List<EnderecoResponse> findAllEnderecosByUsuarioId(Long usuarioId) {
        Cliente usuario = (Cliente) usuarioService.findOrFailEntityById(usuarioId);

        return usuario.getListaEndereco().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnderecoResponse> findAllEnderecosByUsuarioId(String login) {
        Cliente usuario = (Cliente) usuarioService.findByLogin(login);

        return usuario.getListaEndereco().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }


    @Transactional
    public EnderecoResponse add(Long usuarioId, EnderecoForm form) {
        validator.validate(form);

        Cliente cliente = (Cliente) usuarioService.findOrFailEntityById(usuarioId);

        Endereco endereco = mapper.toEntity(form);

        Municipio municipio = municipioService.buscarOuFalharEntidadePorId(endereco.getMunicipio().getId());

        endereco.setMunicipio(municipio);

        repository.persist(endereco);

        cliente.addEndereco(endereco);

        cliente.setLevelAcessUser();

        return buscarOuFalharResponseId(endereco.getId());
    }

    @Transactional
    public EnderecoResponse update(Long enderecoId, Long usuarioId, EnderecoForm form) {
        validator.validate(form);

        Cliente usuario = (Cliente) usuarioService.findOrFailEntityById(usuarioId);

        if (form.isPrincipal()) {
            usuario.getListaEndereco().forEach(e -> e.setPrincipal(false));
        }

        Endereco target = buscarOuFalharEntidadePorId(enderecoId);

        Endereco source = mapper.toEntity(form);

        if (!usuario.getListaEndereco().contains(target)) {
            throw new ConflictException(String.format("Endereço com código %s, não pertence ao usuário com código %s.", enderecoId, usuarioId));
        }

        BeanUtil.copyNonNullProperties(source, target);

        return buscarOuFalharResponseId(target.getId());
    }

    @Transactional
    public void delete(Long id, Long usuarioId) {
        Cliente usuario = (Cliente) usuarioService.findOrFailEntityById(usuarioId);

        Endereco endereco = buscarOuFalharEntidadePorId(id);

        usuario.removeEndereco(endereco);

        repository.delete(endereco);
    }

}
