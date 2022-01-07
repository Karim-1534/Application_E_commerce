package com.example.applicatione_commerce.Model;
import com.google.firebase.firestore.DocumentReference;

import java.util.List;

public class Panier {

    Integer QUANTITE;
    float TOTALE;

    List<DocumentReference> PRODUITS;

    public Panier() {
    }


    public Panier(Integer QUANTITE, float TOTALE, List<DocumentReference> PRODUITS) {
        this.QUANTITE = QUANTITE;
        this.TOTALE = TOTALE;
        this.PRODUITS = PRODUITS;
    }

    public List<DocumentReference> getPRODUITS() {
        return PRODUITS;
    }

    public void setPRODUITS(List<DocumentReference> PRODUITS) {
        this.PRODUITS = PRODUITS;
    }

    public Integer getQUANTITE() {
        return QUANTITE;
    }

    public void setQUANTITE(Integer QUANTITE) {
        this.QUANTITE = QUANTITE;
    }

    public float getTOTALE() {
        return TOTALE;
    }

    public void setTOTALE(float TOTALE) {
        this.TOTALE = TOTALE;
    }
}
