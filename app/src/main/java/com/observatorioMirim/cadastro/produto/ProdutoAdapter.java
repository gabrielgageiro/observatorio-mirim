package com.observatorioMirim.cadastro.produto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.observatorioMirim.R;
import com.observatorioMirim.cadastro.EntradaFragment;
import com.observatorioMirim.utils.AbstractFragment;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 0;
    private final int VIEW_LOADING = 1;

    private List<Integer> produtos;

    public ProdutoAdapter(List<Integer> produtos) {
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public ProdutoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(VIEW_ITEM == viewType){
            return new ProdutoHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.produto_view, parent, false));
        }
        return new ProdutoHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loading, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ProdutoHolder){
            addLinhaItem((ProdutoHolder) holder, position);
        } else if(holder instanceof ProdutoAdapter.LoadingViewHolder){
            showLoadingView((ProdutoAdapter.LoadingViewHolder) holder, position);
        }
        holder.itemView.setOnClickListener(o ->{
            AbstractFragment.openFragmentFromActivity((AppCompatActivity) o.getContext(), EntradaFragment.create());
        });
    }

    @Override
    public int getItemCount() {
        return produtos != null && !produtos.isEmpty() ? produtos.size() : 0;
    }

    public void addProduto(Integer produto){
        produtos.add(produto);
        notifyItemInserted(getItemCount());
    }

    /**
     * Decide qual view mostrar
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return produtos.get(position) == null ? VIEW_LOADING: VIEW_ITEM;
    }

    private void addLinhaItem(ProdutoHolder holder, int position){
        Integer integer = produtos.get(position);
        if (integer != null){
            holder.getNomeProduto().setText(integer.toString());

        }
    }

    private void showLoadingView(ProdutoAdapter.LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
