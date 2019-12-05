package com.observatorioMirim.utils;

import androidx.annotation.NonNull;

public enum UnidadeProduto {

    Unidade( "Unidade"),
    Litro("Litro"),
    ML("ML"),
    Kg("Kg"),
    Grama("Grama"),
    Caixa("Caixa");

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
