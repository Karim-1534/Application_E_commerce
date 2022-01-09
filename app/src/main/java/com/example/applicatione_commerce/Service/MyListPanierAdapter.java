package com.example.applicatione_commerce.Service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.applicatione_commerce.R;

import java.util.ArrayList;

public class MyListPanierAdapter extends ArrayAdapter<MyListProduit> {

    public MyListPanierAdapter(@NonNull Context context, ArrayList<MyListProduit> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;

        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_panier, parent, false);
        }

        MyListProduit currentProduit = getItem(position);

        ImageView produitImage = currentItemView.findViewById(R.id.img_produit);
        assert currentProduit != null;
        produitImage.setImageBitmap(currentProduit.getImageBitmap());

        TextView produit = currentItemView.findViewById(R.id.produit);
        produit.setText(currentProduit.getProduit());

        TextView prix = currentItemView.findViewById(R.id.prix);
        prix.setText(currentProduit.getPrix());

        TextView commercant = currentItemView.findViewById(R.id.commercant);
        commercant.setText(currentProduit.getCommercant());

        NumberPicker np = currentItemView.findViewById(R.id.quantite);
        np.setValue(1);
        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(true);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                currentProduit.setQuantite(picker.getValue());
            }
        });

        ImageButton deleteproduit = (ImageButton) currentItemView.findViewById(R.id.delete);
        View finalCurrentItemView = currentItemView;
        deleteproduit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                remove(currentProduit);
            }
        });

        return currentItemView;
    }

}
