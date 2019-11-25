package com.observatorioMirim.views.entrada.produto.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.observatorioMirim.R;
import com.observatorioMirim.api.models.produto.ProdutoDto;
import com.observatorioMirim.api.models.saida.Saida;
import com.observatorioMirim.cadastro.produto.Produto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EntradaProdutoListAdapter extends ArrayAdapter<ProdutoDto> {

    private Context context;
    private List<ProdutoDto> produtos;

    public EntradaProdutoListAdapter(Context context, List<ProdutoDto> produtos) {
        super(context, 0, produtos);
        this.produtos = produtos;
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ProdutoDto produto = produtos.get(i);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_produto, null);
        }

        TextView dataSaida = view.findViewById(R.id.nome_produto);
        dataSaida.setText(produto.getNome());

        return view;
    }
}
