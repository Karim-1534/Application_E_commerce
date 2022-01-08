package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ConnexGestActivity extends Activity {
    private ListView listView;
    String commerce[]
            = {"Commerçant 1","Commerçant 2","Commerçant 3","Commerçant 4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilgestionnaire);
        initActivity();

    }

    private void initActivity(){

        listView = (ListView)findViewById(R.id.listView);
        initListView();
    }

    private void initListView() {
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(
                this,
                R.layout.list_commercant,
                R.id.nom_commer_,
                commerce);
        listView.setAdapter(arr);
    }


    public void ajout(View v){
        Intent intent = new Intent(this, AjoutCommerce.class);
        startActivity(intent);
    }

    public void modifier(View v){
        Intent intent = new Intent(this, ModifCommerce.class);
        startActivity(intent);
    }

    public void supprimer(View v){

        CustomDialogClass cdd = new CustomDialogClass(ConnexGestActivity.this);
        cdd.setResult("Commerçant supprimé");
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.text.setText("Voulez-vous vraiment supprimer ce commerçant ?");


    }


}
