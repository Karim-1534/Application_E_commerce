package com.example.applicatione_commerce.Service;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.applicatione_commerce.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class MyListPCliAdapter extends ArrayAdapter<MyListProduit> {

    private ArrayList<MyListProduit> panier = new ArrayList<>();
    private static Gson gson;

    public MyListPCliAdapter(@NonNull Context context, ArrayList<MyListProduit> arrayList) {
        super(context, 0, arrayList);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_produit_client, parent, false);
        }

        MyListProduit currentProduit = getItem(position);

        ImageView produitImage = currentItemView.findViewById(R.id.img_produit);
        assert currentProduit != null;
        Log.d("URI", String.valueOf(currentProduit.getImg_produit()));
        produitImage.setImageBitmap(currentProduit.getImageBitmap());

        TextView produit = currentItemView.findViewById(R.id.produit);
        produit.setText(currentProduit.getProduit());

        TextView prix = currentItemView.findViewById(R.id.prix);
        prix.setText(currentProduit.getPrix());

        TextView commercant = currentItemView.findViewById(R.id.commercant);
        commercant.setText(currentProduit.getCommercant());


        ImageButton addpanier = (ImageButton) currentItemView.findViewById(R.id.panier);
        View finalCurrentItemView = currentItemView;
        addpanier.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(finalCurrentItemView.getContext(), produit.getText()+" ajouté au panier", Toast.LENGTH_SHORT).show();
                panier.add(currentProduit);
            }
        });

        return currentItemView;
    }

    public ArrayList<MyListProduit> getPanier(){
        return panier;
    }

    public static Gson getGsonParser() {
        if(null == gson) {
            GsonBuilder builder = new GsonBuilder();
            gson = builder.create();
        }
        return gson;
    }
}
