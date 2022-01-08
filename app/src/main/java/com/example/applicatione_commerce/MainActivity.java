package com.example.applicatione_commerce;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity{
    private  List<String> list;
    private Spinner spinner;
    private Intent intent;
    private Bundle bundle = new Bundle();

    public void inscription(View v){
        intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }

    public void connexion(View v){
        spinner = (Spinner) findViewById(R.id.choixcatego);
        TextView type = (TextView) spinner.getSelectedView();
        if(type.getText().equals("Gestionnaire")){
            intent = new Intent(this, ConnexGestActivity.class);
            startActivity(intent);
        }else if(type.getText().equals("Commerçant")){
            intent = new Intent(this, ConnexComActivity.class);
            startActivity(intent);
        }else if(type.getText().equals("Client")){
            String str = "connecté";
            intent = new Intent(this, ConnexClientActivity.class);
            bundle.putString("type",str);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilconnexion);
        initActivity();

    }

    private void initActivity() {

        list = new ArrayList<String>();
        spinner = (Spinner) findViewById(R.id.choixcatego);
        createSpinnerCategorie();

    }

    private void createSpinnerCategorie() {
        list.clear();
        list.add("Gestionnaire");
        list.add("Commerçant");
        list.add("Client");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,list);
        spinner.setAdapter(adapter);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public void anonyme(View view) {
        String str = "anonyme";
        intent = new Intent(this, ConnexClientActivity.class);
        bundle.putString("type",str);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
