package com.example.applicatione_commerce.Model.Commandes;

import com.example.applicatione_commerce.Model.Panier;
import com.example.applicatione_commerce.Model.Produit;
import com.example.applicatione_commerce.Model.Utilisateurs.Client;

import java.util.Date;
import java.util.List;

public class CommandesClient {
    Panier panier;
    Client client;
    Date date;
    String status;



    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
