package com.observatorioMirim.api.models.entrada;

import java.io.Serializable;

public class EntradaAlunoDto implements Serializable {

    private Integer id;
    private Integer idEntrada; //Id local da entrada
    private String nome;

    public EntradaAlunoDto() {}

    public EntradaAlunoDto(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
