package com.observatorioMirim.saida.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.observatorioMirim.R;
import com.observatorioMirim.api.models.saida.Saida;

import java.util.ArrayList;

public class SaidaListFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        ArrayList<Saida> saidas = (ArrayList<Saida>) getArguments().getSerializable("saidas");

        if(saidas.isEmpty()){
            return inflater.inflate(R.layout.fragment_list_saida_vazio, container, false);
        }

        View view = inflater.inflate(R.layout.fragment_list_saida, container, false);
        ListView listaSaida = view.findViewById(R.id.list_saida);

        SaidaListAdapter saidaListAdapter = new SaidaListAdapter(getActivity(), saidas);
        listaSaida.setAdapter(saidaListAdapter);

        return view;
    }

    public static SaidaListFragment newInstance(final ArrayList<Saida> saidas){

        SaidaListFragment saidaListFragment = new SaidaListFragment();

        Bundle args = new Bundle();
        args.putSerializable("saidas", saidas);
        saidaListFragment.setArguments(args);

        return saidaListFragment;
    }

}
