package com.example.observatorio_mirim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


/**
 * Created by Programador on 30/08/2017.
 */

public class SplashActivity extends Activity {

    /*
    Image View para manipulacao da imagem.
    */
    private ImageView io_imv_logo;

    /*
    Animacao da splash.
    */
    private Animation io_animation_fade;

    /*
    Cria a Activity e faz a animação.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        Seta a visão.
         */
        setContentView(R.layout.splash_activity);

         /*
        Resgata o logo da aplicacao.
        */
        io_imv_logo = findViewById(R.id.image_nyan_cat);

        /*
        Cria a animacao para o logo da aplicacao.
        */
        io_animation_fade = AnimationUtils.loadAnimation(this, R.anim.anim_splash);
        io_animation_fade.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Nao implementar no momento.
            }

            @Override
            public final void onAnimationEnd(final Animation lo_animation){
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                finish();
            }
        });

        /*
        Inicia a animacao da aplicacao.
        */
        io_imv_logo.startAnimation(io_animation_fade);

    }

    /*
    Cancela o efeito da splah e vai direto para a tela de inicio
     */
    public void ClickImage(View v) {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
    }

}
