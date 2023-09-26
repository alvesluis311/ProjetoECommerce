package br.unitins.ecommerce.service.usuario;

import br.unitins.ecommerce.dto.endereco.EnderecoForm;
import br.unitins.ecommerce.dto.endereco.EnderecoResponse;
import br.unitins.ecommerce.dto.usuario.*;
import br.unitins.ecommerce.exception.ConflictException;
import br.unitins.ecommerce.exception.NotFoundEntityException;
import br.unitins.ecommerce.mapper.ClienteMapper;
import br.unitins.ecommerce.mapper.EnderecoMapper;
import br.unitins.ecommerce.mapper.TelefoneMapper;
import br.unitins.ecommerce.model.endereco.Endereco;
import br.unitins.ecommerce.model.usuario.Cliente;
import br.unitins.ecommerce.model.usuario.Telefone;
import br.unitins.ecommerce.model.usuario.Usuario;
import br.unitins.ecommerce.repository.ClienteRepository;
import br.unitins.ecommerce.service.hash.HashService;
import br.unitins.ecommerce.service.token.TokenJwtService;
import br.unitins.ecommerce.utils.BeanUtil;
import br.unitins.ecommerce.utils.RequestValidator;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteRepository repository;

    @Inject
    EntityManager em;

    @Inject
    TokenJwtService tokenService;

    @Inject
    HashService hashService;

    @Inject
    ClienteMapper mapper;

    @Inject
    EnderecoMapper enderecoMapper;

    @Inject
    TelefoneMapper telefoneMapper;

    @Inject
    RequestValidator validator;

    public List<ClienteResponse> findAll() {
        List<Cliente> listaCliente = repository.findAll().list();
        return mapper.toListResponse(listaCliente);
    }


    public Cliente findById(Long id) {
        return repository.findEntityById(id)
                .orElseThrow(() -> new NotFoundEntityException(String.format("Cliente com id %s não encontrado.", id)));
    }

    public Cliente findByLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(() -> new NotFoundEntityException(String.format("Cliente com login %s não encontrado.", login)));
    }

    public ClienteResponse findResponseById(Long id) {
        return mapper.toResponse(findById(id));
    }

    @Transactional
    public String signIn(SignUserForm form) {

        validator.validate(form);

        Cliente cliente = mapper.toEntity(form);

        formValidator(cliente);

        cliente.setSenha(hashService.getHashSenha(form.getSenha()));

        cliente.setLevelAcessUser();

        save(cliente);

        return tokenService.generateJwt(cliente);
    }


    @Transactional
    public void insert(ClienteForm form) {

        validator.validate(form);

        Cliente cliente = mapper.toEntity(form);

        formValidator(cliente);

        cliente.setSenha(hashService.getHashSenha(form.getSenha()));

        cliente.setLevelAcessUser();

        save(cliente);
    }

    @Transactional
    public void update(Long id, ClienteUpdate update) {
        validator.validate(update);

        Cliente source = mapper.toEntity(update);

        Cliente target = repository.findById(id);

        updateValidator(target, source);

        BeanUtil.copyNonNullProperties(source, target);

        source.setSenha(hashService.getHashSenha(update.getSenha()));
    }

    public void save(Cliente cliente) {
        repository.persist(cliente);
    }

    @Transactional
    public void delete(Long id) {
        Cliente usuario = findById(id);

        repository.delete(usuario);
    }

    public EnderecoResponse findEndereco(Long id, Long enderecoId) {
        Cliente cliente = findById(id);

        Endereco endereco = getEndereco(enderecoId, cliente);

        return enderecoMapper.toResponse(endereco);

    }

    public List<EnderecoResponse> findAllEnderecos(Long id) {
        Cliente cliente = repository.findById(id);

        return enderecoMapper.toResposeList(cliente.getListaEndereco());
    }

    @Transactional
    public void insertEndereco(Long id, EnderecoForm form) {
        Cliente cliente = repository.findById(id);

        validator.validate(form);
        Endereco endereco = enderecoMapper.toEntity(form);
        cliente.addEndereco(endereco);

        save(cliente);
    }

    @Transactional
    public void updateEndereco(Long id, Long enderecoId, EnderecoForm form) {
        Cliente cliente = repository.findById(id);

        validator.validate(form);

        Endereco source = enderecoMapper.toEntity(form);

        Endereco target = getEndereco(enderecoId, cliente);

        BeanUtil.copyNonNullProperties(source, target);

    }

    @Transactional
    public void deleteEndereco(Long id, Long enderecoId) {
        Cliente cliente = repository.findById(id);

        Endereco endereco = getEndereco(enderecoId, cliente);

        cliente.removeEndereco(endereco);
        em.remove(endereco);
    }


    public TelefoneResponse findTelefone(Long id, Long telefoneId) {
        Cliente cliente = findById(id);

        Telefone telefone = getTelefone(telefoneId, cliente);

        return telefoneMapper.toResponse(telefone);

    }

    public List<TelefoneResponse> findAllTelefones(Long id) {
        Cliente cliente = repository.findById(id);

        return telefoneMapper.toResponseList(cliente.getListaTelefone());
    }


    @Transactional
    public void insertTelefone(Long id, TelefoneForm form) {
        Cliente cliente = repository.findById(id);

        validator.validate(form);

        Telefone telefone = telefoneMapper.toEntity(form);

        cliente.addTelefone(telefone);

        save(cliente);
    }


    @Transactional
    public void updateTelefone(Long id, Long telefoneId, TelefoneForm form) {
        Cliente cliente = repository.findById(id);

        validator.validate(form);

        Telefone source = telefoneMapper.toEntity(form);

        Telefone target = getTelefone(telefoneId, cliente);

        BeanUtil.copyNonNullProperties(source, target);

    }


    @Transactional
    public void deleteTelefone(Long id, Long telefoneId) {
        Cliente cliente = repository.findById(id);

        Telefone telefone = getTelefone(telefoneId, cliente);

        cliente.removeTelefone(telefone);

        em.remove(telefone);
    }


    private Endereco getEndereco(Long enderecoId, Cliente cliente) {
        return cliente.getListaEndereco()
                .stream()
                .filter(e -> e.getId().equals(enderecoId))
                .findFirst()
                .orElseThrow(() -> new NotFoundEntityException("Endereço não encontrado"));
    }


    private Telefone getTelefone(Long telefoneId, Cliente cliente) {
        return cliente.getListaTelefone()
                .stream()
                .filter(t -> t.getId().equals(telefoneId))
                .findFirst()
                .orElseThrow(() -> new NotFoundEntityException("Telefone não encontrado"));
    }


    private void updateValidator(Cliente target, Cliente source) {
        if (!repository.isPersistent(target)) {
            throw new ConflictException("Entidade não encontra-se persistida.");
        }

        if (!target.getEmail().equals(source.getEmail())) {
            validateEmail(source.getEmail());
        }

        if (!target.getLogin().equals(source.getLogin())) {
            validateLogin(source.getLogin());
        }

        if (!target.getCpf().equals(source.getCpf())) {
            validateCpf(source.getCpf());
        }

    }

    private void formValidator(Cliente cliente) {
        validateCpf(cliente.getCpf());
        validateEmail(cliente.getEmail());
        validateLogin(cliente.getLogin());
    }

    private void validateEmail(String email) {
        Optional<Cliente> cliente = repository.findByEmail(email);
        if (cliente.isPresent()) {
            throw new ConflictException("Email já cadastrado");
        }
    }

    private void validateCpf(String cpf) {
        Optional<Cliente> cliente = repository.findByCpf(cpf);
        if (cliente.isPresent()) {
            throw new ConflictException("Cpf já cadastrado");
        }
    }

    private void validateLogin(String login) {
        Optional<Cliente> cliente = repository.findByLogin(login);
        if (cliente.isPresent()) {
            throw new ConflictException("Login já cadastrado");
        }
    }

}
