package com.observatorioMirim.api.endpoint;

import com.observatorioMirim.api.models.Escola;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AutenticacaoEndPoint {

    @GET("api/escola/listar")
    Call<List<Escola>> GetEscolas();

    @POST("/api/escola/buscar")
    Call<Escola> GetEscolasByCodigoSenha(@Body Escola escola);
}
