package com.observatorioMirim.api.endpoint;

import com.observatorioMirim.api.models.RespostaEscola;
import com.observatorioMirim.api.models.fornecedor.Fornecedor;
import com.observatorioMirim.api.models.produto.Produto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FornecedorEndPoint {

    @POST("api/escola/fornecedores/incluir")
    Call<RespostaEscola> postFornecedor(@Body Fornecedor[] fornecedor);

    @GET("/api/escola/fornecedores/listar")
    Call<List<Fornecedor>> getFornecedores(@Query("id_conta") final Integer idConta, @Query("id_escola") final Integer idEscola);
}
