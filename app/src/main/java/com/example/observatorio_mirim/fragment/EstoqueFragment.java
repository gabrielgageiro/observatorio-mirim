package com.example.observatorio_mirim.fragment;

import com.example.observatorio_mirim.R;

public class EstoqueFragment extends AbstractFragment {

    public EstoqueFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static EstoqueFragment create(){
        return new EstoqueFragment(R.layout.estoque_fragment, "Estoque");
    }
}
