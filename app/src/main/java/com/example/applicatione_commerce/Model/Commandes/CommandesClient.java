package com.example.applicatione_commerce.Model.Commandes;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.ServerTimestamp;

public class CommandesClient {
    DocumentReference PANIER;
    DocumentReference CLIENT;
    Timestamp DATE;
    String STATUT;

    public CommandesClient() {
    }

    public CommandesClient(DocumentReference panier, DocumentReference CLIENT, Timestamp DATE, String STATUT) {
        this.PANIER = panier;
        this.CLIENT = CLIENT;
        this.DATE = DATE;
        this.STATUT = STATUT;
    }

    public DocumentReference getPANIER() {
        return PANIER;
    }

    public void setPANIER(DocumentReference PANIER) {
        this.PANIER = PANIER;
    }

    @ServerTimestamp
    public Timestamp getDATE() {
        return DATE;
    }

    public void setDATE(Timestamp DATE) {
        this.DATE = DATE;
    }

    public String getSTATUT() {
        return STATUT;
    }

    public void setSTATUT(String STATUT) {
        this.STATUT = STATUT;
    }

    public DocumentReference getCLIENT() {
        return CLIENT;
    }

    public void setCLIENT(DocumentReference CLIENT) {
        this.CLIENT = CLIENT;
    }
}
