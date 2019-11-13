package com.observatorioMirim.cadastro.produto;

import com.observatorioMirim.R;
import com.observatorioMirim.utils.AbstractFragment;

public class ProdutoFragment extends AbstractFragment {

    public ProdutoFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static ProdutoFragment create(){
        return new ProdutoFragment(R.layout.produto_view, "Produtos");
    }
}
