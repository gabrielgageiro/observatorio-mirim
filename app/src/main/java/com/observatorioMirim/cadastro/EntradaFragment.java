package com.observatorioMirim.cadastro;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.observatorioMirim.R;
import com.observatorioMirim.estoque.LineAdapter;
import com.observatorioMirim.utils.AbstractFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EntradaFragment extends AbstractFragment {
    private List<String> listaAlunos = Arrays.asList("Love", "Passion", "Peace", "Hello", "Test");
    public EntradaFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static EntradaFragment create(){
        return new EntradaFragment(R.layout.activity_entrada, "Entrada");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        /*Entry chip section*/
        System.out.println("EUIAOHAIUEHEASOIUEA");
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.mainTagAutoCompleteTextView);
        ChipGroup chipGroup = view.findViewById(R.id.mainTagChipGroup);
        loadTagsUi(autoCompleteTextView, chipGroup, new ArrayList<String>(), listaAlunos);
        return view;
    }

    private void loadTagsUi(AutoCompleteTextView autoCompleteTextView, ChipGroup chipGroup, List<String> atuaisChips, List<String> allAlunos) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, allAlunos);
        autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.setOnItemClickListener((adapterView, view, position, id) -> {
            String nome = adapterView.getItemAtPosition(position).toString();
            addChipToGroup(nome, chipGroup, atuaisChips);
        });

        autoCompleteTextView.setOnEditorActionListener((textView, actionId, keyEvent) ->{
            if(actionId == EditorInfo.IME_ACTION_DONE){
                addChipToGroup(textView.getText().toString(), chipGroup, atuaisChips);
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
                System.out.println("OIl");
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
        chip.setClickable(true);;
        chip.setCloseIconVisible(true);
        chipGroup.addView(chip);
        chip.setOnCloseIconClickListener(o->{
            chipGroup.removeView(chip);
            items.remove(nome);
        });
    }
//
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = super.onCreateView(inflater, container, savedInstanceState);
//        /*Entry chip section*/
//        System.out.println("EUIAOHAIUEHEASOIUEA");
//        final ChipGroup entryChipGroup = view.findViewById(R.id.entry_chip_group);
//        final Chip entryChip = getChip(entryChipGroup, "Hello World");
//        final Chip entryChip2 = getChip(entryChipGroup, "Test");
//        entryChipGroup.addView(entryChip);
//        entryChipGroup.addView(entryChip2);
//
//
//        return view;
//    }
//
//    private Chip getChip(final ChipGroup entryChipGroup, String text) {
//
//        final Chip chip = new Chip(getActivity());
//        chip.setChipDrawable(ChipDrawable.createFromResource(getActivity(), R.xml.chip));
//        int paddingDp = (int) TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP, 10,
//                getResources().getDisplayMetrics()
//        );
//        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
//        chip.setText(text);
//        chip.setOnCloseIconClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                entryChipGroup.removeView(chip);
//            }
//        });
//        return chip;
//    }
}
