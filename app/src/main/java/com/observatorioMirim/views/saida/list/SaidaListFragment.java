package com.observatorioMirim.views.saida.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.observatorioMirim.LoginActivity;
import com.observatorioMirim.MainActivity;
import com.observatorioMirim.R;
import com.observatorioMirim.utils.Contexto;
import com.observatorioMirim.utils.Shared;
import com.observatorioMirim.utils.SweetUtils;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.observatorioMirim.utils.AbstractFragment;

import java.security.acl.Group;

public class SaidaListFragment extends Fragment {
    SwipeRefreshLayout refreshLayout;
    ListView listaSaida;
    Group colaboradores;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        MainActivity activity = (MainActivity) getActivity();
        activity.getBottomNavigationView().setVisibility(View.VISIBLE);

        SweetUtils.cancelarLoaderNativo();

        if (Contexto.getIdEntradaAtual(getContext()) < 0) {
            AbstractFragment.openFragmentFromActivity(activity, SaidaVaziaListFragment.newInstance(),"Entradas");
        }

        View view = inflater.inflate(R.layout.fragment_list_saida, container, false);
        listaSaida = view.findViewById(R.id.list_saida);

        SaidaListAdapter saidaListAdapter = new SaidaListAdapter(getActivity());
        listaSaida.setAdapter(saidaListAdapter);

        setHasOptionsMenu(true);

        refreshLayout = view.findViewById(R.id.swipe_refresh_saida);
        refreshLayout.setOnRefreshListener(() -> {

            SaidaList.open((MainActivity) getActivity());
            refreshLayout.setRefreshing(false);
        });

        return view;
    }

    public static SaidaListFragment newInstance() {
        return new SaidaListFragment();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.buttons_entradas, menu);
        initButtons(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initButtons(Menu menu) {
        MenuItem colaboradores = menu.findItem(R.id.colaboradores);
        MenuItem logout = menu.findItem(R.id.logout);

        colaboradores.setOnMenuItemClickListener(item -> {
            SweetUtils.message(getContext(), "Colaboradores","Aplicativo desenvolvido pelos alunos do curso de Ciência da Computação em conjunto ao curso de Ciências Contábeis.\n UNESC - 2019.", SweetAlertDialog.CUSTOM_IMAGE_TYPE);
            return true;
        });

        logout.setOnMenuItemClickListener(item -> {
            SweetUtils.confirmDialog(getContext(), "Confirme sua ação", "Deseja realmente sair?", "Confirmar", "Cancelar",
                    (SweetAlertDialog sDialog) -> {
                        Shared.putBoolean(getContext(), "logarAutomaticamente", false);
                        Intent it = new Intent(getContext(), LoginActivity.class);
                        startActivity(it);
                    },

                    SweetAlertDialog::dismissWithAnimation);
            return true;
        });
    }
}
