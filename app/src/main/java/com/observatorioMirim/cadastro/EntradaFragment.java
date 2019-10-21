package com.observatorioMirim.cadastro;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class EntradaFragment extends AbstractFragment {
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
        final ChipGroup entryChipGroup = view.findViewById(R.id.entry_chip_group);
        final Chip entryChip = getChip(entryChipGroup, "Hello World");
        final Chip entryChip2 = getChip(entryChipGroup, "Test");
        entryChipGroup.addView(entryChip);
        entryChipGroup.addView(entryChip2);


        return view;
    }

    private Chip getChip(final ChipGroup entryChipGroup, String text) {

        final Chip chip = new Chip(getActivity());
        chip.setChipDrawable(ChipDrawable.createFromResource(getActivity(), R.xml.chip));
        int paddingDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10,
                getResources().getDisplayMetrics()
        );
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
        chip.setText(text);
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entryChipGroup.removeView(chip);
            }
        });
        return chip;
    }
}
