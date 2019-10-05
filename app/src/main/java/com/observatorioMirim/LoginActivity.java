package com.observatorioMirim;

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
import com.observatorioMirim.api.models.Escola;

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
                API.getEscolas(new Callback<List<Escola>>() {
                    @Override
                    public void onResponse(Call<List<Escola>> call, Response<List<Escola>> response) {
                        if(response != null && response.body() != null && response.body().size() > 0){

                            for (Escola escola : response.body()) {
                                if(escola.getUsuario_smart().equals(edit_text_usuario.getText().toString())){
                                    System.out.println("Login efetivado");
                                } else {
                                    System.out.println(escola.getUsuario_smart());
                                    System.out.println(edit_text_usuario.getText().toString());
                                }
                            }
                        } else {
                            System.out.println("Erro ao trazer as escolas");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Escola>> call, Throwable t) {

                    }
                });
            }
        });
    }


}
