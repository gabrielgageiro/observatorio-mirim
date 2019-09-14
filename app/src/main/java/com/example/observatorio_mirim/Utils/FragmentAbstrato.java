package com.example.observatorio_mirim.Utils;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.observatorio_mirim.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class FragmentAbstrato extends Fragment {
    private Parcelable vrState = null;
    private static String KEY = "fragment";
    private static String TITULO = "titulo";
    private View view;

    public FragmentAbstrato() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_abstrato, container, false);
        return inflater.inflate(R.layout.fragment_fragment_abstrato, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Find
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    }

    private void rvState(){
        vrState = rv_jogos.getLayoutManager().onSaveInstanceState()
    }

    public Parcelable getVrState() {
        return vrState;
    }

    public void setVrState(Parcelable vrState) {
        this.vrState = vrState;
    }

    public static String getKEY() {
        return KEY;
    }

    public static void setKEY(String KEY) {
        FragmentAbstrato.KEY = KEY;
    }

    public static String getTITULO() {
        return TITULO;
    }

    public static void setTITULO(String TITULO) {
        FragmentAbstrato.TITULO = TITULO;
    }
}
