package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AjoutAnnonce extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_annonce);
        List<String> list = new ArrayList<String>();
        list.add("------");
        list.add("Animaux de compagnie");
        list.add("Mode et beauté");
        list.add("Maison et bricolage");
        list.add("Appareils & Electronique");
        list.add("Musique, vidéo et jeux");
        list.add("Livres et lecture");
        list.add("Jouets, enfants et bébé");
        list.add("Auto et Moto");
        list.add("Bureau et Professionnel");
        list.add("Sport et boutique de fans");

        Spinner spinner = (Spinner) findViewById(R.id.categorie);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,list);
        spinner.setAdapter(adapter);


        Button btn_valider = (Button) findViewById(R.id.btn_ajout_annonce);
        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = getResources().getString(R.string.conf_ajout_annonce);
                Intent intent = new Intent(AjoutAnnonce.this, Confirm.class);
                Bundle bundle = new Bundle();
                bundle.putString("type",str);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
