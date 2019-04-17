package com.navegam.fepsfretefirebase.Utils;

public class Document {

    public String stRemetente;
    public String stDestinatario;
    public String stCidade_des;
    public String stCidade_ori;
    public String stTelRem;
    public String stData_viagem;
    public String stDescricao;
    public String stQuantidade;
    public String stValor;
    public String stValorFrete;
    public String stValorPago;


    public Document(String stRemetente, String stDestinatario, String stCidade_des, String stCidade_ori, String stTel, String stData_viagem, String stDescricao, String stQuantidade, String stValor, String stValorFrete, String stValorPago) {
        this.stRemetente = stRemetente;
        this.stDestinatario = stDestinatario;
        this.stCidade_des = stCidade_des;
        this.stCidade_ori = stCidade_ori;
        this.stTelRem = stTel;
        this.stData_viagem = stData_viagem;
        this.stDescricao = stDescricao;
        this.stQuantidade = stQuantidade;
        this.stValor = stValor;
        this.stValorFrete = stValorFrete;
        this.stValorPago = stValorPago;
    }

    public Document(){}

    public String getStRemetente() {
        return stRemetente;
    }

    public void setStRemetente(String stRemetente) {
        this.stRemetente = stRemetente;
    }

    public String getStDestinatario() {
        return stDestinatario;
    }

    public void setStDestinatario(String stDestinatario) {
        this.stDestinatario = stDestinatario;
    }

    public String getStCidade_des() {
        return stCidade_des;
    }

    public void setStCidade_des(String stCidade_des) {
        this.stCidade_des = stCidade_des;
    }

    public String getStCidade_ori() {
        return stCidade_ori;
    }

    public void setStCidade_ori(String stCidade_ori) {
        this.stCidade_ori = stCidade_ori;
    }

    public String getStTelRem() {
        return stTelRem;
    }

    public void setStTelRem(String stCPF) {
        this.stTelRem = stCPF;
    }

    public String getStData_viagem() {
        return stData_viagem;
    }

    public void setStData_viagem(String stData_viagem) {
        this.stData_viagem = stData_viagem;
    }

    public String getStDescricao() {
        return stDescricao;
    }

    public void setStDescricao(String stDescrição) {
        this.stDescricao = stDescricao;
    }

    public String getStQuantidade() {
        return stQuantidade;
    }

    public void setStQuantidade(String stQuantidade) {
        this.stQuantidade = stQuantidade;
    }

    public String getStValor() {
        return stValor;
    }

    public void setStValor(String stValor) {
        this.stValor = stValor;
    }

    public String getStValorFrete() {
        return stValorFrete;
    }

    public void setStValorFrete(String stValorFrete) {
        this.stValorFrete = stValorFrete;
    }

    public String getStValorPago() {
        return stValorPago;
    }

    public void setStValorPago(String stValorPago) {
        this.stValorPago = stValorPago;
    }
}