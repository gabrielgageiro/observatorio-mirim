package com.observatorioMirim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.observatorioMirim.api.API;
import com.observatorioMirim.api.models.escola.Escola;
import com.observatorioMirim.api.models.produto.Produto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextInputLayout textUsuario;
    private TextInputLayout text_input;
    private TextInputEditText edit_text_usuario;
    private TextInputEditText edit_text_senha;
    private ImageView login_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_login);
        textUsuario = findViewById(R.id.password_text_input);
        text_input = findViewById(R.id.text_input);
        edit_text_usuario = findViewById(R.id.edit_text_usuario);
        edit_text_senha = findViewById(R.id.edit_text_senha);
        login_logo = findViewById(R.id.login_logo);


         Animation animation_esquerda = AnimationUtils.loadAnimation(this, R.anim.move_para_esquerda);
         Animation animation_esquerda_500Ms = AnimationUtils.loadAnimation(this, R.anim.move_para_esquerda);
         Animation animation_direita = AnimationUtils.loadAnimation(this, R.anim.move_para_direta);

        textUsuario.startAnimation(animation_esquerda);
        text_input.startAnimation(animation_esquerda);
        btnLogin.startAnimation(animation_esquerda_500Ms);
        login_logo.startAnimation(animation_direita);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_text_usuario.getText().length() == 0 && edit_text_senha.getText().length() == 0){
                    System.out.println("Implementar Animação informando que o usuário e a senha devem ser preenchidos");
                    return;
                }

                final Escola escola = new Escola(edit_text_usuario.getText().toString(), edit_text_senha.getText().toString());
                System.out.println(escola.getUsuario_smart());
                System.out.println(escola.getSenha_smart());

                API.getEscolaByCodigoSenha(escola.getUsuario_smart(), escola.getSenha_smart(), new Callback<Escola>() {
                    @Override
                    public void onResponse(Call<Escola> call, Response<Escola> response) {
                        if(response != null && response.body() != null){
                            if(response.body().getUsuario_smart().equals(escola.getUsuario_smart()) && response.body().getSenha_smart().equals(escola.getSenha_smart())){
                                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(it);
                                //TODO: Implementar uma mensagem informando que o login foi efetivado ou Bem Vindo!
                            }
                        } else {
                            System.out.println("Erro ao encontrar a escola");
                        }
                    }

                    @Override
                    public void onFailure(Call<Escola> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        //incluirProduto();
        listarProdutos();
    }

    private void listarProdutos() {
        API.getProdutos(8, 1, new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
            }
        });
    }


    private void incluirProduto() {
        final List<Produto> produtos = Arrays.asList(new Produto(1, "Produto Teste", "Teste", "1546464", "1", new Date(), 1, "Equipe1"));

        API.saveProduto(produtos, new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
            }
        });


    }


}
