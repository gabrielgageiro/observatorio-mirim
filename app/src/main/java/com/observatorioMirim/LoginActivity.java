package com.observatorioMirim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.observatorioMirim.api.API;
import com.observatorioMirim.api.models.escola.Escola;
import com.observatorioMirim.utils.Shared;
import com.observatorioMirim.utils.SweetUtils;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

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
    private CheckBox loginAutomatico;

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

        loginAutomatico = findViewById(R.id.login_automatico);


        Animation animation_esquerda = AnimationUtils.loadAnimation(this, R.anim.move_para_esquerda);
        Animation animation_esquerda_500Ms = AnimationUtils.loadAnimation(this, R.anim.move_para_esquerda);
        Animation animation_direita = AnimationUtils.loadAnimation(this, R.anim.move_para_direta);

        textUsuario.startAnimation(animation_esquerda);
        text_input.startAnimation(animation_esquerda);
        btnLogin.startAnimation(animation_esquerda_500Ms);
        login_logo.startAnimation(animation_direita);
        loginAutomatico.startAnimation(animation_esquerda_500Ms);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.requireNonNull(edit_text_usuario.getText()).length() == 0 && Objects.requireNonNull(edit_text_senha.getText()).length() == 0){
                    SweetUtils.message(LoginActivity.this, "Atenção!", "O usuário e a senha devem ser preenchidos", SweetAlertDialog.WARNING_TYPE);
                    return;
                }

                final Escola escola = new Escola(edit_text_usuario.getText().toString(), edit_text_senha.getText().toString());

                login(escola.getUsuario_smart(), escola.getSenha_smart());
            }
        });
        logarAutomaticamente();
        //SweetUtils.loaderNativo(this, "", "Sincronizando...");
        //SweetUtils.cancelarLoaderNativo();
        //SweetUtils.loaderSweet(this, "Sincronizando...");
        //SweetUtils.cancelarLoaderSweet();
    }

    public void setLoginAutomatico() {

        boolean logarAutomaticamente = loginAutomatico.isChecked();

        if (logarAutomaticamente) {
            Shared.putString(LoginActivity.this, "codigoEscola", edit_text_usuario.getText().toString());
            Shared.putString(LoginActivity.this, "senhaEscola", edit_text_senha.getText().toString());
            Shared.putBoolean(LoginActivity.this, "logarAutomaticamente", true);
        } else {
            Shared.putBoolean(LoginActivity.this, "logarAutomaticamente", false);
        }
    }

    public void logarAutomaticamente(){
        boolean logarAutomaticamente = Shared.getBoolean(LoginActivity.this, "logarAutomaticamente");
        final String codigoEscola = Shared.getString(LoginActivity.this, "codigoEscola");
        final String senhaEscola = Shared.getString(LoginActivity.this, "senhaEscola");

        if(logarAutomaticamente){
           login(codigoEscola, senhaEscola);
        }
    }

    public void login(final String codigoEscola, final String senhaEscola){
        API.getEscolaByCodigoSenha(codigoEscola, senhaEscola, new Callback<Escola>() {
            @Override
            public void onResponse(Call<Escola> call, Response<Escola> response) {
                Integer idConta = response.body().getIdConta();
                Integer idEscola = response.body().getId();

                if(response != null && response.body() != null){
                    if(response.body().getUsuario_smart().equals(codigoEscola) && response.body().getSenha_smart().equals(senhaEscola)){
                        Shared.putInt(LoginActivity.this, "idConta", idConta);
                        Shared.putInt(LoginActivity.this, "idEscola", idEscola);

                        setLoginAutomatico();

                        Intent it = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(it);
                    }
                } else {
                    SweetUtils.message(LoginActivity.this, "Atenção!", "O usuário ou senha informados estão incorretos", SweetAlertDialog.ERROR_TYPE);
                }
            }

            @Override
            public void onFailure(Call<Escola> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //Para que seja possivel logar com outra conta
    public void unLogin(){
        Shared.putBoolean(LoginActivity.this, "logarAutomaticamente", false);
    }

}
