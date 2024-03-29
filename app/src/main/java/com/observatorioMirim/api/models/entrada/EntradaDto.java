package com.observatorioMirim.api.models.entrada;

import android.content.Context;

import com.observatorioMirim.api.models.saida.Saida;
import com.observatorioMirim.utils.Shared;

import java.time.LocalDate;

public class EntradaDto {

    private Integer id;
    private Integer idEscola;
    private Integer idConta;
    private Integer idSaida;
    private String observacao;
    private LocalDate data;

    public EntradaDto() {}

    public EntradaDto(Saida saida) {
        this.idEscola = saida.getIdEscola();
        this.idConta = saida.getIdConta();
        this.idSaida = saida.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(Integer idEscola) {
        this.idEscola = idEscola;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public Integer getIdSaida() {
        return idSaida;
    }

    public void setIdSaida(Integer idSaida) {
        this.idSaida = idSaida;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isNew(){
        return id == null;
    }
}
