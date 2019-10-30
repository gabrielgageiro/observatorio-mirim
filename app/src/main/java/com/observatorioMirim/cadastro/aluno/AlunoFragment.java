package com.observatorioMirim.cadastro.aluno;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.observatorioMirim.R;
import com.observatorioMirim.utils.AbstractFragment;

public class AlunoFragment extends AbstractFragment {
    private ImageButton addButton;

    private AlunoFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static AlunoFragment create(){
        return new AlunoFragment(R.layout.activity_aluno, "Cadastro Aluno");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        addButton = (ImageButton) view.findViewById(R.id.addAluno);
        addButton.setOnClickListener(v ->{

        });

        return view;
    }
}
