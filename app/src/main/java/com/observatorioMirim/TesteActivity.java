package com.observatorioMirim;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TesteActivity extends AppCompatActivity {

    ListView listView;
    List<String> testes = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);

        listView = findViewById(R.id.list_view);


        testes.add("A");
        testes.add("B");
        testes.add("C");
        testes.add("D");
        testes.add("E");
        testes.add("F");


//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, testes);


        listView.setAdapter(new TesteAdapter(testes, this));
    }
}
