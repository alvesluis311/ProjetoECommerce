    package br.unitins.ecommerce.model.usuario;

    import br.unitins.ecommerce.model.endereco.Endereco;
    import br.unitins.ecommerce.model.produto.game.Game;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.HashSet;
    import java.util.List;

    @Getter
    @Setter
    @Entity
    public class Cliente extends Usuario {

        @Column(name = "nome_completo")
        private String nomeCompleto;

        private String cpf;

        @Column(name = "data_nascimento")
        private LocalDate dataNascimento;

        private Sexo sexo;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "usuario_id")
        private List<Telefone> listaTelefone;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "usuario_id")
        private List<Endereco> listaEndereco;

        @ManyToMany
        @JoinTable(name = "lista_desejo",
                joinColumns = @JoinColumn(name = "id_usuario"),
                inverseJoinColumns = @JoinColumn(name = "id_produto"))
        private List<Game> produtos;

        public void addTelefone(Telefone telefone) {
            if (this.listaTelefone == null) {
                this.listaTelefone = new ArrayList<>();
            }
            if (this.listaTelefone.isEmpty()) {
                listaTelefone = new ArrayList<>();
                telefone.setPrincipal(true);
            } else {
                if (telefone.isPrincipal()) {
                    this.listaTelefone.forEach(t -> t.setPrincipal(false));
                }
            }
            listaTelefone.add(telefone);
        }
        public void removeTelefone(Telefone telefone) {
            this.listaTelefone.remove(telefone);

            if (this.getListaTelefone().size() == 1) {
                Telefone t = this.getListaTelefone().get(0);
                t.setPrincipal(true);
            }
        }

        public void addEndereco(Endereco endereco) {
            if (this.listaEndereco == null) {
                this.listaEndereco = new ArrayList<>();
            }
            if (this.listaEndereco.isEmpty()) {
                endereco.setPrincipal(true);
            } else {
                if (endereco.isPrincipal()) {
                    this.listaEndereco.forEach(e -> e.setPrincipal(false));
                }
            }
            listaEndereco.add(endereco);
        }

        public void removeEndereco(Endereco endereco) {
            this.listaEndereco.remove(endereco);

            if (this.getListaEndereco().size() == 1) {
                Endereco e = this.getListaEndereco().get(0);
                e.setPrincipal(true);
            } else if (this.getListaEndereco().isEmpty()) {
                setListaPerfil(null);

                this.setLevelAcessUser();
            }

        }

        public Endereco getEnderecoPrincipal() {
            if (this.listaEndereco == null) {
                return null;
            }

            return this.listaEndereco.stream()
                    .filter(Endereco::isPrincipal)
                    .findFirst()
                    .orElse(null);
        }

        public Telefone getTelefonePrincipal() {
            if (this.listaTelefone == null) {
                return null;
            }

            return this.listaTelefone.stream()
                    .filter(Telefone::isPrincipal)
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public void setLevelAcessUser() {
                if (getEnderecoPrincipal() != null){
                    addPerfil(Perfil.USER);
                } else {
                    addPerfil(Perfil.USER_BASIC);
                }
            }
    }
