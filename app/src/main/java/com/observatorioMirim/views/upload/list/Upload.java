package com.observatorioMirim.views.upload.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.observatorioMirim.utils.AbstractFragment;

public class Upload {

    private static final String TITULO = "Sincronizar";

    public static void open(@NonNull final AppCompatActivity activity){
        AbstractFragment.openFragmentFromActivity(activity, UploadFragment.newInstance(), TITULO, "UploadFragment");
    }
}
