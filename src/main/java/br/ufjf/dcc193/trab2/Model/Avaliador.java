package br.ufjf.dcc193.trab2.Model;

import java.util.List;

/**
 * Avaliador
 */
public class Avaliador {

    private Long id;

    private String nome;

    private String email;

    private Long codigoAcesso;

    private boolean[] areasConhecimento;

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