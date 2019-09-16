package com.example.observatorio_mirim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.observatorio_mirim.Fragment.AbstractFragment;
import com.example.observatorio_mirim.Fragment.CadastroFragment;
import com.example.observatorio_mirim.Fragment.EstoqueFragment;
import com.example.observatorio_mirim.Fragment.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
    private BottomNavigationView bottomNavigationView;

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
        openFragment(EstoqueFragment.create());
//        TODO adicionar efeito de fade ao trocar de tela
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_estoque: {
                Toast.makeText(this, "Estoque", Toast.LENGTH_LONG).show();
                openFragment(EstoqueFragment.create());

            } break;
            case R.id.menu_cadastrar: {
                Toast.makeText(this, "Cadastro", Toast.LENGTH_LONG).show();
                openFragment(CadastroFragment.create());
            } break;
            case R.id.menu_upload: {
                Toast.makeText(this, "Upload", Toast.LENGTH_LONG).show();
                openFragment(UploadFragment.create());
            } break;
        }
        return true;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
    }

    private void openFragment(AbstractFragment fragment){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(fragment.getTitulo());
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
