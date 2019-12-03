package com.observatorioMirim.views.upload.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.observatorioMirim.R;
import com.observatorioMirim.api.models.entrada.EntradaDtoDB;

public class UploadFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        if(EntradaDtoDB.countSincronizacoesPendentes(getActivity()) < 1){
            return inflater.inflate(R.layout.fragment_upload_vazio, container, false);
        }

        return null;
    }

    public static UploadFragment newInstance(){
        return new UploadFragment();
    }

}
