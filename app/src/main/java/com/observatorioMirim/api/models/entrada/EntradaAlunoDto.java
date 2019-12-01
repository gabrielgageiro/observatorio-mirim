package com.observatorioMirim.api.models.entrada;

import java.io.Serializable;

public class EntradaAlunoDto implements Serializable {

    private String nome;

    public EntradaAlunoDto() {}

    public EntradaAlunoDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
