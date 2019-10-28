package com.observatorioMirim.cadastro.aluno;

import com.observatorioMirim.R;
import com.observatorioMirim.utils.AbstractFragment;

public class AlunoFragment extends AbstractFragment {
    private AlunoFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static AlunoFragment create(){
        return new AlunoFragment(R.layout.activity_aluno, "Cadastro Aluno");
    }
}
