package com.observatorioMirim.entrada;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.observatorioMirim.R;
import com.observatorioMirim.cadastro.produto.ProdutoFragment;
import com.observatorioMirim.utils.AbstractFragment;
import com.observatorioMirim.utils.LineAdapter;

import java.util.List;

public class EntradaAdapter extends LineAdapter<Integer, EntradaHolder> {
    public EntradaAdapter(List<Integer> entradas) {
        super(entradas);
    }

    @NonNull
    @Override
    public EntradaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (VIEW_ITEM == viewType) {
            return new EntradaHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.entrada_view, parent, false));
        }
        return new EntradaHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_loading, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        addLinhaItem((EntradaHolder) holder, position);
        holder.itemView.setOnClickListener(o -> {
            AbstractFragment.openFragmentFromActivity((AppCompatActivity) o.getContext(), ProdutoFragment.create());
        });
    }

    @Override
    protected void addLinhaItem(EntradaHolder holder, int position) {
        Integer integer = itens.get(position);
        if (integer != null) {
            holder.getCodigoEntrada().setText(integer.toString());
        }
    }
}
