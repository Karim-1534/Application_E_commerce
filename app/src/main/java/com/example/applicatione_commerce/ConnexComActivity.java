package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConnexComActivity  extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilcommercant);
    }

    public void ajout(View v) {
        Intent intent = new Intent(this, AjoutAnnonce.class);
        startActivity(intent);
    }

    public void modif(View v) {
        Intent intent = new Intent(this, ModifAnnonce.class);
        startActivity(intent);
    }

    public void consulter(View view) {
        Intent intent = new Intent(this, CommandeRecueActivity.class);
        startActivity(intent);
    }
}
