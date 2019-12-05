package com.observatorioMirim.api.models.entrada.item;

import com.google.gson.annotations.SerializedName;


import java.io.Serializable;
import java.math.BigDecimal;

public class EntradaItem implements Serializable {

    @SerializedName("id_escola")
    private Integer idEscola;

    @SerializedName("id_produto")
    private Integer idProduto;

    @SerializedName("marca")
    private String marca;

    @SerializedName("unidade")
    private String unidade;

    @SerializedName("quantidade")
    private BigDecimal quantidade;

    @SerializedName("observacao")
    private String observacao;

    @SerializedName("prazo_validade")
    private String prazoValidade;

    @SerializedName("foto")
    private String foto;

    @SerializedName("IdConta")
    private Integer idConta;

    public EntradaItem() {
    }

    public EntradaItem(EntradaItemDto entradaItemDto) {
        this.idProduto = entradaItemDto.getIdProduto();
        this.marca = entradaItemDto.getMarca();
        this.unidade = entradaItemDto.getUnidade();
        this.quantidade = entradaItemDto.getQuantidade();
        this.observacao = entradaItemDto.getObservacao();
        this.prazoValidade = entradaItemDto.getDataValidade().toString();
    }

    public Integer getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(Integer idEscola) {
        this.idEscola = idEscola;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getPrazoValidade() {
        return prazoValidade;
    }

    public void setPrazoValidade(String prazoValidade) {
        this.prazoValidade = prazoValidade;
    }
}

