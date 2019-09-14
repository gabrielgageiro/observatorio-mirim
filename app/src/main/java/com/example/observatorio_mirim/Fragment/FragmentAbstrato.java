package com.example.observatorio_mirim.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private RecyclerView abstract_fragment;
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
        RecyclerView abstract_fragment = (RecyclerView) view;
        abstract_fragment.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        abstract_fragment.setLayoutManager(linearLayoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation());
        abstract_fragment.addItemDecoration(divider);

        rvState();
    }

    private void rvState(){
        vrState = abstract_fragment .getLayoutManager().onSaveInstanceState();
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
