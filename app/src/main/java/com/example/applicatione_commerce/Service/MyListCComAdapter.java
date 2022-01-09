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

public class MyListCComAdapter extends ArrayAdapter<MyListCCom>{

    public MyListCComAdapter(@NonNull Context context, ArrayList<MyListCCom> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_commande_commercant, parent, false);
        }

        MyListCCom currentNumberPosition = getItem(position);

        ImageView produitImage = currentItemView.findViewById(R.id.img_produit);
        assert currentNumberPosition != null;
        produitImage.setImageResource(currentNumberPosition.getImg_produit());

        TextView produit = currentItemView.findViewById(R.id.produit);
        produit.setText(currentNumberPosition.getProduit());

        TextView client = currentItemView.findViewById(R.id.nom_client);
        client.setText(currentNumberPosition.getClient());

        TextView date = currentItemView.findViewById(R.id.date);
        date.setText(currentNumberPosition.getDate());

        return currentItemView;
    }

}