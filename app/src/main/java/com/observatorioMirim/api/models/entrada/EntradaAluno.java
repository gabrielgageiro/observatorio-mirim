package com.observatorioMirim.api.models.entrada;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;

public class EntradaAluno implements Serializable {

    @SerializedName("id_escola")
    private Integer idEscola;

    @SerializedName("nome_aluno")
    private String nomeAluno;

    @SerializedName("IdConta")
    private Integer idConta;

    public EntradaAluno() {
    }

    public EntradaAluno(EntradaAlunoDto entradaAlunoDto) {
        this.nomeAluno = entradaAlunoDto.getNome();
    }

    public Integer getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(Integer idEscola) {
        this.idEscola = idEscola;
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
