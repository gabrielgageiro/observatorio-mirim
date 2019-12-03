package com.observatorioMirim.utils;

import androidx.annotation.NonNull;

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


    @NonNull
    @Override
    public String toString() {
        return descricao;
    }
}
