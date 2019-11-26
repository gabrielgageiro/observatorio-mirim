package com.observatorioMirim.views.entrada.produto.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.observatorioMirim.api.models.produto.ProdutoDtoCache;
import com.observatorioMirim.utils.AbstractFragment;

import java.util.ArrayList;

public class EntradaProdutoList {

    private static final String TITULO = "Produtos";

    public static void open(@NonNull final AppCompatActivity activity){

        AbstractFragment.openFragmentFromActivity(activity, EntradaProdutoListFragment.newInstance(ProdutoDtoCache.getCache()), TITULO, EntradaProdutoListFragment.TAG);
    }
}
