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
import com.observatorioMirim.api.API;
import com.observatorioMirim.api.models.RespostaEscola;
import com.observatorioMirim.api.models.entrada.Entrada;
import com.observatorioMirim.api.models.entrada.EntradaDto;
import com.observatorioMirim.api.models.entrada.EntradaDtoDao;
import com.observatorioMirim.api.models.entrada.aluno.EntradaAlunoDto;
import com.observatorioMirim.api.models.entrada.aluno.EntradaAlunoDtoDao;
import com.observatorioMirim.utils.SweetUtils;
import com.observatorioMirim.views.saida.list.SaidaList;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EntradaAlunoFragment extends Fragment {

    private AutoCompleteTextView autoCompleteTextView;
    private ChipGroup chipGroup;
    private Button sincronizarEntradaAgora;
    private Button sincronizarEntradaDepois;
    private TextInputEditText textInputObservacao;
    private boolean jaSalvo = false; //Indica se os dados já foram salvos

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_aluno, container, false);

        autoCompleteTextView = view.findViewById(R.id.mainTagAutoCompleteTextView);
        chipGroup = view.findViewById(R.id.mainTagChipGroup);
        loadTagsUi(autoCompleteTextView, chipGroup, new ArrayList<>());

        textInputObservacao = view.findViewById(R.id.aluno_observacao);

        sincronizarEntradaAgora = view.findViewById(R.id.sincronizar_entrada_agora);
        sincronizarEntradaAgora.setOnClickListener(onClick -> {
            try {

                SweetUtils.loaderNativo(getActivity(), "Sincronizando", "Aguarde enquanto a entrada é sincronizada.");

                List<String> alunosStr = validarAlunos(); //Validacao deve ocorrer antes do update

                EntradaDto entradaDto;

                if(!jaSalvo){
                    entradaDto = updateEntrada();
                    saveNomeAlunos(alunosStr, entradaDto.getId());

                    jaSalvo = true;
                }else{
                    entradaDto = EntradaDto.getEntradaAtual(getActivity());
                }

                Entrada entrada = Entrada.generateEntrada(getActivity(), entradaDto);

                API.postEntrada(entrada, new Callback<RespostaEscola>() {
                    @Override
                    public void onResponse(Call<RespostaEscola> call, Response<RespostaEscola> response) {
                        EntradaDtoDao.delete(getActivity(), entradaDto.getId());
                        SweetUtils.cancelarLoaderNativo();
                        Toast.makeText(getContext(), "Entrada enviada com sucesso!", Toast.LENGTH_LONG).show();
                        SaidaList.open((MainActivity) getActivity());
                    }

                    @Override
                    public void onFailure(Call<RespostaEscola> call, Throwable t) {
                        SweetUtils.cancelarLoaderNativo();
                        SweetUtils.message(getActivity(), "Erro", "Não foi possível sincronizar, verifique sua conexão com a internet.", SweetAlertDialog.ERROR_TYPE);
                    }
                });

            }catch (Exception e){
                SweetUtils.cancelarLoaderNativo();
                SweetUtils.message(getActivity(), "Erro:", e.getMessage(), SweetAlertDialog.ERROR_TYPE);
            }
        });

        sincronizarEntradaDepois = view.findViewById(R.id.sincronizar_entrada_depois);
        sincronizarEntradaDepois.setOnClickListener(onClick -> {
            try {

                List<String> alunosStr = validarAlunos(); //Validacao deve ocorrer antes do update

                int entradaId = updateEntrada().getId();
                saveNomeAlunos(alunosStr, entradaId);

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

    private List<String> validarAlunos() throws Exception {

        List<String> alunosStr = new ArrayList<>();

        for (int i = 0; i< chipGroup.getChildCount() ; i++){
            Chip chip = (Chip) chipGroup.getChildAt(i);

            String alunoStr = chip.getText().toString();

            if(alunoStr.trim().isEmpty()){
                throw new Exception("Nome de aluno não pode ser vazio!");
            }else if(alunoStr.trim().length() < 2){
                throw new Exception( alunoStr + " não é um nome válido!");
            }

            alunosStr.add(alunoStr);
        }

        if(alunosStr.isEmpty()){
            throw new Exception("Você precisa informar pelo menos um aluno!");
        }

        return alunosStr;
    }

    private void saveNomeAlunos(List<String> alunosStr, int entradaId) {
        EntradaAlunoDtoDao.insertAll(getActivity(), alunosStr.stream().map( s -> new EntradaAlunoDto(s, entradaId)).collect(Collectors.toList()));
    }

    private EntradaDto updateEntrada(){
        EntradaDto entradaDto = EntradaDto.getEntradaAtual(getActivity());
        entradaDto.setObservacao(textInputObservacao.getText().toString());
        entradaDto.setFinalizada(true);
        EntradaDtoDao.save(getActivity(), entradaDto);

        return entradaDto;
    }

    private boolean isJaSalvo() {
        return jaSalvo;
    }

    private void setJaSalvo(boolean jaSalvo) {
        this.jaSalvo = jaSalvo;
    }

    public static EntradaAlunoFragment newInstance(){
        return new EntradaAlunoFragment();
    }
}
