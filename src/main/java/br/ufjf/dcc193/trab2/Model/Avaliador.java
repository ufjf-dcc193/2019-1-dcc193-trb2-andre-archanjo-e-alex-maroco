package br.ufjf.dcc193.trab2.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * Avaliador
 */
@Entity
public class Avaliador {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    //@NotBlank(message = "Insira um nome")
    private String nome;
    

    @NotBlank(message = "O campo não pode estar vazio")
    @Column(unique=true)
    @Email(message = "Insira um e-mail válido")
    private String email;

    @NotNull(message = "O campo não pode estar vazio")
    private Long codigoAcesso;

    private boolean areaExatas;

    private boolean areaHumanas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Revisao> listRevisao;

    public Avaliador() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCodigoAcesso() {
        return codigoAcesso;
    }

    public void setCodigoAcesso(Long codigoAcesso) {
        this.codigoAcesso = codigoAcesso;
    }

    public List<Revisao> getListRevisao() {
        return listRevisao;
    }

    public void setListRevisao(List<Revisao> listRevisao) {
        this.listRevisao = listRevisao;
    }

    public boolean isAreaExatas() {
        return areaExatas;
    }

    public void setAreaExatas(boolean areaExatas) {
        this.areaExatas = areaExatas;
    }

    public boolean isAreaHumanas() {
        return areaHumanas;
    }

    public void setAreaHumanas(boolean areaHumanas) {
        this.areaHumanas = areaHumanas;
    }

    @Override
    public String toString() {
        return "Avaliador [areaExatas=" + areaExatas + ", areaHumanas=" + areaHumanas + ", codigoAcesso=" + codigoAcesso
                + ", email=" + email + ", id=" + id + ", listRevisao=" + listRevisao + ", nome=" + nome + "]";
    }

    public void addRevisao(Revisao revisao){
        this.listRevisao.add(revisao);
    }

    public void removeRevisao(Revisao revisao){
        this.listRevisao.remove(revisao);
    }

    
    
}