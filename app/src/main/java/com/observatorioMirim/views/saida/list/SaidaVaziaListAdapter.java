package com.observatorioMirim.views.saida.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.observatorioMirim.R;
import com.observatorioMirim.api.models.entrada.EntradaDto;
import com.observatorioMirim.api.models.entrada.EntradaDtoDB;
import com.observatorioMirim.api.models.produto.ProdutoDto;
import com.observatorioMirim.api.models.produto.ProdutoDtoCache;
import com.observatorioMirim.api.models.produto.ProdutoDtoDB;
import com.observatorioMirim.api.models.saida.Saida;
import com.observatorioMirim.utils.Shared;
import com.observatorioMirim.views.entrada.produto.list.EntradaProdutoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SaidaVaziaListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> saida;

    public SaidaVaziaListAdapter(Context context,List<String> saida) {
        super(context, 0, saida);
        this.context = context;
        this.saida = saida;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_saida_vazio, null);
        }
        return view;
    }
}
