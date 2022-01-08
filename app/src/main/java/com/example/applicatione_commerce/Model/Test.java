package com.example.applicatione_commerce.Model;

public class Test {
    String rien;
    double Prix;

    public Test() {
    }

    public Test(String rien, double prix) {
        this.rien = rien;
        Prix = prix;
    }


    public String getRien() {
        return rien;
    }

    public void setRien(String rien) {
        this.rien = rien;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double prix) {
        Prix = prix;
    }
}
