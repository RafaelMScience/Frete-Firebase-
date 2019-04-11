package com.navegam.fepsfretefirebase.Utils;

public class NavegamData {
    private String CPF;
    private String nameOwner;
    private String email;
    private String nameBoat;
    private String CNPJ;
    private String adminOwner;
    private String employees;

    private String login;
    private String password;

    public NavegamData() {

    }

    public NavegamData( String CPF, String nameOwner, String email, String nameBoat, String CNPJ,
                        String login, String password, String adminOwner, String employees) {
        this.CPF = CPF;
        this.nameOwner = nameOwner;
        this.email = email;
        this.nameBoat = nameBoat;
        this.CNPJ = CNPJ;
        this.login = login;
        this.password = password;
        this.adminOwner = adminOwner;
        this.employees = employees;
    }

    public String getAdminOwner() {
        return adminOwner;
    }

    public void setAdminOwner(String adminOwner) {
        this.adminOwner = adminOwner;
    }

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
