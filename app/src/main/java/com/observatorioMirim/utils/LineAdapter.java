package com.observatorioMirim.utils;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class LineAdapter<T,S> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected final int VIEW_ITEM = 0;
    protected final int VITW_LOADING = 1;

    protected List<T> itens;

    public LineAdapter(List<T> itens) {
        this.itens = itens;
    }

    @Override
    public int getItemCount() {
        return itens != null && !itens.isEmpty() ? itens.size() : 0;
    }

    public void addLine(T item) {
        itens.add(item);
        notifyItemInserted(getItemCount());
    }

    @Override
    public int getItemViewType(int position) {
        return itens.get(position) == null ? VITW_LOADING : VIEW_ITEM;
    }

    protected abstract void addLinhaItem(S holder, int position);

}
