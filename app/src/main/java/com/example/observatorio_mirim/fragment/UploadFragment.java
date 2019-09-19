package com.example.observatorio_mirim.fragment;

import com.example.observatorio_mirim.R;

public class UploadFragment extends AbstractFragment {
    public UploadFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static UploadFragment create(){
        return new UploadFragment(R.layout.upload_fragment, "Sincronizar");
    }
}
