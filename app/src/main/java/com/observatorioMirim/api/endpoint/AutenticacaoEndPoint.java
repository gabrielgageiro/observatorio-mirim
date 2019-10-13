package com.observatorioMirim.api.endpoint;

import com.observatorioMirim.api.models.escola.Escola;
import com.observatorioMirim.api.models.produto.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AutenticacaoEndPoint {

    @GET("/api/escola/listar")
    Call<List<Escola>> GetEscolas();

    @GET("/api/escola/login")
    Call<Escola> GetEscolasByCodigoSenha(@Query("usuario_smart") final String usuario_smart, @Query("senha_smart") final String senha_smart);

    @POST("/api/escola/produtos/incluir")
    Call<List<Produto>> saveProdutos(@Body List<Produto> produtos);

    @GET("/api/escola/produtos/listar")
    Call<List<Produto>> GetProdutos(@Query("id_conta") final Integer idConta, @Query("id_escola") final Integer idEscola);
}
