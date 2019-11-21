package com.observatorioMirim.utils;

import android.content.Context;
import android.graphics.Color;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class SweetUtils {

    public static void message(Context context, String titulo, String conteudo, int typeSweetAlert){
        new SweetAlertDialog(context, typeSweetAlert)
                .setTitleText(titulo)
                .setContentText(conteudo)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                }).show();
    }

    public static void loader(Context context, int typeSweetAlert, String mensagemLoader){
        SweetAlertDialog dialog = new SweetAlertDialog(context, typeSweetAlert);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));//Cor do loader
        dialog.setTitleText(mensagemLoader);
        dialog.show();
    }
}
