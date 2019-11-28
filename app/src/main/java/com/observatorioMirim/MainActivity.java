package com.observatorioMirim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.observatorioMirim.views.entrada.produto.item.EntradaProdutoItem;
import com.observatorioMirim.views.saida.list.SaidaList;
import com.observatorioMirim.utils.AbstractFragment;
import com.observatorioMirim.upload.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.observatorioMirim.views.saida.list.SaidaListFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
    private BottomNavigationView bottomNavigationView;
    private Menu menu;
    private String busca;

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

        SaidaList.open(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_entradas:
                SaidaList.open(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        super.onCreateOptionsMenu(this.menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.searchable, this.menu);
        MenuItem search = this.menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search.getActionView();
        this.menu.setGroupVisible(R.id.menu_group, false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                busca = query;
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        search.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                return true;       // Return true to collapse action view
            }
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                searchView.setQuery(busca, false);
                return true;      // Return true to expand action view
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        Fragment fragmentAtual = getSupportFragmentManager().getFragments().get(0);

        if(fragmentAtual instanceof SaidaListFragment || fragmentAtual instanceof UploadFragment){
            return;
        }

        super.onBackPressed();
    }

    public void changeMenuVisibleState(boolean visible) {
        menu.setGroupVisible(R.id.menu_group, visible);
    }
    public Menu getMenuVisibleState() {
        return menu;
    }
}
