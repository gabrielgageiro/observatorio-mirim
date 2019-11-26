package com.observatorioMirim.views.entrada.produto.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.observatorioMirim.MainActivity;
import com.observatorioMirim.R;
import com.observatorioMirim.api.models.produto.ProdutoDto;

import java.util.ArrayList;

public class EntradaProdutoListFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        MainActivity main = (MainActivity) getActivity();
        main.getBottomNavigationView().setVisibility(View.GONE);

        ArrayList<ProdutoDto> produtos = (ArrayList<ProdutoDto>) getArguments().getSerializable("produtos");

        View view = inflater.inflate(R.layout.fragment_list_produto, container, false);
        ListView listaProduto = view.findViewById(R.id.list_produto);

        EntradaProdutoListAdapter saidaListAdapter = new EntradaProdutoListAdapter(getActivity(), produtos);
        listaProduto.setAdapter(saidaListAdapter);

        return view;
    }

    public static EntradaProdutoListFragment newInstance(final ArrayList<ProdutoDto> produtos){

        EntradaProdutoListFragment entradaProdutoListFragment = new EntradaProdutoListFragment();

        Bundle args = new Bundle();
        args.putSerializable("produtos", produtos);
        entradaProdutoListFragment.setArguments(args);

        return entradaProdutoListFragment;
    }

}
