package br.unitins.ecommerce.service.usuario;

import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosClienteForm;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosUsuarioForm;
import br.unitins.ecommerce.dto.usuario.dadospessoais.DadosUsuarioResponse;
import br.unitins.ecommerce.exception.ConflictException;
import br.unitins.ecommerce.exception.NegocioException;
import br.unitins.ecommerce.exception.NotFoundEntityException;
import br.unitins.ecommerce.mapper.UsuarioMapper;
import br.unitins.ecommerce.model.usuario.Cliente;
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

    public DadosUsuarioResponse findPersonalData(String login) {
        Usuario usuario = findByLogin(login);

        if (usuario instanceof Cliente cliente) {
            return mapper.dadosPessoaisToResponse(cliente);
        }
        return mapper.dadosPessoaisToResponse(usuario);

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


    @Transactional
    public void insert(UsuarioForm form) {
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
    }


    @Transactional
    public void update(Long usuarioId, UsuarioRequest request) {

        validator.validate(request);
        Usuario source = mapper.toEntity(request);

        Usuario target = findOrFailEntityById(usuarioId);

        BeanUtil.copyNonNullProperties(source, target);

        target.setSenha(hashService.getHashSenha(request.getSenha()));

    }

    @Transactional
    public void merge(Long usuarioId, UsuarioPatch patch) {
        validator.validateNonNullProperties(patch);
        Usuario source = mapper.toEntity(patch);
        Usuario target = findOrFailEntityById(usuarioId);

        BeanUtil.copyNonNullProperties(source, target);

        if (patch.getSenha() != null) {
            target.setSenha(hashService.getHashSenha(patch.getSenha()));
        }

    }

    @Transactional
    public void merge(Long usuarioId, DadosUsuarioForm form) {
        validator.validateNonNullProperties(form);
        Usuario source = mapper.dadosPessoaisToEntity(form);

        Usuario target = findOrFailEntityById(usuarioId);

        BeanUtil.copyNonNullProperties(source, target);

    }

    @Transactional
    public void merge(Long usuarioId, DadosClienteForm form) {
        validator.validateNonNullProperties(form);


        Cliente source = mapper.dadosPessoaisToEntity(form);

        Usuario cliente = findOrFailEntityById(usuarioId);

        if (cliente instanceof Cliente target) {
            BeanUtil.copyNonNullProperties(source, target);
        }
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


    private void validateEmail(String email) {
        Optional<Usuario> usuario = repository.buscarPorEmail(email);
        if (usuario.isPresent()) {
            throw new ConflictException("Email já cadastrado");
        }
    }

    private void validateLogin(String login) {
        Optional<Usuario> usuario = repository.findByLogin(login);
        if (usuario.isPresent()) {
            throw new ConflictException("Login já cadastrado");
        }
    }

}
