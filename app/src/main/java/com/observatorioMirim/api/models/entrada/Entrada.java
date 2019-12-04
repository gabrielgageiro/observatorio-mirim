package com.observatorioMirim.api.models.entrada;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.observatorioMirim.api.models.entrada.aluno.EntradaAluno;
import com.observatorioMirim.api.models.entrada.aluno.EntradaAlunoDto;
import com.observatorioMirim.api.models.entrada.aluno.EntradaAlunoDtoDB;
import com.observatorioMirim.api.models.entrada.item.EntradaItem;
import com.observatorioMirim.api.models.produto.ProdutoDto;
import com.observatorioMirim.api.models.produto.ProdutoDtoDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Entrada implements Serializable {

    @SerializedName("id_escola")
    private Integer idEscola;

    @SerializedName("id_saida")
    private Integer idSaida;

    @SerializedName("observacao")
    private String observacao;

    @SerializedName("IdConta")
    private Integer idConta;

    @SerializedName("listaEntradaItem")
    private List<EntradaItem> entradaItems = new ArrayList<>();

    @SerializedName("listaEntradaAluno")
    private List<EntradaAluno> entradaAlunos = new ArrayList<>();

    @SerializedName("ListaEntrada")
    private List<Entrada> entradaEntradas = new ArrayList<>();

    @SerializedName("escola")
    private String escola;

    public Entrada() {
    }

    public Entrada(EntradaDto entradaDto) {
        this.idEscola = entradaDto.getIdEscola();
        this.idSaida = entradaDto.getIdSaida();
        this.observacao = entradaDto.getObservacao();
        this.idConta = entradaDto.getIdConta();
    }

    public Integer getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(Integer idEscola) {
        this.idEscola = idEscola;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public List<EntradaItem> getEntradaItems() {
        return entradaItems;
    }

    public void setEntradaItems(List<EntradaItem> entradaItems) {
        this.entradaItems = entradaItems;
    }

    public List<EntradaAluno> getEntradaAlunos() {
        return entradaAlunos;
    }

    public void setEntradaAlunos(List<EntradaAluno> entradaAlunos) {
        this.entradaAlunos = entradaAlunos;
    }

    public Integer getIdSaida() {
        return idSaida;
    }

    public void setIdSaida(Integer idSaida) {
        this.idSaida = idSaida;
    }

    public List<Entrada> getEntradaEntradas() {
        return entradaEntradas;
    }

    public void setEntradaEntradas(List<Entrada> entradaEntradas) {
        this.entradaEntradas = entradaEntradas;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public void addEntradaItem(EntradaItem entradaItem){
        entradaItems.add(entradaItem);
    }

    public void addEntradaAluno(EntradaAluno entradaAluno){
        entradaAlunos.add(entradaAluno);
    }

    public static Entrada generateEntrada(Context context, EntradaDto entradaDto){
        Entrada entrada = new Entrada(entradaDto);


        List<ProdutoDto> produtos = ProdutoDtoDB.listByEntrada(context, entradaDto.getId());
        produtos.forEach( p -> {
            EntradaItem item = new EntradaItem(p);
            item.setIdConta(entrada.getIdConta());
            item.setIdEscola(entrada.getIdEscola());

            entrada.addEntradaItem(item);
        });

        List<EntradaAlunoDto> alunos = EntradaAlunoDtoDB.listByEntrada(context, entradaDto.getId());
        alunos.forEach( a -> {
            EntradaAluno item = new EntradaAluno(a);
            item.setIdConta(entrada.getIdConta());
            item.setIdEscola(entrada.getIdEscola());

            entrada.addEntradaAluno(item);
        });

        return entrada;
    }
}
