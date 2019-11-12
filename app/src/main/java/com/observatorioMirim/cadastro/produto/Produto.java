package com.observatorioMirim.cadastro.produto;

public class Produto {
    private String codigoProduto;
    private String nomeProduto;
    private String barrasProduto;

    public Produto(String codigoProduto, String nomeProduto, String barrasProduto) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.barrasProduto = barrasProduto;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getBarrasProduto() {
        return barrasProduto;
    }

    public void setBarrasProduto(String barrasProduto) {
        this.barrasProduto = barrasProduto;
    }
}
