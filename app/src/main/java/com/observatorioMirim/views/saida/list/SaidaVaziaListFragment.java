package com.observatorioMirim.views.saida.list;

import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.observatorioMirim.utils.AbstractFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SaidaVaziaListFragment extends Fragment {
    SwipeRefreshLayout refreshLayout;
    ListView listaSaida;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        MainActivity activity = (MainActivity) getActivity();
        activity.getBottomNavigationView().setVisibility(View.VISIBLE);

        View view = inflater.inflate(R.layout.fragment_list_saida_vazio, container, false);
        listaSaida = view.findViewById(R.id.list_saida_vazio);
        SaidaVaziaListAdapter saidaListAdapter = new SaidaVaziaListAdapter(getActivity(), Collections.singletonList("s"));
        listaSaida.setAdapter(saidaListAdapter);

        refreshLayout = view.findViewById(R.id.swipe_refresh_saida);
        refreshLayout.setOnRefreshListener(() -> {
            SaidaList.open((MainActivity) getActivity());
            refreshLayout.setRefreshing(false);
        });

        return view;
    }

    public static SaidaVaziaListFragment newInstance() {
        return new SaidaVaziaListFragment();
    }
}
