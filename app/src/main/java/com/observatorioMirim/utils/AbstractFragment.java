package com.observatorioMirim.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.observatorioMirim.MainActivity;
import com.observatorioMirim.R;

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

    public static void openFragmentFromActivity(AppCompatActivity activity, AbstractFragment fragment){
        activity.getSupportActionBar().setTitle(fragment.getTitulo());
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public static void openFragmentFromActivity(@NonNull final AppCompatActivity activity, @NonNull final Fragment fragment, @NonNull final String titulo, @NonNull final String tag){
        activity.getSupportActionBar().setTitle(titulo);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment)
                .addToBackStack(tag)
                .commit();
    }

    public static void openFragmentFromActivity(@NonNull final AppCompatActivity activity, @NonNull final Fragment fragment, @NonNull final String titulo){
        activity.getSupportActionBar().setTitle(titulo);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDetach() {
        ((MainActivity) getActivity()).getBottomNavigationView().setVisibility(View.VISIBLE);
        super.onDetach();
    }
}
