package com.example.applicatione_commerce.Service;

import com.google.firebase.Timestamp;

public class MyListCCom {

    private String produit;
    private String client;
    private Timestamp date;

    public MyListCCom(String nomProduit , String nomClient, Timestamp dateCommande){
        produit = nomProduit;
        client = nomClient;
        date = dateCommande;
    }


    public String getProduit(){
        return produit;
    }

    public String getClient(){
        return client;
    }

    public Timestamp getDate(){
        return date;
    }

}