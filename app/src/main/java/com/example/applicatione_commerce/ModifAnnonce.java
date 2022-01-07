package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class ModifAnnonce extends Activity {
    private Button btn_valider ;
    private List<String> list = new ArrayList<String>();
    private Spinner spinner;
    private ListView listView ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_annonce);
        initActivity();
    }




    private void initActivity(){

        spinner = (Spinner) findViewById(R.id.dispo);
        initSpinnerDispo();

        btn_valider = (Button) findViewById(R.id.btn_modif_annonce);
        createOnClickValidationModifButton();

        listView = (ListView)findViewById(R.id.listView);
        initListView();

    }

    private void initListView() {
        String produit[]
                = {"Produit","Produit","Produit"};
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(
                this,
                R.layout.list_annonce,
                R.id.nom_annonce,
                produit);
        listView.setAdapter(arr);
    }


    /*
     *événement boutton ajout validation de la modification
     *
     * */

    private void createOnClickValidationModifButton(){
        btn_valider.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = getResources().getString(R.string.conf_modif_annonce);
                Intent intent = new Intent(ModifAnnonce.this, Confirm.class);
                Bundle bundle = new Bundle();
                bundle.putString("type",str);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onResume() {

        super.onResume();
        initActivity();
    }
    /*
     * initialisation du spinner disponibilité
     * */
    private void initSpinnerDispo(){
        list.clear();
        list.add("------");
        list.add("Disponible");
        list.add("Indisponible");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,list);
        spinner.setAdapter(adapter);

    }


}