package com.observatorioMirim.cadastro.produto;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.observatorioMirim.R;

public class ProdutoHolder extends RecyclerView.ViewHolder {
    private TextView codigoProduto;
    private TextView nomeProduto;
    private TextView barrasProduto;

    public ProdutoHolder(View itemView){
        super(itemView);
        nomeProduto = (TextView) itemView.findViewById(R.id.nomeProduto);
        barrasProduto = (TextView) itemView.findViewById(R.id.barrasProduto);
        codigoProduto = (TextView) itemView.findViewById(R.id.codigoProduto);
    }

    public TextView getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(TextView codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public TextView getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(TextView nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public TextView getBarrasProduto() {
        return barrasProduto;
    }

    public void setBarrasProduto(TextView barrasProduto) {
        this.barrasProduto = barrasProduto;
    }
}
