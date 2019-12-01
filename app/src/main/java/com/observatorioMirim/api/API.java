package com.observatorioMirim.api;

import android.content.Context;

import com.observatorioMirim.api.endpoint.AutenticacaoEndPoint;
import com.observatorioMirim.api.endpoint.EntradaEndPoint;
import com.observatorioMirim.api.endpoint.FornecedorEndPoint;
import com.observatorioMirim.api.endpoint.ProdutoEndPoint;
import com.observatorioMirim.api.endpoint.SaidaEndPoint;
import com.observatorioMirim.api.models.RespostaEscola;
import com.observatorioMirim.api.models.entrada.Entrada;
import com.observatorioMirim.api.models.entrada.EntradaAluno;
import com.observatorioMirim.api.models.entrada.EntradaItem;
import com.observatorioMirim.api.models.escola.Escola;
import com.observatorioMirim.api.models.fornecedor.Fornecedor;
import com.observatorioMirim.api.models.produto.Produto;
import com.observatorioMirim.api.models.saida.Saida;
import com.observatorioMirim.utils.Shared;

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

    //Retornar uma lista / Array ?
    public static void getProdutos(Context context, final Callback<List<Produto>> callback){
        ProdutoEndPoint endPoint = retrofit.create(ProdutoEndPoint.class);
        Call<List<Produto>> call;
        call = endPoint.getProdutos(Shared.getInt(context, "idConta"), Shared.getInt(context, "idEscola"));
        call.enqueue(callback);
    }

    public static void getProdutoByNome(Context context, final String nomeProduto, final Callback<Produto> callback){
        ProdutoEndPoint endPoint = retrofit.create(ProdutoEndPoint.class);
        Call<Produto> call;
        call = endPoint.getProdutoByNome(Shared.getInt(context, "idConta"), Shared.getInt(context, "idEscola"), nomeProduto);
        call.enqueue(callback);
    }

    public static void getProdutoById(Context context, final Integer idProduto, final Callback<Produto> callback){
        ProdutoEndPoint endPoint = retrofit.create(ProdutoEndPoint.class);
        Call<Produto> call;
        call = endPoint.getProdutoById(Shared.getInt(context, "idConta"), Shared.getInt(context, "idEscola"), idProduto);
        call.enqueue(callback);
    }

    public static void getProdutoByCodigoBarras(Context context, final String codigoBarras, final Callback<Produto> callback){
        ProdutoEndPoint endPoint = retrofit.create(ProdutoEndPoint.class);
        Call<Produto> call;
        call = endPoint.getProdutoByCodigoBarras(Shared.getInt(context, "idConta"), Shared.getInt(context, "idEscola"), codigoBarras);
        call.enqueue(callback);
    }

    public static void postFornecedor(final Fornecedor[] fornecedores, final Callback<RespostaEscola> callback){
        FornecedorEndPoint endPoint = retrofit.create(FornecedorEndPoint.class);
        Call<RespostaEscola> call = endPoint.postFornecedor(fornecedores);
        call.enqueue(callback);
    }

    public static void getFornecedorByNome(Context context, final String nomeProduto, final Callback<Fornecedor> callback){
        FornecedorEndPoint endPoint = retrofit.create(FornecedorEndPoint.class);
        Call<Fornecedor> call;
        call = endPoint.getFornecedorByNome(Shared.getInt(context, "idConta"), Shared.getInt(context, "idEscola"), nomeProduto);
        call.enqueue(callback);
    }

    public static void getFornecedorById(Context context, final Integer idProduto, final Callback<Fornecedor> callback){
        FornecedorEndPoint endPoint = retrofit.create(FornecedorEndPoint.class);
        Call<Fornecedor> call;
        call = endPoint.getFornecedorById(Shared.getInt(context, "idConta"), Shared.getInt(context, "idEscola"), idProduto);
        call.enqueue(callback);
    }

    public static void getFornecedorByCnpj(Context context, final String cnpj, final Callback<Fornecedor> callback){
        FornecedorEndPoint endPoint = retrofit.create(FornecedorEndPoint.class);
        Call<Fornecedor> call;
        call = endPoint.getFornecedorByCnpj(Shared.getInt(context, "idConta"), Shared.getInt(context, "idEscola"), cnpj);
        call.enqueue(callback);
    }

    //Retornar uma lista / Array
    public static void getFornecedores(Context context, final Callback<List<Fornecedor>> callback){
        FornecedorEndPoint endPoint = retrofit.create(FornecedorEndPoint.class);
        Call<List<Fornecedor>> call;
        call = endPoint.getFornecedores(Shared.getInt(context, "idConta"), Shared.getInt(context, "idEscola"));
        call.enqueue(callback);
    }

    public static void postEntrada(final Entrada entradas, final Callback<RespostaEscola> callback){
        EntradaEndPoint endPoint = retrofit.create(EntradaEndPoint.class);
        Call<RespostaEscola> call = endPoint.postEntrada(entradas);
        call.enqueue(callback);
    }

    public static void postEntradaAluno(final EntradaAluno entradaAluno, final Callback<RespostaEscola> callback){
        EntradaEndPoint endPoint = retrofit.create(EntradaEndPoint.class);
        Call<RespostaEscola> call = endPoint.postEntradaAluno(entradaAluno);
        call.enqueue(callback);
    }

    public static void postEntradaItem(final EntradaItem entradaItem, final Callback<RespostaEscola> callback){
        EntradaEndPoint endPoint = retrofit.create(EntradaEndPoint.class);
        Call<RespostaEscola> call = endPoint.postEntradaItem(entradaItem);
        call.enqueue(callback);
    }

    public static void getEntradas(final Integer idConta, final Integer idEscola, final Callback<List<Entrada>> callback){
        EntradaEndPoint endPoint = retrofit.create(EntradaEndPoint.class);
        Call<List<Entrada>> call = endPoint.getEntradas(idConta, idEscola);
        call.enqueue(callback);
    }

    public static void getSaidas(final Callback<List<Saida>> callback){
        SaidaEndPoint endPoint = retrofit.create(SaidaEndPoint.class);
        Call<List<Saida>> call = endPoint.getSaidas(2, 1);
        call.enqueue(callback);
    }

    public static void getSaidasHoje(final Callback<List<Saida>> callback){
        SaidaEndPoint endPoint = retrofit.create(SaidaEndPoint.class);
        Call<List<Saida>> call = endPoint.getSaidasHoje(2, 1);
        call.enqueue(callback);
    }
}
