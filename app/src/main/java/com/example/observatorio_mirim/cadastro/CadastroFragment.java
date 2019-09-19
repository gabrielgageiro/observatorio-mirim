package com.example.observatorio_mirim.cadastro;

import com.example.observatorio_mirim.R;
import com.example.observatorio_mirim.utils.AbstractFragment;

public class CadastroFragment extends AbstractFragment {
    public CadastroFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static CadastroFragment create(){
        return new CadastroFragment(R.layout.cadastro_fragment, "Cadastro");
    }
}
