package com.observatorioMirim.api.endpoint;

import com.observatorioMirim.api.models.saida.Saida;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SaidaEndPoint {
    @GET("api/escola/saida/listar")
    Call<List<Saida>> getSaidas(@Query("id_conta") final Integer idConta, @Query("id_escola") final Integer idEscola);

    @GET("api/escola/saida/listar/hoje")
    Call<List<Saida>> getSaidasHoje(@Query("id_conta") final Integer idConta, @Query("id_escola") final Integer idEscola);
}
