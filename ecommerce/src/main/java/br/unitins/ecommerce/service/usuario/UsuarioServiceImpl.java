package br.unitins.ecommerce.service.usuario;

import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosPessoaisResponse;
import br.unitins.ecommerce.exception.ConflictException;
import br.unitins.ecommerce.exception.NegocioException;
import br.unitins.ecommerce.exception.NotFoundEntityException;
import br.unitins.ecommerce.mapper.UsuarioMapper;
import br.unitins.ecommerce.model.usuario.Perfil;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.repository.UsuarioRepository;
import br.unitins.ecommerce.service.hash.HashService;
import br.unitins.ecommerce.utils.BeanUtil;
import br.unitins.ecommerce.utils.RequestValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioMapper mapper;

    @Inject
    RequestValidator validator;

    @Inject
    UsuarioRepository repository;

    @Inject
    HashService hashService;

    @Override
    public Usuario findByLoginAndSenha(String login, String senha) {
        return repository.findByLoginAndSenha(login, senha);
    }

    @Override
    public Usuario findByLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(() -> new NotFoundEntityException(String.format("Usuario com login %s não encontrado.", login)));
    }

    @Override
    public DadosPessoaisResponse buscarDadosPessoais(String login) {
        return mapper.dadosPessoaisToResponse(findByLogin(login));
    }


    public Usuario findOrFailEntityById(Long usuarioId) {
        return repository.buscarPorId(usuarioId)
                .orElseThrow(() -> new NotFoundEntityException(String.format("Usuario de id %d não encontrado.", usuarioId)));
    }

    public UsuarioResponse findOrFailResponseById(Long usuarioId) {
        return mapper.toResponse(findOrFailEntityById(usuarioId));
    }

    public List<UsuarioResponse> findAll() {
        return mapper.toListResponse(repository.findAll().list());
    }

    public List<ClienteResponse> findAllClientes() {
        return mapper.toListClienteResponse(repository.findAllClientes());
    }

    @Transactional
    public void add(UsuarioForm form) {
        formValidation(form);

        Usuario usuario = mapper.toEntity(form);

        usuario.setSenha(hashService.getHashSenha(usuario.getSenha()));

        usuario.setLevelAcessUser();

        repository.persist(usuario);

    }


    private void formValidation(UsuarioForm form) {
        validator.validate(form);
        validateLogin(form.getLogin());
        validateEmail(form.getEmail());
        if (form instanceof ClienteForm clienteForm) {
            validateCpf(clienteForm.getCpf());
        }
    }


    @Override
    @Transactional
    public void addProfiles(Long id, List<Integer> perfis) {
        Usuario usuario = findOrFailEntityById(id);

        perfis.forEach(p ->
                usuario.addPerfil(Perfil.valueOf(p)));
    }


    @Transactional
    public UsuarioResponse update(Long usuarioId, UsuarioRequest request) {

        validator.validate(request);
        Usuario source = mapper.toEntity(request);

        Usuario target = findOrFailEntityById(usuarioId);

        BeanUtil.copyNonNullProperties(source, target);

        target.setSenha(hashService.getHashSenha(request.getSenha()));

        return findOrFailResponseById(target.getId());
    }

    @Transactional
    public UsuarioResponse merge(Long usuarioId, UsuarioPatch patch) {
        validator.validateNonNullProperties(patch);
        Usuario source = mapper.toEntity(patch);
        Usuario target = findOrFailEntityById(usuarioId);

        BeanUtil.copyNonNullProperties(source, target);

        if (patch.getSenha() != null) {
            target.setSenha(hashService.getHashSenha(patch.getSenha()));
        }

        return findOrFailResponseById(target.getId());
    }

    @Transactional
    public void delete(Long usuarioId) {
        Usuario usuario = findOrFailEntityById(usuarioId);
        repository.delete(usuario);
    }


    @Override
    @Transactional
    public void updatePassword(String login, SenhaDTO dto) {
        Usuario usuario = findByLogin(login);

        String senhaNova = hashService.getHashSenha(dto.senhaNova());

        if (!usuario.getSenha().equals(hashService.getHashSenha(dto.senhaAntiga()))) {
            throw new NegocioException("Senha invalida.");
        }

        usuario.setSenha(senhaNova);
    }

    public void save(Usuario usuario) {
        repository.persist(usuario);
    }


    private void validateEmail(String email) {
        Optional<Usuario> usuario = repository.buscarPorEmail(email);
        if (usuario.isPresent()) {
            throw new ConflictException("Email já cadastrado");
        }
    }

    private void validateCpf(String cpf) {
        Optional<Usuario> usuario = repository.buscarPorCpf(cpf);
        if (usuario.isPresent()) {
            throw new ConflictException("Cpf já cadastrado");
        }
    }

    private void validateLogin(String login) {
        Optional<Usuario> usuario = repository.findByLogin(login);
        if (usuario.isPresent()) {
            throw new ConflictException("Login já cadastrado");
        }
    }

}
