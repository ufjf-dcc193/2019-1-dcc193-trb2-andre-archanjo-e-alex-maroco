package br.ufjf.dcc193.trab2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Revisão
 */
@Entity
public class Revisao {


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Min(value = 0, message = "O valor mínimo é 0")
    @Max(value = 100, message = "O valor máximo é 100")
    private int nota;

    private String descricao;

    private int status;

    public Revisao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    

}