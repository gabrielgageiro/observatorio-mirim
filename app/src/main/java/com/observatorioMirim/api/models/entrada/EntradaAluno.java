package com.observatorioMirim.api.models.entrada;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class EntradaAluno {

    @SerializedName("id_escola")
    private Integer idEscola;

    @SerializedName("id_entrada")
    private Integer idEntrada;

    @SerializedName("nome_aluno")
    private String nomeAluno;

    @SerializedName("IdConta")
    private Integer idConta;

    public EntradaAluno() {
    }

    public EntradaAluno(Integer idEscola, Integer idEntrada, String nomeAluno, Integer idConta) {
        this.idEscola = idEscola;
        this.idEntrada = idEntrada;
        this.nomeAluno = nomeAluno;
        this.idConta = idConta;
    }

    public Integer getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(Integer idEscola) {
        this.idEscola = idEscola;
    }

    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }
}
