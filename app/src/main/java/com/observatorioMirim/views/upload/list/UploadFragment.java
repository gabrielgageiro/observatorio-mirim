package com.observatorioMirim.views.upload.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.observatorioMirim.MainActivity;
import com.observatorioMirim.R;
import com.observatorioMirim.api.API;
import com.observatorioMirim.api.models.RespostaEscola;
import com.observatorioMirim.api.models.entrada.Entrada;
import com.observatorioMirim.api.models.entrada.EntradaDto;
import com.observatorioMirim.api.models.entrada.EntradaDtoDao;
import com.observatorioMirim.api.models.entrada.item.EntradaItemDto;
import com.observatorioMirim.api.models.entrada.item.EntradaItemDtoDao;
import com.observatorioMirim.utils.SweetUtils;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadFragment extends Fragment {

    private TextView textQuantidade;
    private Button buttonSincronizar;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        int total = EntradaItemDtoDao.countItensPendentes(getActivity());
        if(total < 1){
            return inflater.inflate(R.layout.fragment_upload_vazio, container, false);
        }

        View view = inflater.inflate(R.layout.fragment_upload, container, false);

        boolean maisQueUm = total > 1;

        textQuantidade = view.findViewById(R.id.sincronizar_quantidade);
        textQuantidade.setText("Você possui " + total + " ite" + (maisQueUm ? "ns" : "m") + " não enviado" + (maisQueUm ? "s" : "") + "!");

        buttonSincronizar = view.findViewById(R.id.sincronizar);
        buttonSincronizar.setOnClickListener( o -> {

            SweetUtils.loaderNativo(getActivity(), "Sincronizando", "Aguarde enquanto os itens são enviados.");

            List<EntradaDto> entradaDtos = EntradaDtoDao.listAllPendentes(getActivity());

            enviarEntrada(entradaDtos.iterator());
        });

        return view;
    }

    private void enviarEntrada(Iterator<EntradaDto> iterator){

        EntradaDto entradaDto = iterator.next();

        API.postEntrada(Entrada.generateEntrada(getActivity(), entradaDto), new Callback<RespostaEscola>() {
            @Override
            public void onResponse(Call<RespostaEscola> call, Response<RespostaEscola> response) {
                List<EntradaItemDto> itens = EntradaItemDtoDao.listByEntrada(getActivity(), entradaDto.getId());
                itens.forEach( i -> {
                    if(i.isPreenchido()){
                        i.setUpload(true);
                        EntradaItemDtoDao.save(getActivity(), i);
                    }
                });

                if(iterator.hasNext()){
                    enviarEntrada(iterator);
                }else{
                    SweetUtils.cancelarLoaderNativo();
                    Toast.makeText(getContext(), "Sincronizado com sucesso!", Toast.LENGTH_LONG).show();
                    Upload.open((MainActivity) getActivity());
                }
            }

            @Override
            public void onFailure(Call<RespostaEscola> call, Throwable t) {
                SweetUtils.cancelarLoaderNativo();
                SweetUtils.message(getActivity(), "Erro", "Não foi possível sincronizar, verifique sua conexão com a internet.", SweetAlertDialog.ERROR_TYPE);
            }
        });
    }

    public static UploadFragment newInstance(){
        return new UploadFragment();
    }

}
