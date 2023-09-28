package br.unitins.ecommerce.model.produto.game;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import br.unitins.ecommerce.model.DefaultEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Estoque extends DefaultEntity {

    @Column(nullable = false)
    private Boolean disponivel;

    @Column(nullable = false)
    private Integer qtdDisponivel;

    @Column(nullable = false)
    private Integer qtdVendida;

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void expirado() {
        if (this.qtdDisponivel <= 0) {
            this.disponivel = false;
        }else{
            this.disponivel = true;
        }
    }

    public Integer getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(Integer qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public Integer getQtdVendida() {
        return qtdVendida;
    }

    public void setQtdVendida(Integer qtdVendida) {
        this.qtdVendida = qtdVendida;
    }

    public void minusEstoque(Integer qtdDisponivel) {

        this.qtdDisponivel -= qtdDisponivel;
        this.qtdVendida += qtdVendida;
    }

}
