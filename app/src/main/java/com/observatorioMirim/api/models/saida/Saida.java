package com.observatorioMirim.api.models.saida;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Saida implements Serializable {

    @SerializedName("Id")
    private Integer id;

    @SerializedName("id_escola")
    private Integer idEscola;

    @SerializedName("observacao")
    private String observacao;

    @SerializedName("id_usuario")
    private String idUsuario;

    @SerializedName("item")
    private SaidaItem saidaItem;

    @SerializedName("ListaSaidaItem")
    private List<SaidaItem> saidaItemList;

    @SerializedName("IdConta")
    private Integer idConta;

    @SerializedName("escola")
    private String escola;

    public Saida() {}

    public Saida(Integer idEscola, String observacao, String idUsuario, SaidaItem saidaItem, List<SaidaItem> saidaItemList, Integer idConta, String escola) {
        this.idEscola = idEscola;
        this.observacao = observacao;
        this.idUsuario = idUsuario;
        this.saidaItem = saidaItem;
        this.saidaItemList = saidaItemList;
        this.idConta = idConta;
        this.escola = escola;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public SaidaItem getSaidaItem() {
        return saidaItem;
    }

    public void setSaidaItem(SaidaItem saidaItem) {
        this.saidaItem = saidaItem;
    }

    public List<SaidaItem> getSaidaItemList() {
        return saidaItemList;
    }

    public void setSaidaItemList(List<SaidaItem> saidaItemList) {
        this.saidaItemList = saidaItemList;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }
}
