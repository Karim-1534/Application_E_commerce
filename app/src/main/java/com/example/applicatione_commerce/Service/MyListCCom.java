package com.example.applicatione_commerce.Service;

public class MyListCCom {

    private String produit;
    private String client;
    private String date;
    private int img_produit;

    public MyListCCom(int URLimg, String nomProduit , String nomClient, String dateCommande){
        produit = nomProduit;
        client = nomClient;
        date = dateCommande;
        img_produit = URLimg;
    }

    public int getImg_produit(){
        return img_produit;
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