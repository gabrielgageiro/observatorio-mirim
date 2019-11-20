package com.observatorioMirim.api.models.escola;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Escola implements Serializable {

    @SerializedName("nm_escola")
    private String nomeEscola;

    @SerializedName("razao_social")
    private String razaoSocial;

    @SerializedName("cnpj_cpf")
    private String cnpjCpf;

    @SerializedName("cidade")
    private String cidade;

    @SerializedName("estado")
    private String estado;

    @SerializedName("bairro")
    private String bairro;

    @SerializedName("pais")
    private String pais;

    @SerializedName("cep")
    private String cep;

    @SerializedName("endereco")
    private String endereco;

    @SerializedName("numero")
    private String numero;

    @SerializedName("complemento")
    private String complemento;

    @SerializedName("fone_principal")
    private String fonePrincipal;

    @SerializedName("fone_whats")
    private String foneWhats;

    @SerializedName("fone_skype")
    private String foneSkype;

    @SerializedName("email")
    private String email;

    @SerializedName("usuario_smart")
    private String usuario_smart;

    @SerializedName("senha_smart")
    private String senha_smart;

    /*@Deprecated
    @SerializedName("DhInc")
    private String dataHoraInclusao;*/

    @SerializedName("IdConta")
    private Integer idConta;

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

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getNomeEscola() {
        return nomeEscola;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getFonePrincipal() {
        return fonePrincipal;
    }

    public void setFonePrincipal(String fonePrincipal) {
        this.fonePrincipal = fonePrincipal;
    }

    public String getFoneWhats() {
        return foneWhats;
    }

    public void setFoneWhats(String foneWhats) {
        this.foneWhats = foneWhats;
    }

    public String getFoneSkype() {
        return foneSkype;
    }

    public void setFoneSkype(String foneSkype) {
        this.foneSkype = foneSkype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
