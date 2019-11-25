package com.observatorioMirim.cadastro.produto;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.observatorioMirim.MainActivity;
import com.observatorioMirim.R;
import com.observatorioMirim.cadastro.aluno.AlunoFragment;
import com.observatorioMirim.utils.AbstractFragment;
import com.observatorioMirim.utils.ListFrament;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProdutoListFragment extends AbstractFragment implements ListFrament {
    private RecyclerView recyclerView;
    private ProdutoAdapter produtoAdapter;
    private boolean isLoading = false;
    private List<Produto> linhas = new ArrayList<>(Arrays.asList(new Produto("90","91","92"),new Produto("93","94","95"),new Produto("97","98","99")));

    public ProdutoListFragment(Integer key, String titulo) {
        super(key, titulo);
    }
    public static ProdutoListFragment create(){
        return new ProdutoListFragment(R.layout.view_list, "Produtos");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        View view = super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = view.findViewById(R.id.view_list);
        recyclerView.setLayoutManager(layoutManager);
        produtoAdapter = new ProdutoAdapter(linhas);
        recyclerView.setAdapter(produtoAdapter);
        initScrollListener();

        MainActivity main = (MainActivity) getActivity();
        main.getBottomNavigationView().setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void initScrollListener() {
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

    @Override
    public void loadItem() {//todo pegar da api
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            linhas.remove(linhas.size() - 1);
            int scrollPosition = linhas.size();
            produtoAdapter.notifyItemRemoved(scrollPosition);

            int limite = scrollPosition + 10; //limite e o tamanho atual da lista + 10

            while (scrollPosition - 1 < limite) {
                produtoAdapter.addLine(linhas.get(scrollPosition-1));
                scrollPosition++;
            }

            produtoAdapter.notifyDataSetChanged();
            isLoading = false;
        }, 2000);
    }
}
