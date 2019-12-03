package com.observatorioMirim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.observatorioMirim.api.models.entrada.EntradaDtoDB;
import com.observatorioMirim.api.models.produto.ProdutoDtoCache;
import com.observatorioMirim.api.models.produto.ProdutoDtoDB;
import com.observatorioMirim.utils.Shared;
import com.observatorioMirim.utils.SweetUtils;
import com.observatorioMirim.views.entrada.produto.list.EntradaProdutoList;
import com.observatorioMirim.views.saida.list.SaidaList;
import com.observatorioMirim.utils.AbstractFragment;
import com.observatorioMirim.upload.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.observatorioMirim.views.saida.list.SaidaListFragment;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SweetUtils.cancelarLoaderSweet();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);
        initBottomNavigationBar();

        //Limpa tudo que é antigo
        EntradaDtoDB.deleteEntradasNaoFinalizadasAnteriores(this);

        //Verifica se tem algo não finalizado
        Integer entradaId = EntradaDtoDB.existeEntradaNaoFinalizada(this);

        if(entradaId != null){

            SweetUtils.confirmDialog(this, "Continuar Entrada", "Existe uma entrada que não foi terminada, você quer continuar de onde parou?", "Continuar", "Descartar",
                    (SweetAlertDialog sDialog) -> {

                        SweetUtils.loaderNativo(this, "Aguarde", "Sincronizando os produtos.");

                        Shared.putInt(this, "entradaAtual", entradaId);

                        sDialog.dismissWithAnimation();

                        ProdutoDtoCache.setCache(ProdutoDtoDB.listByEntrada(this, entradaId));

                        EntradaProdutoList.open(this);

                        SweetUtils.cancelarLoaderNativo();

                    }, (SweetAlertDialog sDialog) -> {

                        //Caso não queira continuar, limpa tudo que é incompleto
                        EntradaDtoDB.delete(this, entradaId);
                        SaidaList.open(this);

                        sDialog.dismissWithAnimation();

                    });
        }else{
            SaidaList.open(this);
        }

    }

    private void initBottomNavigationBar(){
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemReselectedListener(this);
        bottomNavigationView.setItemIconTintList(null); //necessário para deixar os icones com as cores originais
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
    public void onBackPressed() {
        Fragment fragmentAtual = getSupportFragmentManager().getFragments().get(0);

        if(fragmentAtual instanceof SaidaListFragment || fragmentAtual instanceof UploadFragment){
            return;
        }

        super.onBackPressed();
    }
}
