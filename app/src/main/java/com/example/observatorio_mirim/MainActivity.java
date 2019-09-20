package com.example.observatorio_mirim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.observatorio_mirim.Fragment.AbstractFragment;
import com.example.observatorio_mirim.Fragment.CadastroFragment;
import com.example.observatorio_mirim.Fragment.EstoqueFragment;
import com.example.observatorio_mirim.Fragment.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.karan.churi.PermissionManager.PermissionManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
    private BottomNavigationView bottomNavigationView;
    private PermissionManager permission;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPermissionCamera();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_bar);

//        initBottomNavigationBar();
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

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        permission.checkResult(requestCode,permissions, grantResults);
        //To get Granted Permission and Denied Permission
        ArrayList<String> granted=permission.getStatus().get(0).granted;
        ArrayList<String> denied=permission.getStatus().get(0).denied;
        if (denied != null && denied.size() > 0) {
            Toast.makeText(this,"Negou",Toast.LENGTH_LONG).show();
        }
        else{
            new IntentIntegrator(this).initiateScan(); // `this` is the current Activity
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void getPermissionCamera(){
        permission=new PermissionManager() {

            @Override
            public List<String> setPermission() {
                // If You Don't want to check permission automatically and check your own custom permission
                // Use super.setPermission(); or Don't override this method if not in use
                List<String> customPermission=new ArrayList<>();
                customPermission.add(Manifest.permission.CAMERA);
                return customPermission;
            }
        };
        boolean result = permission.checkAndRequestPermissions(this);

        if (result) {
            new IntentIntegrator(this).initiateScan();
        }
    }
}
