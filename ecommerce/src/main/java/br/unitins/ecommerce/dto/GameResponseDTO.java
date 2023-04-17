package topicos1.unitins.projeto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

// import java.util.HashMap;
// import java.util.Map;

import topicos1.unitins.projeto.model.produto.game.Game;

public class GameResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String estoque;
    private String diretor;
    private Integer anoLancamento;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String developer;

   // private Map<String, Object> plataforma;

    public GameResponseDTO(Game game) {
        this.id = game.getId();
        this.nome = game.getNome();
        this.descricao = game.getDescricao();
        this.preco = game.getPreco();
        if(game.getEstoque() > 0){
            this.estoque = ""+game.getEstoque()+" Unidades em estoque";
        }else{
            this.estoque = "Estoque Esgotado";
        }
        this.diretor = game.getDiretor();
        this.anoLancamento = game.getAnoLancamento();
        this.developer = game.getDeveloper().getLabel();
    //    this.plataforma = new HashMap<String, Object>();
    //    ((Produto) this.plataforma.put("nome", game.getPlataforma())).getNome();
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

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
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

    // public Map<String, Object> getPlataforma() {
    //     return plataforma;
    // }

    // public void setPlataforma(Map<String, Object> plataforma) {
    //     this.plataforma = plataforma;
    // }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    
    
}
