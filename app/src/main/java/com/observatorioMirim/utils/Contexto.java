package com.observatorioMirim.utils;

import android.content.Context;

public final class Contexto {

    public static int getIdEntradaAtual(Context context){
        return Shared.getInt(context, "entradaAtual");
    }

    public static void setIdEntradaAtual(Context context, int id){
        Shared.putInt(context, "entradaAtual", id);
    }
}
