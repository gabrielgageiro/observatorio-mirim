package com.example.observatorio_mirim.upload;

import com.example.observatorio_mirim.R;
import com.example.observatorio_mirim.utils.AbstractFragment;

public class UploadFragment extends AbstractFragment {
    public UploadFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static UploadFragment create(){
        return new UploadFragment(R.layout.upload_fragment, "Sincronizar");
    }
}
