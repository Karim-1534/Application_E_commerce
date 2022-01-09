package com.example.applicatione_commerce.Model;






import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.google.firebase.firestore.DocumentReference;

import java.util.List;
import java.util.Map;

public class Produit {

    String DESCRIPTION;
    String NOM;
    Integer OFFRE;
    Double PRIX;
    DocumentReference RAYON;
    String SERVICE;
    String urlPicture;



    public Produit() {
    }

    public Produit(String DESCRIPTION, String NOM, Integer OFFRE, Double PRIX, DocumentReference RAYON, String SERVICE, String urlPicture) {
        this.DESCRIPTION = DESCRIPTION;
        this.NOM = NOM;
        this.OFFRE = OFFRE;
        this.PRIX = PRIX;
        this.RAYON = RAYON;
        this.SERVICE = SERVICE;
        this.urlPicture = urlPicture;
    }

    public Produit(String DESCRIPTION, String NOM, Double PRIX, DocumentReference RAYON, String SERVICE, String urlPicture) {
        this.DESCRIPTION = DESCRIPTION;
        this.NOM = NOM;
        this.PRIX = PRIX;
        this.RAYON = RAYON;
        this.SERVICE = SERVICE;
        this.urlPicture = urlPicture;
    }

    public String getSERVICE() {
        return SERVICE;
    }

    public void setSERVICE(String SERVICE) {
        this.SERVICE = SERVICE;
    }
    public DocumentReference getRAYON() {
        return RAYON;
    }

    public void setRAYON(DocumentReference RAYON) {
        this.RAYON = RAYON;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public Integer getOFFRE() {
        return OFFRE;
    }

    public void setOFFRE(Integer OFFRE) {
        this.OFFRE = OFFRE;
    }

    public Double getPRIX() {
        return PRIX;
    }

    public void setPRIX(Double PRIX) {
        this.PRIX = PRIX;
    }


    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

/*
    public String getAll(){
       return "Le nom:  " +this.getNOM()+"\n "+
               "Le PRIX:  "+ this.getPRIX()+"\n "+
               "La description: "+ this.getDESCRIPTION()+"\n "+
               this.getOFFRE()+"\n "+
               this.getRAYON()+"\n "+
               this.getUrlPicture();

    }*/
}

