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
    private Date idConta;

    public Entrada() {
    }

    public Entrada(Integer idEscola, Date dataHora, String observacao, Date idConta) {
        this.idEscola = idEscola;
        this.dataHora = dataHora;
        this.observacao = observacao;
        this.idConta = idConta;
    }

    public Integer getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(Integer idEscola) {
        this.idEscola = idEscola;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getIdConta() {
        return idConta;
    }

    public void setIdConta(Date idConta) {
        this.idConta = idConta;
    }
}
