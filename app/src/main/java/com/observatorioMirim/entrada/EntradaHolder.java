package com.observatorioMirim.entrada;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.observatorioMirim.R;

public class EntradaHolder extends RecyclerView.ViewHolder {
    private TextView codigoEntrada;
    private TextView labelEntrada;
    CardView cardView;

    public EntradaHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cv);
        labelEntrada = (TextView) itemView.findViewById(R.id.labelEntrada);
        codigoEntrada = (TextView) itemView.findViewById(R.id.codigoEntrada);
    }

    public TextView getCodigoEntrada() {
        return codigoEntrada;
    }

    public void setCodigoEntrada(TextView codigoEntrada) {
        this.codigoEntrada = codigoEntrada;
    }

    public TextView getLabelEntrada() {
        return labelEntrada;
    }

    public void setLabelEntrada(TextView labelEntrada) {
        this.labelEntrada = labelEntrada;
    }
}

