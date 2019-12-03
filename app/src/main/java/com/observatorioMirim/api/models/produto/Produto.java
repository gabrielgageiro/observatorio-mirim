package com.observatorioMirim.api.models.produto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Produto implements Serializable {
    @SerializedName("id_escola")
    private Integer idEscola;

    @SerializedName("nome")
    private String nome;

    @SerializedName("marca")
    private String marca;

    @SerializedName("codigo_barras")
    private String codigoBarras;

    @SerializedName("unidade")
    private String unidade;

    @SerializedName("IdConta")
    private Integer idConta;

    @SerializedName("escola")
    private String escola;

    public Produto() {
    }

    public Produto(Integer idEscola, String nome, String marca, String codigoBarras, String unidade, Integer idConta, String escola) {
        this.idEscola = idEscola;
        this.nome = nome;
        this.marca = marca;
        this.codigoBarras = codigoBarras;
        this.unidade = unidade;
        this.idConta = idConta;
        this.escola = escola;
    }

    public Integer getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(Integer idEscola) {
        this.idEscola = idEscola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
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

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
