package com.example.applicatione_commerce.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rayon {
    String NOM;
    List<String> SERVICES;

    public Rayon() {
    }

    public Rayon(String NOM, List<String> SERVICES) {
        this.NOM = NOM;
        this.SERVICES = SERVICES;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public List<String> getSERVICES() {
        return SERVICES;
    }

    public void setSERVICES(List<String> SERVICES) {
        this.SERVICES = SERVICES;
    }
}
