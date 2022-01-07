package com.example.applicatione_commerce.Model.Utilisateurs;

import com.google.firebase.firestore.DocumentReference;
import java.util.List;


public class Commercant {
    String EMAIL;
    String MOTDEPASSE;
    String NOM;
    String NUMSIRET;
    List<DocumentReference> PRODUITS;

    public Commercant() {
    }

    public Commercant(String EMAIL, String MOTDEPASSE, String NOM, String NUMSIRET, List<DocumentReference> PRODUITS) {
        this.EMAIL = EMAIL;
        this.MOTDEPASSE = MOTDEPASSE;
        this.NOM = NOM;
        this.NUMSIRET = NUMSIRET;
        this.PRODUITS = PRODUITS;
    }

    public String getNUMSIRET() {
        return NUMSIRET;
    }

    public void setNUMSIRET(String NUMSIRET) {
        this.NUMSIRET = NUMSIRET;
    }

    public List<DocumentReference> getPRODUITS() {
        return PRODUITS;
    }

    public void setPRODUITS(List<DocumentReference> PRODUITS) {
        this.PRODUITS = PRODUITS;
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
