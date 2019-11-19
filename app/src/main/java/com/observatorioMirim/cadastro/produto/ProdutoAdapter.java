package com.observatorioMirim.cadastro.produto;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.observatorioMirim.R;
import com.observatorioMirim.utils.LineAdapter;

import java.util.List;

public class ProdutoAdapter extends LineAdapter<Produto, ProdutoHolder> {
    public ProdutoAdapter(List<Produto> produtos) {
        super(produtos);
    }

    @NonNull
    @Override
    public ProdutoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (VIEW_ITEM == viewType) {
            return new ProdutoHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.produto_view, parent, false));
        }
        return new ProdutoHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loading, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        addLinhaItem((ProdutoHolder) holder, position);
    }

    @Override
    public void addLinhaItem(ProdutoHolder holder, int position) {
          Produto produto = itens.get(position);
        if (produto != null) {
            holder.getNomeProduto().setText(produto.getNomeProduto());
            holder.getCodigoProduto().setText(produto.getCodigoProduto());
            holder.getBarrasProduto().setText(produto.getBarrasProduto());
        }
    }
}
