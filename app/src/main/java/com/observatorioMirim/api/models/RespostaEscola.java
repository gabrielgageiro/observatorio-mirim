package com.observatorioMirim.api.models;

import java.io.Serializable;

public class RespostaEscola implements Serializable {

    private boolean Sucesso;
    private String Mensagem;
    private long Id;

    public boolean isSucesso() {
        return Sucesso;
    }

    public void setSucesso(boolean sucesso) {
        Sucesso = sucesso;
    }

    public String getMensagem() {
        return Mensagem;
    }

    public void setMensagem(String mensagem) {
        Mensagem = mensagem;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }
}
