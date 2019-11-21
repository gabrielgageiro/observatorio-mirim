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
import com.observatorioMirim.api.models.RespostaEscola;
import com.observatorioMirim.api.models.entrada.Entrada;
import com.observatorioMirim.api.models.entrada.EntradaAluno;
import com.observatorioMirim.api.models.entrada.EntradaItem;
import com.observatorioMirim.api.models.escola.Escola;
import com.observatorioMirim.api.models.fornecedor.Fornecedor;
import com.observatorioMirim.api.models.produto.Produto;
import com.observatorioMirim.utils.Shared;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
                if(Objects.requireNonNull(edit_text_usuario.getText()).length() == 0 && Objects.requireNonNull(edit_text_senha.getText()).length() == 0){
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Atenção!")
                            .setContentText("O usuário e a senha devem ser preenchidos")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
                                }
                            }).show();
                    return;
                }

                final Escola escola = new Escola(edit_text_usuario.getText().toString(), edit_text_senha.getText().toString());


                API.getEscolaByCodigoSenha(escola.getUsuario_smart(), escola.getSenha_smart(), new Callback<Escola>() {
                    @Override
                    public void onResponse(Call<Escola> call, Response<Escola> response) {
                        Integer conta = response.body().getIdConta();
                        Integer idEscola = response.body().getId();



                        if(response != null && response.body() != null){
                            if(response.body().getUsuario_smart().equals(escola.getUsuario_smart()) && response.body().getSenha_smart().equals(escola.getSenha_smart())){
                                Shared.putInt(getApplicationContext(),Integer.valueOf(conta),conta);
                                Shared.putInt(getApplicationContext(),Integer.valueOf(idEscola),idEscola);

                                //TODO: Criar classe observatorioUtils, salvar as chaves aqui, salvar o idConta e idEscola no share
                                /*new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Bem Vindo")
                                        .setContentText("WELCOME")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                sweetAlertDialog.dismissWithAnimation();
                                            }
                                        }).show();*/
                                    Intent it = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(it);

                            }
                        } else {
                            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Atenção!")
                                    .setContentText("O usuário ou senha informados estão incorretoses")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismissWithAnimation();
                                        }
                                    }).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Escola> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
    }
    public void teste(){
        API.getFornecedores(LoginActivity.this, new Callback<List<Fornecedor>>() {
            @Override
            public void onResponse(Call<List<Fornecedor>> call, Response<List<Fornecedor>> response) {

            }

            @Override
            public void onFailure(Call<List<Fornecedor>> call, Throwable t) {

            }
        });
    }
}
