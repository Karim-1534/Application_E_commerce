package com.example.applicatione_commerce.Service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.applicatione_commerce.R;

import java.util.ArrayList;

public class MyListPComAdapter extends ArrayAdapter<MyListProduit> {

    public MyListPComAdapter(@NonNull Context context, ArrayList<MyListProduit> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_produit_commercant, parent, false);
        }

        MyListProduit currentNumberPosition = getItem(position);

        ImageView produitImage = currentItemView.findViewById(R.id.img_produit);
        assert currentNumberPosition != null;
        produitImage.setImageBitmap(currentNumberPosition.getImageBitmap());

        TextView produit = currentItemView.findViewById(R.id.produit);
        produit.setText(currentNumberPosition.getProduit());

        TextView prix = currentItemView.findViewById(R.id.prix);
        prix.setText(currentNumberPosition.getPrix());

        return currentItemView;
    }
}
