package br.ufjf.dcc193.trab2.Model;

/**
 * Trabalho
 */
public class Trabalho {

    private Long id;

    private String titulo;

    private String descricao;

    private String url;

    private int areaConhecimento;

    private Revisao revisao;

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

    public Revisao getRevisao() {
        return revisao;
    }

    public void setRevisao(Revisao revisao) {
        this.revisao = revisao;
    }

    
    
}