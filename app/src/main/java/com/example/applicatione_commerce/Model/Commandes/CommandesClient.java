package com.example.applicatione_commerce.Model.Commandes;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import java.util.List;

public class CommandesClient {
    DocumentReference commandesClients;


    public CommandesClient(List<DocumentReference> PRODUITS, DocumentReference CLIENT, DocumentReference COMMERCANT, Timestamp DATE, String STATUT) {
    }

    public CommandesClient(DocumentReference commandesClients) {
        this.commandesClients = commandesClients;
    }

    public DocumentReference getCommandesClients() {
        return commandesClients;
    }

    public void setCommandesClients(DocumentReference commandesClients) {
        this.commandesClients = commandesClients;
    }
}
