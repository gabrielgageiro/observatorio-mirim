package com.observatorioMirim.saida.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.observatorioMirim.api.API;
import com.observatorioMirim.api.models.saida.Saida;
import com.observatorioMirim.utils.AbstractFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaidaList {

    private static final String TITULO = "Entradas";

    public static void open(@NonNull final AppCompatActivity activity){

        API.getSaidasHoje(new Callback<List<Saida>>() {
            @Override
            public void onResponse(Call<List<Saida>> call, Response<List<Saida>> response) {
                if(response != null && response.body() != null && !response.body().isEmpty()){
                    AbstractFragment.openFragmentFromActivity(activity, SaidaListFragment.newInstance((ArrayList<Saida>) response.body()), TITULO);
                }else{
                    ArrayList<Saida> saidas = new ArrayList<>();
                    AbstractFragment.openFragmentFromActivity(activity, SaidaListFragment.newInstance(saidas), TITULO);
                }
            }

            @Override
            public void onFailure(Call<List<Saida>> call, Throwable t) {

            }
        });
    }
}
