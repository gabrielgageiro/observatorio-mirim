package com.observatorioMirim.views.entrada.produto.item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.observatorioMirim.R;
import com.observatorioMirim.api.models.produto.ProdutoDto;

public class EntradaProdutoItemFragment extends Fragment {

    private TextView nome;
    private TextInputEditText marca;
    private TextInputEditText diaValidade;
    private TextInputEditText mesValidade;
    private TextInputEditText anoValidade;
    private TextInputEditText quantidade;
    private TextInputEditText unidade;
    private TextInputEditText observacao;
    private Button darEntrada;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        ProdutoDto produto = (ProdutoDto) getArguments().getSerializable("produto");

        View view = inflater.inflate(R.layout.fragment_item_produto, container, false);


        nome = view.findViewById(R.id.entrada_produto_item_nome);
        nome.setText(produto.getNome());

        marca = view.findViewById(R.id.entrada_produto_item_marca);

        diaValidade = view.findViewById(R.id.entrada_produto_item_validade_dia);
        mesValidade = view.findViewById(R.id.entrada_produto_item_validade_mes);
        anoValidade = view.findViewById(R.id.entrada_produto_item_validade_ano);

        quantidade = view.findViewById(R.id.entrada_produto_item_quantidade);
        unidade = view.findViewById(R.id.entrada_produto_item_unidade);

        observacao = view.findViewById(R.id.entrada_produto_item_observacao);


        darEntrada = view.findViewById(R.id.entrada_produto_item_dar_entrada);
        darEntrada.setOnClickListener( o -> {

        });


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
