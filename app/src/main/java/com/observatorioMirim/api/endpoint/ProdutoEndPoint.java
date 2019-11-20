package com.observatorioMirim.api.endpoint;

import com.observatorioMirim.api.models.RespostaEscola;
import com.observatorioMirim.api.models.produto.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProdutoEndPoint {

    @POST("api/escola/produtos/incluir")
    Call<RespostaEscola> postProduto(@Body Produto[] produto);

    @GET("/api/escola/produtos/listar")
    Call<List<Produto>> getProdutos(@Query("id_conta") final Integer idConta, @Query("id_escola") final Integer idEscola);

    @GET("api/escola/produtos/especifico/nome")
    Call<Produto> getProdutoByNome(@Query("id_conta") final Integer idConta, @Query("id_escola") final Integer idEscola, @Query("nome") final String nomeProduto);

    @GET("api/escola/produtos/especifico")
    Call<Produto> getProdutoById(@Query("id_conta") final Integer idConta, @Query("id_escola") final Integer idEscola, @Query("id") final Integer idProduto);

    @GET("api/escola/produtos/especifico/codigobarras")
    Call<Produto> getProdutoByCodigoBarras(@Query("id_conta") final Integer idConta, @Query("id_escola") final Integer idEscola, @Query("codigo_barras") final String codigoBarras);
}

