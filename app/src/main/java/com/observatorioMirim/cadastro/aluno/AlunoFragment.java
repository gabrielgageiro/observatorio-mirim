package com.observatorioMirim.cadastro.aluno;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.observatorioMirim.R;
import com.observatorioMirim.utils.AbstractFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AlunoFragment extends AbstractFragment {
    //todo listaAlunos sao os alunos da entrada. o cadastro dos alunos devem vir da API
    private List<String> listaAlunos = Arrays.asList("Gabriel", "Lucas", "Marcelo B", "Marcelo R", "Dani", "Cassio");
    private AutoCompleteTextView autoCompleteTextView;
    private ChipGroup chipGroup;

    private AlunoFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static AlunoFragment create() {
        return new AlunoFragment(R.layout.activity_entrada, "Entrada");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        /*Entry chip section*/

        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.mainTagAutoCompleteTextView);
        chipGroup = view.findViewById(R.id.mainTagChipGroup);
        loadTagsUi(autoCompleteTextView, chipGroup, new ArrayList<String>(), listaAlunos);
        return view;
    }

    private void loadTagsUi(AutoCompleteTextView autoCompleteTextView, ChipGroup chipGroup, List<String> atuaisChips, List<String> allAlunos) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, allAlunos);
        autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.setOnItemClickListener((adapterView, view, position, id) -> {
            String nome = adapterView.getItemAtPosition(position).toString();
            insertOrCreateAluno(nome);
            autoCompleteTextView.setText(null);
        });

        autoCompleteTextView.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                insertOrCreateAluno(textView.getText().toString());
                textView.setText(null);
                return true;
            }
            return false;
        });

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void addChipToGroup(String nome, ChipGroup chipGroup, List<String> items) {
        Chip chip = new Chip(getContext());
        chip.setText(nome);
        chip.setCheckable(false);
        chip.setClickable(true);
        chip.setCloseIconVisible(true);
        chipGroup.addView(chip);
        chip.setOnCloseIconClickListener(o -> {
            chipGroup.removeView(chip);
            items.remove(nome);
        });
    }

    private void insertOrCreateAluno(String aluno) {
        //todo listaAlunos sao os alunos da entrada. o cadastro dos alunos devem vir da API
        addChipToGroup(aluno, chipGroup, new ArrayList<String>());
        return;
    }

}
