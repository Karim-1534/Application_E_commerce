package com.example.applicatione_commerce.Model.Commandes;

import com.google.firebase.firestore.DocumentReference;

import java.util.List;

public class CommandesCommercant {
    List<DocumentReference> commandesClients;

    public CommandesCommercant() {
    }

    public CommandesCommercant(List<DocumentReference> commandesClients) {
        this.commandesClients = commandesClients;
    }

    public List<DocumentReference> getCommandesClients() {
        return commandesClients;
    }

    public void setCommandesClients(List<DocumentReference> commandesClients) {
        this.commandesClients = commandesClients;
    }
}
