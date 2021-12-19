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
import java.util.prefs.PreferenceChangeEvent;



public class MainActivity extends Activity{

    public void inscription(View v){
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }

    public void connexion(View v){
        Spinner spinner = (Spinner) findViewById(R.id.choixcatego);
        TextView type = (TextView) spinner.getSelectedView();
        if(type.getText().equals("Gestionnaire")){
            Intent intent = new Intent(this, ConnexGestActivity.class);
            startActivity(intent);
        }else if(type.getText().equals("Commerçant")){
            Intent intent = new Intent(this, ConnexComActivity.class);
            startActivity(intent);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulaireacceuilconnexion);
        List<String> list = new ArrayList<String>();
        list.add("Gestionnaire");
        list.add("Commerçant");
        list.add("Client");
        Spinner spinner = (Spinner) findViewById(R.id.choixcatego);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,list);
        spinner.setAdapter(adapter);




        /*FileInputStream serviceAccount =
                new FileInputStream("path/to/serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://application-e-commerce-b47b4-default-rtdb.europe-west1.firebasedatabase.app")
                .build();

        FirebaseApp.initializeApp(options);*/
    }


    @Override
    public void onResume() {
        super.onResume();
    }

}
