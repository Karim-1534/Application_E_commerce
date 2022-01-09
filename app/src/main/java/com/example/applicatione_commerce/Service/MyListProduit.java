package com.example.applicatione_commerce.Service;

import static android.content.ContentValues.TAG;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MyListProduit {
    private String produit;
    private double prix;
    private String commercant;
    private URL img_produit;
    private int quantite;

    public MyListProduit(String URLimg, String nomProduit , double prixProduit, String nomCommercant) throws MalformedURLException {
        produit = nomProduit;
        prix = prixProduit;
        commercant = nomCommercant;
        img_produit =  new URL(URLimg);
    }

    public MyListProduit(String URLimg, String nomProduit , double prixProduit) throws MalformedURLException {
        produit = nomProduit;
        prix = prixProduit;
        img_produit = new URL(URLimg);
    }

    public URL getImg_produit(){
        return img_produit;
    }

    public String getProduit(){
        return produit;
    }

    public String getPrix(){
        return ""+prix;
    }

    public String getCommercant(){
        return commercant;
    }

    public Bitmap getImageBitmap() {
        Bitmap bm = null;
        try {
            URLConnection conn = img_produit.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "Error getting bitmap", e);
        }
        return bm;
    }


    public void setQuantite(int q){
        quantite = q;
    }

}
