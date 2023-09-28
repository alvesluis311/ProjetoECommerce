package br.unitins.ecommerce.model.produto.plataforma;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import br.unitins.ecommerce.model.DefaultEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Plataforma extends DefaultEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    //@Column(nullable = false)
    private LocalDate anoLancamento;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    private Fabricante fabricante;

}

