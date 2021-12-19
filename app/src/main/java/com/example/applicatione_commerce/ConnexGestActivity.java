package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConnexGestActivity extends Activity {
    String commerce[]
            = { "Commercant" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilgestionnaire);
        ListView listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(
                this,
                R.layout.list_item,
                R.id.nom_commer_,
                commerce);
        listView.setAdapter(arr);
    }

    public void ajout(View v){
        Intent intent = new Intent(this, AjoutCommerce.class);
        startActivity(intent);
    }

    public void modifier(View v){
        Toast.makeText(this, "modifier", Toast.LENGTH_SHORT).show();
    }

    public void supprimer(View v){
        Toast.makeText(this, "supprimer", Toast.LENGTH_SHORT).show();
    }
}
