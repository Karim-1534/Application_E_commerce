package com.example.applicatione_commerce.Service;

public class MyListCCli {

    private String commande;
    private String statut;

    public MyListCCli(String nomCommande , String Strstatut){
        commande = nomCommande;
        statut = Strstatut;
    }


    public String getCommande(){
        return commande;
    }

    public String getStatut(){
        return statut;
    }

}