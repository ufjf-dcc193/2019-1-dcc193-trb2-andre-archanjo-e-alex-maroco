package br.ufjf.dcc193.trab2.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Trabalho
 */
@Entity
public class Trabalho {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotBlank(message = "Insira um título")
    private String titulo;

    @NotBlank(message = "Insira uma descrição")
    private String descricao;

    @NotBlank(message = "Insira uma URL")
    private String url;

    @NotNull(message = "Selecione uma área de conhecimento")
    private int areaConhecimento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Revisao> listRevisao;

    public Trabalho() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(int areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    public List<Revisao> getListRevisao() {
        return listRevisao;
    }

    public void setListRevisao(List<Revisao> listRevisao) {
        this.listRevisao = listRevisao;
    }

    public void addRevisao(Revisao revisao){
        this.listRevisao.add(revisao);
    }

    public void removeRevisao(Revisao revisao){
        this.listRevisao.remove(revisao);
    }

    @Override
    public String toString() {
        return "Trabalho [areaConhecimento=" + areaConhecimento + ", descricao=" + descricao + ", id=" + id
                + ", listRevisao=" + listRevisao + ", titulo=" + titulo + ", url=" + url + "]";
    }

    

    
}