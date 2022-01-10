package com.example.applicatione_commerce.Service;

public class MyListCCom {

    private String produit;
    private String client;
    private String date;

    public MyListCCom(String nomProduit , String nomClient, String dateCommande){
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

    public String getDate(){
        return date;
    }

}