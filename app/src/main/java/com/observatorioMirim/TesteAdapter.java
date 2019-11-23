package com.observatorioMirim;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TesteAdapter extends BaseAdapter {

    private List<String> testes;
    private Activity act;

    public TesteAdapter(List<String> testes, Activity act) {
        this.testes = testes;
        this.act = act;
    }

    @Override
    public int getCount() {
        return testes != null ? testes.size() : 0;
    }

    @Override
    public String getItem(int i) {
        return testes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = act.getLayoutInflater().inflate(R.layout.teste_view, viewGroup, false);
        }

        TextView cardTxt = view.findViewById(R.id.cardTxt);
        cardTxt.setText(testes.get(i));

        return view;
    }
}
