package com.observatorioMirim.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import static com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog.PROGRESS_TYPE;

public class SweetUtils {

    private static ProgressDialog dialog;
    private static SweetAlertDialog dialogSweet;

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

    public static void loaderSweet(Context context, String mensagemLoader){
        dialogSweet = new SweetAlertDialog(context, PROGRESS_TYPE);
        dialogSweet.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));//Cor do loader
        dialogSweet.setTitleText(mensagemLoader);
        dialogSweet.show();
    }

    public static void cancelarLoaderSweet(){
        if(dialogSweet != null){
            dialogSweet.cancel();
        }
    }

    public static void loaderNativo(Context context, String titulo, String message){
        dialog = ProgressDialog.show(context, titulo, message, true);
    }

    public static void cancelarLoaderNativo(){
        if(dialog != null) {
            dialog.cancel();
        }
    }

    public static void confirmDialog(Context context, String titulo, String conteudo, String confirmText, String cancelText, SweetAlertDialog.OnSweetClickListener confirmClick, SweetAlertDialog.OnSweetClickListener cancelClick){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(titulo)
                .setContentText(conteudo)
                .setConfirmText(confirmText)
                .setConfirmClickListener(confirmClick)
                .setCancelText(cancelText)
                .setCancelClickListener(cancelClick)
                .show();
    }
}
