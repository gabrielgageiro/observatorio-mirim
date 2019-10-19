package com.observatorioMirim.api;

import com.observatorioMirim.api.endpoint.AutenticacaoEndPoint;
import com.observatorioMirim.api.endpoint.FornecedorEndPoint;
import com.observatorioMirim.api.endpoint.ProdutoEndPoint;
import com.observatorioMirim.api.models.RespostaEscola;
import com.observatorioMirim.api.models.escola.Escola;
import com.observatorioMirim.api.models.fornecedor.Fornecedor;
import com.observatorioMirim.api.models.produto.Produto;

import java.util.Date;
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

    public static void postProdutos(final Produto[] produtos, final Callback<RespostaEscola> callback){
        ProdutoEndPoint endPoint = retrofit.create(ProdutoEndPoint.class);
        Call<RespostaEscola> call = endPoint.postProduto(produtos);
        call.enqueue(callback);
    }

    public static void getProdutos(final Integer idConta, final Integer idEscola, final Callback<List<Produto>> callback){
        ProdutoEndPoint endPoint = retrofit.create(ProdutoEndPoint.class);
        Call<List<Produto>> call;
        call = endPoint.GetProdutos(idConta, idEscola);
        call.enqueue(callback);
    }

    public static void postFornecedor(final Fornecedor[] fornecedores, final Callback<RespostaEscola> callback){
        FornecedorEndPoint endPoint = retrofit.create(FornecedorEndPoint.class);
        Call<RespostaEscola> call = endPoint.postFornecedor(fornecedores);
        call.enqueue(callback);
    }

    public static void getFornecedores(final Integer idConta, final Integer idEscola, final Callback<List<Fornecedor>> callback){
        FornecedorEndPoint endPoint = retrofit.create(FornecedorEndPoint.class);
        Call<List<Fornecedor>> call;
        call = endPoint.getFornecedores(idConta, idEscola);
        call.enqueue(callback);
    }
}
//TODO: organizar os get e saves nos devidos lugares (services)
