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
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.observatorioMirim.MainActivity;
import com.observatorioMirim.R;
import com.observatorioMirim.api.models.produto.Produto;
import com.observatorioMirim.api.models.produto.ProdutoDto;

import java.util.ArrayList;
import java.util.List;

public class EntradaProdutoListFragment extends Fragment {
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    public static final String TAG = "EntradaProdutoListFragment";
    ArrayList<ProdutoDto> produtos;
    EntradaProdutoListAdapter saidaListAdapter;
    ListView listaProduto;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        MainActivity main = (MainActivity) getActivity();
        main.getBottomNavigationView().setVisibility(View.GONE);
        produtos = (ArrayList<ProdutoDto>) getArguments().getSerializable("produtos");

        View view = inflater.inflate(R.layout.fragment_list_produto, container, false);
        listaProduto = view.findViewById(R.id.list_produto);

        saidaListAdapter = new EntradaProdutoListAdapter(getActivity(), produtos);
        listaProduto.setAdapter(saidaListAdapter);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.searchable, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if(searchItem != null){
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
                final List<ProdutoDto> filtro = new ArrayList<>();

                for (ProdutoDto p : produtos){
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
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        //TODO: Exibir popup confirmando

        MainActivity main = (MainActivity) getActivity();
        main.getBottomNavigationView().setVisibility(View.VISIBLE);
        main.getSupportActionBar().setTitle("Entradas");
    }

    public static EntradaProdutoListFragment newInstance(final ArrayList<ProdutoDto> produtos){

        EntradaProdutoListFragment entradaProdutoListFragment = new EntradaProdutoListFragment();

        Bundle args = new Bundle();
        args.putSerializable("produtos", produtos);
        entradaProdutoListFragment.setArguments(args);

        return entradaProdutoListFragment;
    }

}
