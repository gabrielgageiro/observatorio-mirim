package com.observatorioMirim.views.entrada.produto.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.observatorioMirim.utils.AbstractFragment;

public class EntradaProdutoList {

    private static final String TITULO = "Produtos";

    public static void open(@NonNull final AppCompatActivity activity){
        AbstractFragment.openFragmentFromActivity(activity, EntradaProdutoListFragment.newInstance(), TITULO, EntradaProdutoListFragment.TAG);
    }
}
