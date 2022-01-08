package com.example.applicatione_commerce.Model.Utilisateurs;

public class Client  {
    String ADRESSE;
    String EMAIL;
    String MOTDEPASSE;
    Integer NUMERO_TEL;
    String NOM;

    public Client() {
    }

    public Client(String ADRESSE, String EMAIL, String MOTDEPASSE, Integer NUMERO_TEL, String NOM) {
        this.ADRESSE = ADRESSE;
        this.EMAIL = EMAIL;
        this.MOTDEPASSE = MOTDEPASSE;
        this.NUMERO_TEL = NUMERO_TEL;
        this.NOM = NOM;
    }

    public String getADRESSE() {
        return ADRESSE;
    }

    public void setADRESSE(String ADRESSE) {
        this.ADRESSE = ADRESSE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getMOTDEPASSE() {
        return MOTDEPASSE;
    }

    public void setMOTDEPASSE(String MOTDEPASSE) {
        this.MOTDEPASSE = MOTDEPASSE;
    }

    public Integer getNUMERO_TEL() {
        return NUMERO_TEL;
    }

    public void setNUMERO_TEL(Integer NUMERO_TEL) {
        this.NUMERO_TEL = NUMERO_TEL;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }
}
