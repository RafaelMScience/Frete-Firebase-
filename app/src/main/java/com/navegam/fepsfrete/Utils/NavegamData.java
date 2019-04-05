package com.navegam.fepsfrete.Utils;

public class NavegamData {
    private String CPF;
    private String nameOwner;
    private String email;
    private String nameBoat;
    private String CNPJ;

    public NavegamData() {

    }

    public NavegamData( String CPF, String nameOwner, String email, String nameBoat, String CNPJ) {
        this.CPF = CPF;
        this.nameOwner = nameOwner;
        this.email = email;
        this.nameBoat = nameBoat;
        this.CNPJ = CNPJ;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameBoat() {
        return nameBoat;
    }

    public void setNameBoat(String nameBoat) {
        this.nameBoat = nameBoat;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }
}
