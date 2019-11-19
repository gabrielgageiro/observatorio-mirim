package com.observatorioMirim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.observatorioMirim.entrada.EntradaListFragment;
import com.observatorioMirim.utils.AbstractFragment;
import com.observatorioMirim.upload.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
    private BottomNavigationView bottomNavigationView;
    private Button btnProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);
        initBottomNavigationBar();
    }

    private void initBottomNavigationBar(){
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemReselectedListener(this);
        bottomNavigationView.setItemIconTintList(null); //necessário para deixar os icones com as cores originais
        AbstractFragment.openFragmentFromActivity(this, EntradaListFragment.create());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_entradas:
                AbstractFragment.openFragmentFromActivity(this, EntradaListFragment.create());
                break;
            case R.id.menu_sincronizar:
                AbstractFragment.openFragmentFromActivity(this, UploadFragment.create());
                break;
        }
        return true;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
    }

    public BottomNavigationView getBottomNavigationView() {
        return bottomNavigationView;
    }
}
