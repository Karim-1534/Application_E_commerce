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

public class MyListCCliAdapter extends ArrayAdapter<MyListCCli>{

    public MyListCCliAdapter(@NonNull Context context, ArrayList<MyListCCli> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_commande_client, parent, false);
        }

        MyListCCli currentNumberPosition = getItem(position);


        TextView commande = currentItemView.findViewById(R.id.commande);
        commande.setText(currentNumberPosition.getCommande());

        TextView statut = currentItemView.findViewById(R.id.statut);
        statut.setText(currentNumberPosition.getStatut());

        return currentItemView;
    }

}

/*
}

 */