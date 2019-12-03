package com.observatorioMirim.api.models.entrada;

import com.observatorioMirim.api.models.saida.Saida;

public class EntradaDto {

    private Integer id;
    private Integer idEscola;
    private Integer idConta;
    private Integer idSaida;
    private String observacao;
    private boolean finalizada = false;

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

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public boolean isNew(){
        return id == null;
    }
}
