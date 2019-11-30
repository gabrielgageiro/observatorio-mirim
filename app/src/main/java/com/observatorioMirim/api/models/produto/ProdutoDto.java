package com.observatorioMirim.api.models.produto;

import com.observatorioMirim.api.models.saida.SaidaItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoDto implements Serializable {

    private Integer id; //Id no banco de dados local
    private Integer idProduto;
    private Integer idSaida;
    private String nome;
    private String marca;
    private LocalDate dataValidade;
    private BigDecimal quantidade;
    private String unidade;
    private String observacao;
    private boolean entrada = false;

    public ProdutoDto() {
    }

    public ProdutoDto (SaidaItem saidaItem){
        this.idProduto = saidaItem.getIdProduto();
        this.idSaida = saidaItem.getIdSaida();
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

    public Integer getIdSaida() {
        return idSaida;
    }

    public void setIdSaida(Integer idSaida) {
        this.idSaida = idSaida;
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
}
