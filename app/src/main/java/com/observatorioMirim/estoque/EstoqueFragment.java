package com.observatorioMirim.estoque;

import com.observatorioMirim.R;
import com.observatorioMirim.utils.AbstractFragment;

public class EstoqueFragment extends AbstractFragment {

    public EstoqueFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static EstoqueFragment create(){
        return new EstoqueFragment(R.layout.produto_view, "Estoque");
    }
}
