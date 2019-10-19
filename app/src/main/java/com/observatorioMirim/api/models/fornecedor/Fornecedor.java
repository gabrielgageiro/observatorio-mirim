package com.observatorioMirim.api.models.fornecedor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Fornecedor implements Serializable {

    @SerializedName("id_escola")
    private Integer idEscola;

    @SerializedName("nome")
    private String nome;

    @SerializedName("cnpj")
    private String cnpj;

    @SerializedName("telefone")
    private String telefone;

    @SerializedName("endereco")
    private String endereco;

    @SerializedName("observacao")
    private String observacao;

    @SerializedName("IdConta")
    private Integer idConta;

    @SerializedName("escola")
    private String escola;

    public Fornecedor() {
    }

    public Fornecedor(Integer idEscola, String nome, String cnpj, String telefone, String endereco, String observacao, Date dataHoraInclusao, Integer idConta, String escola) {
        this.idEscola = idEscola;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
        this.observacao = observacao;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }
}
