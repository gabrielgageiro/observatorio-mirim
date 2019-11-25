package com.observatorioMirim.views.entrada.produto.list;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.observatorioMirim.api.models.produto.ProdutoDto;
import com.observatorioMirim.cadastro.produto.Produto;
import com.observatorioMirim.utils.AbstractFragment;
import com.observatorioMirim.views.entrada.produto.list.EntradaProdutoListFragment;

import java.util.ArrayList;

public class EntradaProdutoList {

    private static final String TITULO = "Produtos";

    public static void open(@NonNull final FragmentActivity activity, final ArrayList<ProdutoDto> produtos){

        AbstractFragment.openFragmentFromActivity(activity, EntradaProdutoListFragment.newInstance(produtos), TITULO);
    }
}
