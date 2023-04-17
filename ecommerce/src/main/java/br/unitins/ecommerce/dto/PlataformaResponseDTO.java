package topicos1.unitins.projeto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import topicos1.unitins.projeto.model.produto.Plataforma;

public class PlataformaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Integer anoLancamento;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fabricante;

    public PlataformaResponseDTO(Plataforma plataforma) {
        this.id = plataforma.getId();
        this.nome = plataforma.getNome();
        this.descricao = plataforma.getDescricao();
        this.anoLancamento = plataforma.getAnoLancamento();
        this.fabricante = plataforma.getFabricante().getLabel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    
    
}
