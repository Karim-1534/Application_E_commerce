package com.example.applicatione_commerce.Service;

public class MyListCCli {

    private String commande;
    private String statut;
    private int img_produit;

    public MyListCCli(int URLimg, String nomCommande , String Strstatut){
        commande = nomCommande;
        statut = Strstatut;
        img_produit = URLimg;
    }

    public int getImg_produit(){
        return img_produit;
    }

    public String getCommande(){
        return commande;
    }

    public String getStatut(){
        return statut;
    }

}