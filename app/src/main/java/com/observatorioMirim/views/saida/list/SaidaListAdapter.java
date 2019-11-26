package com.observatorioMirim.views.saida.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.observatorioMirim.R;
import com.observatorioMirim.api.models.produto.ProdutoDto;
import com.observatorioMirim.api.models.saida.Saida;
import com.observatorioMirim.views.entrada.produto.list.EntradaProdutoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SaidaListAdapter extends ArrayAdapter<Saida> {

    private Context context;
    private List<Saida> saidas;

    public SaidaListAdapter(Context context, List<Saida> saidas) {
        super(context, 0, saidas);
        this.saidas = saidas;
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Saida saida = saidas.get(i);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_saida, null);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        view.setOnClickListener(o -> {

            ArrayList<ProdutoDto> produtos = new ArrayList<>();

            saida.getSaidaItemList().forEach( s -> produtos.add(new ProdutoDto(s)));

            EntradaProdutoList.open((AppCompatActivity) context, produtos);
        });

        TextView dataSaida = view.findViewById(R.id.data_saida);
        dataSaida.setText(LocalDate.now().format(formatter));

        return view;
    }
}
