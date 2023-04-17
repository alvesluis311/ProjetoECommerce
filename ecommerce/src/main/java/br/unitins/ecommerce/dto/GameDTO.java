package topicos1.unitins.projeto.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class GameDTO {

    @NotBlank(message = "O campo nome deve ser informado.")
    @Size(max = 60, message = "O nome deve posssuir no máximo 60 caracteres.")
    private String nome;

    private String descricao;

    private Double preco;

    private Integer estoque;

    private String diretor;

    private Integer anoLancamento;

    @Enumerated(EnumType.STRING)
    private Integer developer;

    private List<Long> idPlataforma;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public void setDeveloper(Integer developer) {
        this.developer = developer;
    }

    public Integer getDeveloper() {
        return developer;
    }

    public List<Long> getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(List<Long> idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

}
