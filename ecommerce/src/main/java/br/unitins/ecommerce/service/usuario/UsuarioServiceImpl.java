package br.unitins.ecommerce.service.usuario;

import br.unitins.ecommerce.dto.usuario.UsuarioForm;
import br.unitins.ecommerce.dto.usuario.UsuarioPatch;
import br.unitins.ecommerce.dto.usuario.UsuarioRequest;
import br.unitins.ecommerce.dto.usuario.UsuarioResponse;
import br.unitins.ecommerce.exception.ConflictException;
import br.unitins.ecommerce.exception.NotFoundEntityException;
import br.unitins.ecommerce.mapper.UsuarioMapper;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.repository.UsuarioRepository;
import br.unitins.ecommerce.service.endereco.EnderecoService;
import br.unitins.ecommerce.service.hash.HashService;

import br.unitins.ecommerce.utils.BeanUtil;
import br.unitins.ecommerce.utils.RequestValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Getter
@Setter
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioMapper mapper;

    @Inject
    RequestValidator validator;

    @Inject
    UsuarioRepository repository;

    @Inject
    TelefoneService telefoneService;

    @Inject
    EnderecoService enderecoService;

    @Inject
    HashService hashService;

    public Usuario buscarOuFalharEntidadePorId(Long usuarioId) {
        return repository.buscarPorId(usuarioId)
                .orElseThrow(() -> new NotFoundEntityException(String.format("Usuario de id %d não encontrado.", usuarioId)));
    }

    public UsuarioResponse buscarOuFalharResponsePorId(Long usuarioId) {
        return mapper.toResponse(buscarOuFalharEntidadePorId(usuarioId));
    }

    public List<UsuarioResponse> buscarListaUsuario() {
        return mapper.toListResponse(repository.findAll().list());
    }

    @Transactional
    public UsuarioResponse cadastrar(UsuarioForm form) {
        validator.validate(form);
        validarCpf(form.getCpf());
        validarEmail(form.getEmail());

        Usuario usuario = mapper.toEntity(form);

        usuario.getListaTelefone()
                .forEach(t ->
                        telefoneService.cadastrar(t)
                );

        usuario.getListaEndereco()
                .forEach(e -> {
                    enderecoService.cadastrar(e);
                });

        usuario.setSenha(hashService.getHashSenha(usuario.getSenha()));

        repository.persist(usuario);

        return buscarOuFalharResponsePorId(usuario.getId());
    }

    private void validarEmail(String email) {
        Optional<Usuario> usuario = repository.buscarPorEmail(email);
        if (usuario.isPresent()) {
            throw new ConflictException("Email já cadastrado");
        }
    }

    private void validarCpf(String cpf) {
        Optional<Usuario> usuario = repository.buscarPorCpf(cpf);
        if (usuario.isPresent()) {
            throw new ConflictException("Cpf já cadastrado");
        }
    }

    @Transactional
    public UsuarioResponse atualizar(Long usuarioId, UsuarioRequest request) {

        validator.validate(request);
        Usuario source = mapper.toEntity(request);

        Usuario target = buscarOuFalharEntidadePorId(usuarioId);

        BeanUtil.copyNonNullProperties(source, target);

        target.setSenha(hashService.getHashSenha(request.getSenha()));

        return buscarOuFalharResponsePorId(target.getId());
    }

    @Transactional
    public UsuarioResponse atualizarParcial(Long usuarioId, UsuarioPatch patch) {
        validator.validateNonNullProperties(patch);
        Usuario source = mapper.toEntity(patch);
        Usuario target = buscarOuFalharEntidadePorId(usuarioId);

        BeanUtil.copyNonNullProperties(source, target);

        if (patch.getSenha() != null) {
            target.setSenha(hashService.getHashSenha(patch.getSenha()));
        }

        return buscarOuFalharResponsePorId(target.getId());
    }

    @Transactional
    public void deletar(Long usuarioId) {
        Usuario usuario = buscarOuFalharEntidadePorId(usuarioId);
        repository.delete(usuario);
    }

    public Usuario buscarPorLoginESenha(String login, String senha) {

        return repository.findByLoginAndSenha(login, senha);
    }

    @Override
    public Usuario buscarPorLogin(String login) {
        return repository.findByLogin(login);
    }

}
