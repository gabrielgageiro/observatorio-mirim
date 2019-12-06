package com.observatorioMirim.views.entrada.produto.list;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.observatorioMirim.MainActivity;
import com.observatorioMirim.R;
import com.observatorioMirim.api.models.entrada.item.EntradaItemDto;
import com.observatorioMirim.api.models.entrada.item.EntradaItemDtoDao;
import com.observatorioMirim.utils.Contexto;
import com.observatorioMirim.views.entrada.aluno.EntradaAluno;
import com.observatorioMirim.utils.SweetUtils;
import com.observatorioMirim.views.entrada.produto.item.EntradaProdutoItem;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class EntradaProdutoListFragment extends Fragment {
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    public static final String TAG = "EntradaProdutoListFragment";
    ArrayList<EntradaItemDto> produtos;
    EntradaProdutoListAdapter saidaListAdapter;
    ListView listaProduto;
    Button buttonTerminei;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        MainActivity main = (MainActivity) getActivity();
        main.getBottomNavigationView().setVisibility(View.GONE);

        View view = inflater.inflate(R.layout.fragment_list_produto, container, false);

        setHasOptionsMenu(true);

        produtos = EntradaItemDtoDao.listByEntrada(getContext(), Contexto.getIdEntradaAtual(getActivity()));

        saidaListAdapter = new EntradaProdutoListAdapter(getActivity(), produtos);
        listaProduto = view.findViewById(R.id.list_produto);
        listaProduto.setAdapter(saidaListAdapter);

        buttonTerminei = view.findViewById(R.id.entrada_produto_item_dar_entrada);
        buttonTerminei.setOnClickListener(o -> {
            EntradaAluno.open((MainActivity) getActivity());
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.searchable, menu);
        initSearchButton(menu);
        initAdicionarNoVoProdutoButton(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initAdicionarNoVoProdutoButton(Menu menu) {
            MenuItem addProdutoItem = menu.findItem(R.id.add_novo_produto);

            addProdutoItem.setOnMenuItemClickListener(item -> {

            EntradaProdutoItem.open((AppCompatActivity) getContext(), null); // necessario mandar null
            return true;
        });
    }

    private void initSearchButton(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();

            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        }

        queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                query = query.toLowerCase();
                final List<EntradaItemDto> filtro = new ArrayList<>();

                for (EntradaItemDto p : produtos) {
                    String text = p.getNome().toLowerCase();

                    if (text.contains(query)) {
                        filtro.add(p);
                    }
                }

                listaProduto.setAdapter(new EntradaProdutoListAdapter(getActivity(), filtro));
                saidaListAdapter.notifyDataSetChanged();
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MainActivity main = (MainActivity) getActivity();
        main.getBottomNavigationView().setVisibility(View.VISIBLE);
        main.getSupportActionBar().setTitle("Entradas");
    }

    public static EntradaProdutoListFragment newInstance() {
        return new EntradaProdutoListFragment();
    }

}
