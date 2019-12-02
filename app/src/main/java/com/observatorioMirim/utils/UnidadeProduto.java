package com.observatorioMirim.utils;

public enum UnidadeProduto {

    UNIDADE( "Unidade"),
    LITRO("Litro"),
    ML("ML"),
    KG("Kg"),
    GR("Grama"),
    CX("Caixa");

    private String descricao;

    private UnidadeProduto(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
