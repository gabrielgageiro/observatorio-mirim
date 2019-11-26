package com.observatorioMirim.views.entrada.produto.item;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.observatorioMirim.api.models.produto.ProdutoDto;
import com.observatorioMirim.utils.AbstractFragment;

public class EntradaProdutoItem {

    private static final String TITULO = "Produto";

    public static void open(@NonNull final AppCompatActivity activity, ProdutoDto produtoDto){
        AbstractFragment.openFragmentFromActivity(activity, EntradaProdutoItemFragment.newInstance(produtoDto), TITULO);
    }
}
