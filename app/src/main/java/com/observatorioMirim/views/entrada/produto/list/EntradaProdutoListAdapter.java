package com.observatorioMirim.views.entrada.produto.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.observatorioMirim.R;
import com.observatorioMirim.api.models.produto.ProdutoDto;
import com.observatorioMirim.views.entrada.produto.item.EntradaProdutoItem;

import java.util.List;

public class EntradaProdutoListAdapter extends ArrayAdapter<ProdutoDto> {

    private Context context;
    private List<ProdutoDto> produtos;
    private ImageView produtoMarcado;

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

        produtoMarcado = view.findViewById(R.id.list_item_produto_checked);
        if(!produto.isEntrada()){
            produtoMarcado.setVisibility(View.GONE);
        } else {
            produtoMarcado.setVisibility(View.VISIBLE);
        }

        view.setOnClickListener( o -> {
            EntradaProdutoItem.open((AppCompatActivity)context, produto);
        });

        TextView dataSaida = view.findViewById(R.id.nome_produto);
        dataSaida.setText(produto.getNome());

        return view;
    }
}
