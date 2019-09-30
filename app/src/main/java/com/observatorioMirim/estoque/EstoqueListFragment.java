package com.observatorioMirim.estoque;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.observatorioMirim.R;
import com.observatorioMirim.utils.AbstractFragment;

import java.util.ArrayList;

public class EstoqueListFragment extends AbstractFragment {
    public EstoqueListFragment(Integer key, String titulo) {
        super(key, titulo);
    }
    private RecyclerView recyclerView;
    private LineAdapter lineAdapter;

    public static EstoqueListFragment create(){
        return new EstoqueListFragment(R.layout.produto_list, "Cadastro");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        View view = super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = view.findViewById(R.id.produto_list);
        recyclerView.setLayoutManager(layoutManager);
        lineAdapter = new LineAdapter(new ArrayList<Produto>());
        recyclerView.setAdapter(lineAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = new RecyclerView(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        lineAdapter = new LineAdapter(new ArrayList<Produto>());
        recyclerView.setAdapter(lineAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

}
