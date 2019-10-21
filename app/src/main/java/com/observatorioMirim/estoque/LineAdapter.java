package com.observatorioMirim.estoque;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.observatorioMirim.R;

import java.util.List;

public class LineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 0;
    private final int VIEW_LOADING = 1;

    private List<Integer> produtos;

    public LineAdapter(List<Integer> produtos) {
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(VIEW_ITEM == viewType){
            return new LineHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.produto_view, parent, false));
        }
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loading, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof LineHolder){
            addLinhaItem((LineHolder) holder, position);
        } else if(holder instanceof LoadingViewHolder){
            showLoadingView((LoadingViewHolder) holder, position);
        }
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

    private void addLinhaItem(LineHolder holder, int position){
        Integer integer = produtos.get(position);
        if (integer != null){
            holder.getNomeProduto().setText(integer.toString());

        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
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
