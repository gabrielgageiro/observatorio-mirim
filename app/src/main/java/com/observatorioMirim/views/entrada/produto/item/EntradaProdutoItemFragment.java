package com.observatorioMirim.views.entrada.produto.item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.observatorioMirim.MainActivity;
import com.observatorioMirim.R;
import com.observatorioMirim.api.models.produto.ProdutoDto;
import com.observatorioMirim.views.entrada.produto.list.EntradaProdutoListAdapter;

import java.util.ArrayList;

public class EntradaProdutoItemFragment extends Fragment {

    private TextView entradaProdutoItemNome;
    private Button button;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        ProdutoDto produto = (ProdutoDto) getArguments().getSerializable("produto");

        View view = inflater.inflate(R.layout.fragment_item_produto, container, false);


        entradaProdutoItemNome = view.findViewById(R.id.entrada_produto_item_nome);
        entradaProdutoItemNome.setText(produto.getNome());

//        button = view.findViewById(R.id.oi);
//        button.setOnClickListener( o -> {
//
//        });


        return view;
    }

    public static EntradaProdutoItemFragment newInstance(final ProdutoDto produto){

        EntradaProdutoItemFragment entradaProdutoListFragment = new EntradaProdutoItemFragment();

        Bundle args = new Bundle();
        args.putSerializable("produto", produto);
        entradaProdutoListFragment.setArguments(args);

        return entradaProdutoListFragment;
    }

}
