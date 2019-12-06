package com.observatorioMirim.utils;

import android.content.Context;

import com.observatorioMirim.api.models.entrada.EntradaDto;
import com.observatorioMirim.api.models.entrada.EntradaDtoDao;

public final class Contexto {

    public static int getIdEntradaAtual(Context context){
        return Shared.getInt(context, "entradaAtual");
    }

    public static void setIdEntradaAtual(Context context, int id){
        Shared.putInt(context, "entradaAtual", id);
    }

    public static EntradaDto getEntradaAtual(Context context){
        return EntradaDtoDao.findById(context, getIdEntradaAtual(context));
    }
}
