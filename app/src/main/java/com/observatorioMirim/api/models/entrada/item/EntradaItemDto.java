package com.observatorioMirim.api.models.entrada.item;

import androidx.annotation.NonNull;

import com.observatorioMirim.api.models.saida.SaidaItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EntradaItemDto implements Serializable {

    private Integer id; //Id no banco de dados local
    private Integer idProduto;
    private Integer idEntrada; //Id local da entrada
    private String nome;
    private String marca;
    private LocalDate dataValidade;
    private BigDecimal quantidade;
    private String unidade;
    private String observacao;
    private boolean entrada = false;
    private boolean upload = false;

    public EntradaItemDto() {
    }

    public EntradaItemDto(SaidaItem saidaItem){
        this.idProduto = saidaItem.getIdProduto();
        this.nome = saidaItem.getProduto();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Integer idEntrada) {
        this.idEntrada = idEntrada;
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

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isEntrada() {
        return entrada;
    }

    public void setEntrada(boolean entrada) {
        this.entrada = entrada;
    }

    public boolean isNew(){
        return id == null;
    }

    public boolean isUpload() {
        return upload;
    }

    public void setUpload(boolean upload) {
        this.upload = upload;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
