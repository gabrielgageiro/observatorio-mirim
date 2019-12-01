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

    @SerializedName("id_saida")
    private Integer idSaida;

    @SerializedName("observacao")
    private String observacao;

    @SerializedName("IdConta")
    private Integer idConta;

    @SerializedName("listaEntradaItem")
    private List<EntradaItem> entradaItems = new ArrayList<>();

    @SerializedName("listaEntradaAluno")
    private List<EntradaAluno> entradaAlunos = new ArrayList<>();

    @SerializedName("ListaEntrada")
    private List<Entrada> entradaEntradas = new ArrayList<>();

    @SerializedName("escola")
    private String escola;

    public Entrada() {
    }

    public Entrada(Integer idEscola, Integer idSaida, String observacao, Integer idConta, List<EntradaItem> entradaItems, List<EntradaAluno> entradaAlunos, List<Entrada> entradaEntradas, String escola) {
        this.idEscola = idEscola;
        this.idSaida = idSaida;
        this.observacao = observacao;
        this.idConta = idConta;
        this.entradaItems = entradaItems;
        this.entradaAlunos = entradaAlunos;
        this.entradaEntradas = entradaEntradas;
        this.escola = escola;
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

    public Integer getIdSaida() {
        return idSaida;
    }

    public void setIdSaida(Integer idSaida) {
        this.idSaida = idSaida;
    }

    public List<Entrada> getEntradaEntradas() {
        return entradaEntradas;
    }

    public void setEntradaEntradas(List<Entrada> entradaEntradas) {
        this.entradaEntradas = entradaEntradas;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }
}
