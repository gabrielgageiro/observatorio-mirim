package com.observatorioMirim.entrada;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.observatorioMirim.R;
import com.observatorioMirim.cadastro.EntradaFragment;
import com.observatorioMirim.cadastro.produto.ProdutoFragment;
import com.observatorioMirim.utils.AbstractFragment;

import java.util.List;

public class EntradaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 0;
    private final int VIEW_LOADING = 1;

    private List<Integer> entradas;

    public EntradaAdapter(List<Integer> entradas) {
        this.entradas = entradas;
    }

    @NonNull
    @Override
    public EntradaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(VIEW_ITEM == viewType){
            return new EntradaHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.entrada_view, parent, false));
        }
        return new EntradaHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loading, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof EntradaHolder){
            addLinhaItem((EntradaHolder) holder, position);
        } else if(holder instanceof EntradaAdapter.LoadingViewHolder){
            showLoadingView((EntradaAdapter.LoadingViewHolder) holder, position);
        }
        holder.itemView.setOnClickListener(o ->{
            AbstractFragment.openFragmentFromActivity((AppCompatActivity) o.getContext(), ProdutoFragment.create());
        });
    }

    @Override
    public int getItemCount() {
        return entradas != null && !entradas.isEmpty() ? entradas.size() : 0;
    }

    public void addEntrada(Integer entrada){
        entradas.add(entrada);
        notifyItemInserted(getItemCount());
    }

    /**
     * Decide qual view mostrar
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return entradas.get(position) == null ? VIEW_LOADING: VIEW_ITEM;
    }

    private void addLinhaItem(EntradaHolder holder, int position){
        Integer integer = entradas.get(position);
        if (integer != null){
            holder.getCodigoEntrada().setText(integer.toString());

        }
    }

    private void showLoadingView(EntradaAdapter.LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed
    }

    public void addEntrada(int scrollPosition) {

    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
