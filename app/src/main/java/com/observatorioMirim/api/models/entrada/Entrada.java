package com.observatorioMirim.api.models.entrada;

import com.google.gson.annotations.SerializedName;
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

    public static Entrada generateEntrada(MainActivity mainActivity){
        Entrada entrada = new Entrada();

        int idConta = Shared.getInt(mainActivity, "idConta");
        int idEscola = Shared.getInt(mainActivity, "idEscola");

        List<ProdutoDto> produtos = ProdutoDtoDB.list(mainActivity);
        produtos.forEach( p -> {
            EntradaItem item = new EntradaItem(p);
            item.setIdConta(idConta);
            item.setIdEscola(idEscola);

            entrada.addEntradaItem(item);
        });

        entrada.setIdSaida(produtos.get(0).getIdEntrada());
        entrada.setIdConta(idConta);
        entrada.setIdEscola(idEscola);

        return entrada;
    }
}
