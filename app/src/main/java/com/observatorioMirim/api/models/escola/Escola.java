package com.observatorioMirim.api.models.escola;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Escola implements Serializable {
    @SerializedName("usuario_smart")
    private String usuario_smart;

    @SerializedName("senha_smart")
    private String senha_smart;

    public Escola() {
    }

    public Escola(String usuario_smart, String senha_smart) {
        this.usuario_smart = usuario_smart;
        this.senha_smart = senha_smart;
    }

    public String getUsuario_smart() {
        return usuario_smart;
    }

    public void setUsuario_smart(String usuario_smart) {
        this.usuario_smart = usuario_smart;
    }

    public String getSenha_smart() {
        return senha_smart;
    }

    public void setSenha_smart(String senha_smart) {
        this.senha_smart = senha_smart;
    }
}
