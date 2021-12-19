package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConnexComActivity  extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1_com);
    }

    public void ajout(View v) {
        Intent intent = new Intent(this, AjoutAnnonce.class);
        startActivity(intent);

    }

}
