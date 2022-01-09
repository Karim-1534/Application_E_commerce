package com.example.applicatione_commerce.Service;

public class MyListCommercant {

    private String commercant;
    private String categorie;

    public MyListCommercant(String nomCommercant , String nomCategorie){
        commercant = nomCommercant;
        categorie = nomCategorie;
    }

    public String getCommercant(){
        return commercant;
    }

    public String getCategorie(){
        return categorie;
    }
}
