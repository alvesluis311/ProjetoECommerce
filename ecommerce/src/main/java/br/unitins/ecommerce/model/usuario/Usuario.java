package br.unitins.ecommerce.model.usuario;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import br.unitins.ecommerce.model.DefaultEntity;
import br.unitins.ecommerce.model.endereco.Endereco;
import br.unitins.ecommerce.model.produto.Produto;

// IMPLEMENTAR O LISTA DESEJO
// IMPLEMENTAR O CRUD DE AVALIACAO

@Entity
public class Usuario extends DefaultEntity {

    private String login;
    private String senha;

    @ElementCollection
    @CollectionTable(name = "perfis", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"))
    @Column(name = "perfil", length = 30)
    private Set<Perfil> perfis;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @ManyToMany
    @JoinTable(name = "lista_desejo",
                joinColumns = @JoinColumn(name = "id_usuario"),
                inverseJoinColumns = @JoinColumn(name = "id_produto"))
    // Criando uma tabela auxiliar
    private List<Produto> produtos;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @ManyToOne
    @JoinColumn(name = "id_endereco", nullable = false)
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "id_telefone_principal", unique = true, nullable = false)
    private Telefone telefonePrincipal;

    @OneToOne
    @JoinColumn(name = "id_telefone_opcional", unique = true)
    private Telefone telefoneOpcional;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Produto produto) {
        this.produtos.add(produto);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefonePrincipal() {
        return telefonePrincipal;
    }

    public void setTelefonePrincipal(Telefone telefonePrincipal) {
        this.telefonePrincipal = telefonePrincipal;
    }

    public Telefone getTelefoneOpcional() {
        return telefoneOpcional;
    }

    public void setTelefoneOpcional(Telefone telefoneOpcional) {
        this.telefoneOpcional = telefoneOpcional;
    }

}
