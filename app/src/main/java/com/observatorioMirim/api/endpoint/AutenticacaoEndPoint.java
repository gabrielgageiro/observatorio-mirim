package com.observatorioMirim.api.endpoint;

import com.observatorioMirim.api.models.escola.Escola;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AutenticacaoEndPoint {

    @GET("api/escola/listar")
    Call<List<Escola>> GetEscolas();

    @GET("/api/escola/login")
    Call<Escola> GetEscolasByCodigoSenha(@Query("usuario_smart") final String usuario_smart, @Query("senha_smart") final String senha_smart);
}
