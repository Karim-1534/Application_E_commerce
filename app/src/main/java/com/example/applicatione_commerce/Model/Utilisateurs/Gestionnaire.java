package com.example.applicatione_commerce.Model.Utilisateurs;

public class Gestionnaire {
    String EMAIL;
    String MOTDEPASSE;
    String NOM;


    public Gestionnaire() {
    }

    public Gestionnaire(String EMAIL, String MOTDEPASSE, String NOM) {
        this.EMAIL = EMAIL;
        this.MOTDEPASSE = MOTDEPASSE;
        this.NOM = NOM;
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

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }
}
