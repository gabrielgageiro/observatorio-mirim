package com.example.observatorio_mirim.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class AbstractFragment extends Fragment {
    private Integer key;
    private String titulo;

    public AbstractFragment(Integer key, String titulo) {
        this.key = key;
        this.titulo = titulo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(key, container, false);
    }

    public String getTitulo(){
        return  titulo;
    }

    public Integer getKey() {
        return key;
    }
}
