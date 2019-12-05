package com.observatorioMirim.views.entrada.aluno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.observatorioMirim.utils.AbstractFragment;

public class EntradaAluno {

    private static final String TITULO = "Alunos";

    public static void open(@NonNull final AppCompatActivity activity){
        AbstractFragment.openFragmentFromActivity(activity, EntradaAlunoFragment.newInstance(), TITULO, "EntradaAlunoFragment");
    }
}
