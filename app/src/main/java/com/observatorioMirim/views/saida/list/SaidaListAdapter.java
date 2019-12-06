package com.observatorioMirim.views.saida.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.observatorioMirim.R;
import com.observatorioMirim.api.models.saida.Saida;
import com.observatorioMirim.views.entrada.produto.list.EntradaProdutoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SaidaListAdapter extends ArrayAdapter<Saida> {

    private Context context;

    public SaidaListAdapter(Context context) {
        super(context, 0, Arrays.asList(new Saida()));
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_saida, null);
        }

        view.setOnClickListener(o -> {
            EntradaProdutoList.open((AppCompatActivity) context);
        });

        TextView dataSaida = view.findViewById(R.id.data_saida);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataSaida.setText(LocalDate.now().format(formatter));

        return view;
    }
}
