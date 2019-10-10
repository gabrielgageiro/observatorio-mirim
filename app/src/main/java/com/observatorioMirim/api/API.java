package com.observatorioMirim.api;

import com.observatorioMirim.api.endpoint.AutenticacaoEndPoint;
import com.observatorioMirim.api.models.Escola;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    private static final String URL = "http://apiescola.nextcodeapp.com.br";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create()).build();

    public static void getEscolas(final Callback<List<Escola>> callback) {
        AutenticacaoEndPoint endPoint = retrofit.create(AutenticacaoEndPoint.class);
        Call<List<Escola>> call;

        call = endPoint.GetEscolas();
        call.enqueue(callback);
    }

    public static void getEscolaByCodigoSenha(final String usuario_smart, final String senha_smart, Callback<Escola> callback) {
        AutenticacaoEndPoint endPoint = retrofit.create(AutenticacaoEndPoint.class);
        Call<Escola> call;

        call = endPoint.GetEscolasByCodigoSenha(usuario_smart, senha_smart);
        call.enqueue(callback);
    }
}
