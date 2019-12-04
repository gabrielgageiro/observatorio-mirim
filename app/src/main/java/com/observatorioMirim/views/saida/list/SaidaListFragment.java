package com.observatorioMirim.views.saida.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.observatorioMirim.MainActivity;
import com.observatorioMirim.R;
import com.observatorioMirim.api.models.saida.Saida;

import java.util.ArrayList;

public class SaidaListFragment extends Fragment {
    SwipeRefreshLayout refreshLayout;
    ArrayList<Saida> saidas;
    ListView listaSaida;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        ((MainActivity) getActivity()).getBottomNavigationView().setVisibility(View.VISIBLE);

        saidas = (ArrayList<Saida>) getArguments().getSerializable("saidas");

        if (saidas.isEmpty()) {
            return inflater.inflate(R.layout.fragment_list_saida_vazio, container, false);
        }

        View view = inflater.inflate(R.layout.fragment_list_saida, container, false);
        listaSaida = view.findViewById(R.id.list_saida);

        SaidaListAdapter saidaListAdapter = new SaidaListAdapter(getActivity(), saidas);
        listaSaida.setAdapter(saidaListAdapter);

        setHasOptionsMenu(true);

        refreshLayout = view.findViewById(R.id.swipe_refresh_saida);
        refreshLayout.setOnRefreshListener(() -> {
            saidas = (ArrayList<Saida>) getArguments().getSerializable("saidas");

            SaidaList.open((MainActivity) getActivity());
            listaSaida.setAdapter(new SaidaListAdapter(getActivity(), saidas));
            refreshLayout.setRefreshing(false);
        });

        return view;
    }

    public static SaidaListFragment newInstance(final ArrayList<Saida> saidas) {

        SaidaListFragment saidaListFragment = new SaidaListFragment();

        Bundle args = new Bundle();
        args.putSerializable("saidas", saidas);
        saidaListFragment.setArguments(args);

        return saidaListFragment;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.buttons_entradas, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
