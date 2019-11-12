package com.observatorioMirim.estoque;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.observatorioMirim.R;
import com.observatorioMirim.utils.AbstractFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EstoqueListFragment extends AbstractFragment {
    private RecyclerView recyclerView;
    private LineAdapter lineAdapter;
    private boolean isLoading = false;
    private List<Integer> linhas = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));

    public EstoqueListFragment(Integer key, String titulo) {
        super(key, titulo);
    }

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
        lineAdapter = new LineAdapter(linhas);
        recyclerView.setAdapter(lineAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        initScrollListener();

        return view;
    }

    private void initScrollListener() {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == linhas.size() - 1) {
                        //bottom of list!
                        loadItem();
                        isLoading = true;
                    }
                }
            }
        });

    }

    private void loadItem() {//todo pegar da api
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            linhas.remove(linhas.size() - 1);
                int scrollPosition = linhas.size();
            lineAdapter.notifyItemRemoved(scrollPosition);

            int limite = scrollPosition + 10; //limite e o tamanho atual da lista + 10

                while (scrollPosition - 1 < limite) {
                    lineAdapter.addProduto(scrollPosition);
                    scrollPosition++;
                }

                lineAdapter.notifyDataSetChanged();
                isLoading = false;
        }, 2000);
    }
}