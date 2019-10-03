package com.observatorioMirim.cadastro;

import com.observatorioMirim.R;
import com.observatorioMirim.utils.AbstractFragment;

public class CadastroFragment extends AbstractFragment {
    public CadastroFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static CadastroFragment create(){
        return new CadastroFragment(R.layout.cadastro_fragment, "Cadastro");
    }
}
