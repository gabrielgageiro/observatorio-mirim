package com.observatorioMirim.views.upload.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.observatorioMirim.R;
import com.observatorioMirim.api.API;
import com.observatorioMirim.api.models.RespostaEscola;
import com.observatorioMirim.api.models.entrada.Entrada;
import com.observatorioMirim.api.models.entrada.EntradaDto;
import com.observatorioMirim.api.models.entrada.EntradaDtoDB;
import com.observatorioMirim.utils.SweetUtils;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadFragment extends Fragment {

    private TextView textQuantidade;
    private Button buttonSincronizar;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        int total = EntradaDtoDB.countSincronizacoesPendentes(getActivity());
        if(total < 1){
            return inflater.inflate(R.layout.fragment_upload_vazio, container, false);
        }

        View view = inflater.inflate(R.layout.fragment_upload, container, false);

        textQuantidade = view.findViewById(R.id.sincronizar_quantidade);
        textQuantidade.setText("Você possui " + total + " entradas pendentes!");

        buttonSincronizar = view.findViewById(R.id.sincronizar);
        buttonSincronizar.setOnClickListener( o -> {

            List<EntradaDto> entradaDtos = EntradaDtoDB.listAllPendentes(getActivity());

            for(EntradaDto e : entradaDtos){
                Entrada entrada = Entrada.generateEntrada(getActivity(), e);

                API.postEntrada(entrada, new Callback<RespostaEscola>() {
                    @Override
                    public void onResponse(Call<RespostaEscola> call, Response<RespostaEscola> response) {
                        EntradaDtoDB.delete(getActivity(), e.getId());
                    }

                    @Override
                    public void onFailure(Call<RespostaEscola> call, Throwable t) {
                        SweetUtils.message(getActivity(), "Erro", "Não foi possível sincronizar, verifique sua conexão com a internet.", SweetAlertDialog.ERROR_TYPE);
                    }
                });
            }

        });

        return view;
    }

    public static UploadFragment newInstance(){
        return new UploadFragment();
    }

}
