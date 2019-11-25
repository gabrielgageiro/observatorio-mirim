package com.observatorioMirim.views.entrada.produto.item;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.observatorioMirim.api.models.produto.Produto;
import com.observatorioMirim.api.models.produto.ProdutoDto;
import com.observatorioMirim.utils.AbstractFragment;

public class EntradaProdutoItem {

    private static final String TITULO = "Produtos";

    public static void open(@NonNull final AppCompatActivity activity){


        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setNome("FARINHA DE TRIGO ESPECIAL ENRIQUECIDA COM FERRO E ÁCIDO FÓLICO");

        AbstractFragment.openFragmentFromActivity(activity, EntradaProdutoItemFragment.newInstance(produtoDto), TITULO);
    }
}
