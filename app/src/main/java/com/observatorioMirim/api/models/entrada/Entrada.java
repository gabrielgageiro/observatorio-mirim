package com.observatorioMirim.api.models.entrada;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Entrada implements Serializable {

    @SerializedName("id_escola")
    private Integer idEscola;

    @SerializedName("data_hora")
    private LocalDateTime dataHora;

    @SerializedName("observacao")
    private String observacao;

    @SerializedName("IdConta")
    private Integer idConta;

    @SerializedName("listaEntradaItem")
    private List<EntradaItem> entradaItems = new ArrayList<>();

    @SerializedName("listaEntradaAluno")
    private List<EntradaAluno> entradaAlunos = new ArrayList<>();

    public Entrada() {
    }

    public Entrada(Integer idEscola, LocalDateTime dataHora, String observacao, Integer idConta, List<EntradaItem> entradaItems, List<EntradaAluno> listaEntradaAluno) {
        this.idEscola = idEscola;
        this.dataHora = dataHora;
        this.observacao = observacao;
        this.idConta = idConta;
        this.entradaItems = entradaItems;
        this.entradaAlunos = listaEntradaAluno;
    }

    public Integer getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(Integer idEscola) {
        this.idEscola = idEscola;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public List<EntradaItem> getEntradaItems() {
        return entradaItems;
    }

    public void setEntradaItems(List<EntradaItem> entradaItems) {
        this.entradaItems = entradaItems;
    }

    public List<EntradaAluno> getEntradaAlunos() {
        return entradaAlunos;
    }

    public void setEntradaAlunos(List<EntradaAluno> entradaAlunos) {
        this.entradaAlunos = entradaAlunos;
    }
}
