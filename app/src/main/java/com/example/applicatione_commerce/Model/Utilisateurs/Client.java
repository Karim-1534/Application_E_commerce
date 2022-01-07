package com.example.applicatione_commerce.Model.Utilisateurs;

public class Client  {
    String adresse;
    String email;
    String motdepasse;
    Integer numero_tel;
    String nom;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public Integer getNumero_tel() {
        return numero_tel;
    }

    public void setNumero_tel(Integer numero_tel) {
        this.numero_tel = numero_tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
