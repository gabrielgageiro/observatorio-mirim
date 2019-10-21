package com.observatorioMirim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.observatorioMirim.estoque.EstoqueListFragment;
import com.observatorioMirim.utils.AbstractFragment;
import com.observatorioMirim.cadastro.EntradaFragment;
import com.observatorioMirim.upload.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton novaEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);
        novaEntrada = (FloatingActionButton) findViewById(R.id.nova_entrada);
        novaEntrada.setOnClickListener(o ->{
            AbstractFragment.openFragmentFromActivity(this, EntradaFragment.create());
        });

        initBottomNavigationBar();
    }

    private void initBottomNavigationBar(){
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemReselectedListener(this);
        bottomNavigationView.setItemIconTintList(null); //necessário para deixar os icones com as cores originais
        AbstractFragment.openFragmentFromActivity(this, EstoqueListFragment.create());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_estoque: {
                AbstractFragment.openFragmentFromActivity(this, EstoqueListFragment.create());

            } break;
            case R.id.menu_cadastrar: {
                AbstractFragment.openFragmentFromActivity(this, EntradaFragment.create());
            } break;
            case R.id.menu_upload: {
                AbstractFragment.openFragmentFromActivity(this, UploadFragment.create());
            } break;
        }
        return true;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
    }
}
