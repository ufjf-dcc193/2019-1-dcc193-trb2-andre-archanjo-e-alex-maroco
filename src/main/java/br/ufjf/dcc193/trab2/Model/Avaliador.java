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

import org.apache.logging.log4j.message.Message;

/**
 * Avaliador
 */
@Entity
public class Avaliador {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank(message = "Insira um nome")
    private String nome;
    

    @Column(unique=true)
    @Email(message = "Insira um e-mail válido")
    private String email;

    private Long codigoAcesso;

    private boolean[] areasConhecimento;

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

    public boolean[] getAreasConhecimento() {
        return areasConhecimento;
    }

    public void setAreasConhecimento(boolean[] areasConhecimento) {
        this.areasConhecimento = areasConhecimento;
    }

    public List<Revisao> getListRevisao() {
        return listRevisao;
    }

    public void setListRevisao(List<Revisao> listRevisao) {
        this.listRevisao = listRevisao;
    }


    
}