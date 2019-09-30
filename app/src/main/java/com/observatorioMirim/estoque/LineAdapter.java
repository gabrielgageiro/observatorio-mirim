package com.observatorioMirim.estoque;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.observatorioMirim.R;

import java.util.ArrayList;
import java.util.List;

public class LineAdapter extends RecyclerView.Adapter<LineHolder> {
    //TODO: alterar para dados vindo do banco
    private List<Produto> produtos = new ArrayList<>();
    private String[] dados = new String[]{"Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread",
            "Honeycomb", "Ice Cream Sandwich", "Jelly Bean",
            "KitKat", "Lollipop", "Marshmallow", "Nougat"};

    public LineAdapter(List<Produto> produtos) {
        this.produtos = produtos;
        System.out.println("OOOOOOOOOOOOOOI");
        for (String dado : dados) {
            addProduto(new Produto(dado, dado, dado));
        }
    }


    @NonNull
    @Override
    public LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.produto_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LineHolder holder, int position) {
        holder.getNomeProduto().setText(dados[position]);
    }

    @Override
    public int getItemCount() {
        return !produtos.isEmpty() ? produtos.size() : 0;
    }

    public void addProduto(Produto produto){
        produtos.add(produto);
        notifyItemInserted(getItemCount());
    }
}
