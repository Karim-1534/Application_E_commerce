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

public class MyListCommercantAdapter extends ArrayAdapter<MyListCommercant> {

    public MyListCommercantAdapter(@NonNull Context context, ArrayList<MyListCommercant> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_commercant, parent, false);
        }

        MyListCommercant currentNumberPosition = getItem(position);

        TextView commercant = currentItemView.findViewById(R.id.nom_commer_);
        commercant.setText(currentNumberPosition.getCommercant());


        return currentItemView;
    }
}
