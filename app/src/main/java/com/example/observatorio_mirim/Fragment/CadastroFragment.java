package com.example.observatorio_mirim.Fragment;

import com.example.observatorio_mirim.R;

public class CadastroFragment extends AbstractFragment {
    public CadastroFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static CadastroFragment create(){
        return new CadastroFragment(R.layout.cadastro_fragment, "Cadastro");
    }
}
