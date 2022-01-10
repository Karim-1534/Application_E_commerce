package com.example.applicatione_commerce.Model.Commandes;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.List;

public class Commande {
    List<DocumentReference> PRODUITS;
    DocumentReference CLIENT;
    DocumentReference COMMERCANT;
    String  DATE;
    String STATUT;

    public Commande() {
    }

    public Commande(List<DocumentReference> PRODUITS, DocumentReference CLIENT, DocumentReference COMMERCANT, String DATE, String STATUT) {
        this.PRODUITS = PRODUITS;
        this.CLIENT = CLIENT;
        this.COMMERCANT = COMMERCANT;
        this.DATE = DATE;
        this.STATUT = STATUT;
    }

    public List<DocumentReference> getPRODUITS() {
        return PRODUITS;
    }

    public void setPRODUITS(List<DocumentReference> PRODUITS) {
        this.PRODUITS = PRODUITS;
    }

    public DocumentReference getCLIENT() {
        return CLIENT;
    }

    public void setCLIENT(DocumentReference CLIENT) {
        this.CLIENT = CLIENT;
    }

    public DocumentReference getCOMMERCANT() {
        return COMMERCANT;
    }

    public void setCOMMERCANT(DocumentReference COMMERCANT) {
        this.COMMERCANT = COMMERCANT;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getSTATUT() {
        return STATUT;
    }

    public void setSTATUT(String STATUT) {
        this.STATUT = STATUT;
    }
}
