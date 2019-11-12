package com.observatorioMirim.entrada;

import com.observatorioMirim.R;
import com.observatorioMirim.utils.AbstractFragment;

public class EntradaFragment extends AbstractFragment {

    public EntradaFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static EntradaFragment create(){
        return new EntradaFragment(R.layout.produto_view, "Estoque");
    }
}
