package com.observatorioMirim.views.entrada.aluno;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.observatorioMirim.MainActivity;
import com.observatorioMirim.R;
import com.observatorioMirim.api.models.entrada.EntradaDto;
import com.observatorioMirim.api.models.entrada.EntradaDtoDao;
import com.observatorioMirim.api.models.entrada.aluno.EntradaAlunoDto;
import com.observatorioMirim.api.models.entrada.aluno.EntradaAlunoDtoDao;
import com.observatorioMirim.utils.Shared;
import com.observatorioMirim.utils.SweetUtils;
import com.observatorioMirim.views.saida.list.SaidaList;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;


public class EntradaAlunoFragment extends Fragment {

    private AutoCompleteTextView autoCompleteTextView;
    private ChipGroup chipGroup;
    private Button sincronizarEntradaAgora;
    private Button sincronizarEntradaDepois;
    private TextInputEditText textInputObservacao;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_aluno, container, false);

        autoCompleteTextView = view.findViewById(R.id.mainTagAutoCompleteTextView);
        chipGroup = view.findViewById(R.id.mainTagChipGroup);
        loadTagsUi(autoCompleteTextView, chipGroup, new ArrayList<>());

        textInputObservacao = view.findViewById(R.id.aluno_observacao);

        sincronizarEntradaAgora = view.findViewById(R.id.sincronizar_entrada_agora);
        sincronizarEntradaAgora.setOnClickListener(onClick -> {
//            Toast.makeText(getContext(), "Vai para outra tela", Toast.LENGTH_LONG).show();
//            for (int i = 0; i< chipGroup.getChildCount() ; i++){
//                Chip chip = (Chip) chipGroup.getChildAt(i);
//                alunos.add(chip.getText().toString());
//            }
//            alunos.forEach(s-> System.out.println(s));
        });

        sincronizarEntradaDepois = view.findViewById(R.id.sincronizar_entrada_depois);
        sincronizarEntradaDepois.setOnClickListener(onClick -> {
            try {
                int entradaId = updateEntrada();
                saveNomeAlunos(entradaId);
                Toast.makeText(getContext(), "Entrada finalizada com sucesso!", Toast.LENGTH_LONG).show();
                SaidaList.open((MainActivity) getActivity());
            }catch (Exception e){
                SweetUtils.message(getActivity(), "Erro:", e.getMessage(), SweetAlertDialog.ERROR_TYPE);
            }
        });

        return view;
    }

    private void loadTagsUi(AutoCompleteTextView autoCompleteTextView, ChipGroup chipGroup, List<String> atuaisChips) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.setOnItemClickListener((adapterView, view, position, id) -> {
            String nome = adapterView.getItemAtPosition(position).toString();
            addChipToGroup(nome, chipGroup, new ArrayList<>());
            autoCompleteTextView.setText(null);
        });

        autoCompleteTextView.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addChipToGroup(textView.getText().toString(), chipGroup, new ArrayList<String>());
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
        chip.setTextSize(22);
        chip.setOnCloseIconClickListener(o -> {
            chipGroup.removeView(chip);
            items.remove(nome);
        });
    }

    private void saveNomeAlunos(int entradaId) throws Exception {

        List<EntradaAlunoDto> alunoDtos = new ArrayList<>();

        for (int i = 0; i< chipGroup.getChildCount() ; i++){
            Chip chip = (Chip) chipGroup.getChildAt(i);
            alunoDtos.add(new EntradaAlunoDto(chip.getText().toString(), entradaId));
        }

        if(alunoDtos.isEmpty()){
            throw new Exception("Você precisa informar pelo menos um aluno!");
        }

        EntradaAlunoDtoDao.insertAll(getActivity(), alunoDtos);
    }

    private int updateEntrada(){
        int idEntrada = Shared.getInt(getActivity(), "entradaAtual");

        EntradaDto entradaDto = EntradaDtoDao.findById(getActivity(), idEntrada);
        entradaDto.setObservacao(textInputObservacao.getText().toString());
        entradaDto.setFinalizada(true);
        EntradaDtoDao.save(getActivity(), entradaDto);

        return idEntrada;
    }

    public static EntradaAlunoFragment newInstance(){
        return new EntradaAlunoFragment();
    }
}
