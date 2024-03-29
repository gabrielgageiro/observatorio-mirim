package com.observatorioMirim.api.endpoint;

import com.observatorioMirim.api.models.RespostaEscola;
import com.observatorioMirim.api.models.entrada.Entrada;
import com.observatorioMirim.api.models.entrada.aluno.EntradaAluno;
import com.observatorioMirim.api.models.entrada.item.EntradaItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EntradaEndPoint {
    @POST("api/escola/entrada/incluir")
    Call<RespostaEscola> postEntrada(@Body Entrada entradas);

    @POST("api/escola/entrada/aluno/incluir")
    Call<RespostaEscola> postEntradaAluno(@Body EntradaAluno entradaAluno);

    @POST("api/escola/entrada/item/incluir")
    Call<RespostaEscola> postEntradaItem(@Body EntradaItem entradaItems);

    @GET("api/escola/entrada/listar")
    Call<List<Entrada>> getEntradas(@Query("id_conta") final Integer idConta, @Query("id_escola") final Integer idEscola);

}
