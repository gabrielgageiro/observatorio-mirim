package com.example.observatorio_mirim;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextInputLayout textUsuario;
    private TextInputEditText textSenha;
    private TextInputEditText usuario_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

          btnLogin = findViewById(R.id.btn_login);
          textUsuario = findViewById(R.id.password_text_input);
          textSenha = findViewById(R.id.password_edit_text);
        usuario_edit_text = findViewById(R.id.usuario_edit_text);


         Animation animation_left = AnimationUtils.loadAnimation(this, R.anim.move_para_esquerda);
         Animation animation_right = AnimationUtils.loadAnimation(this, R.anim.move_para_direita);

        textUsuario.startAnimation(animation_left);
        textSenha.startAnimation(animation_left);
        usuario_edit_text.startAnimation(animation_left);
        //btnLogin.startAnimation(animation_right);
    }


}
