package com.observatorioMirim.upload;

import com.observatorioMirim.R;
import com.observatorioMirim.utils.AbstractFragment;

public class UploadFragment extends AbstractFragment {
    public UploadFragment(Integer key, String titulo) {
        super(key, titulo);
    }

    public static UploadFragment create(){
        return new UploadFragment(R.layout.upload_fragment, "Sincronizar");
    }
}
