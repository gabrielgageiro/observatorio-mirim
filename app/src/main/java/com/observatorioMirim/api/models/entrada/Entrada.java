package com.observatorioMirim.api.models.entrada;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

public class Entrada implements Serializable {

    @SerializedName("id_escola")
    private Integer idEscola;

    @SerializedName("data_hora")
    private Date dataHora;

    @SerializedName("observacao")
    private String observacao;

    @SerializedName("IdConta")
    private Integer idConta;

    public Entrada() {
    }

    public Entrada(Integer idEscola, String observacao, Integer idConta, Date dataHora) {
        this.idEscola = idEscola;
        this.observacao = observacao;
        this.idConta = idConta;
        this.dataHora = dataHora;
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

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
}
