package com.observatorioMirim.views.entrada.produto.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.observatorioMirim.api.models.produto.ProdutoDto;
import com.observatorioMirim.utils.AbstractFragment;

import java.util.ArrayList;

public class EntradaProdutoList {

    private static final String TITULO = "Produtos";

    public static void open(@NonNull final AppCompatActivity activity, final ArrayList<ProdutoDto> produtos){

        AbstractFragment.openFragmentFromActivity(activity, EntradaProdutoListFragment.newInstance(produtos), TITULO, EntradaProdutoListFragment.TAG);
    }
}
