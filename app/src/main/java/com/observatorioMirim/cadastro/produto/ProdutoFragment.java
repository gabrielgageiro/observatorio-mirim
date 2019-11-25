package com.observatorioMirim.cadastro.produto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.observatorioMirim.MainActivity;
import com.observatorioMirim.R;
import com.observatorioMirim.utils.AbstractFragment;

public class ProdutoFragment extends AbstractFragment {

    public ProdutoFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static ProdutoFragment create(){
        return new ProdutoFragment(R.layout.produto_view, "Produtos");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
