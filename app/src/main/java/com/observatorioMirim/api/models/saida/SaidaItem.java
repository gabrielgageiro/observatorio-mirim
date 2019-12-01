package com.observatorioMirim.api.models.saida;

import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDateTime;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaidaItem implements Serializable {

    @SerializedName("id_saida")
    private Integer idSaida;

    @SerializedName("id_escola")
    private Integer idEscola;

    @SerializedName("id_fornecedor")
    private Integer idFornecedor;

    @SerializedName("id_produto")
    private Integer idProduto;

    @SerializedName("id_usuario")
    private Integer idUsuario;

    @SerializedName("unidade")
    private String unidade;

    @SerializedName("codigo_barras")
    private String codigoBarras;

    @SerializedName("marca")
    private String marca;

    @SerializedName("quantidade")
    private BigDecimal quantidade;

    @SerializedName("prazo_validade")
    private String prazoValidade;

    @SerializedName("produto")
    private String produto;

    @SerializedName("IdConta")
    private Integer idConta;

    public SaidaItem() {
    }

    public SaidaItem(Integer idSaida, Integer idEscola, Integer idFornecedor, Integer idProduto, Integer idUsuario, String unidade, String codigoBarras, String marca, BigDecimal quantidade, String prazoValidade, String produto, Integer idConta) {
        this.idSaida = idSaida;
        this.idEscola = idEscola;
        this.idFornecedor = idFornecedor;
        this.idProduto = idProduto;
        this.idUsuario = idUsuario;
        this.unidade = unidade;
        this.codigoBarras = codigoBarras;
        this.marca = marca;
        this.quantidade = quantidade;
        this.prazoValidade = prazoValidade;
        this.produto = produto;
        this.idConta = idConta;
    }

    public Integer getIdSaida() {
        return idSaida;
    }

    public void setIdSaida(Integer idSaida) {
        this.idSaida = idSaida;
    }

    public Integer getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(Integer idEscola) {
        this.idEscola = idEscola;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getPrazoValidade() {
        return prazoValidade;
    }

    public void setPrazoValidade(String prazoValidade) {
        this.prazoValidade = prazoValidade;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }
}
