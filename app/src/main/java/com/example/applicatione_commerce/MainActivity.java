package com.example.applicatione_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicatione_commerce.R;
/*import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.GoogleAuthCredential;*/
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;


public class MainActivity extends AppCompatActivity {
    //GoogleCredential credential = GoogleCredential.fromStream(resourceAsStream);
    public void inscription(View v){
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }

    public void connexion(View v){
        Spinner spinner = (Spinner) findViewById(R.id.choixcatego);
        TextView type = (TextView) spinner.getSelectedView();
        Toast.makeText(this, type.getText(), Toast.LENGTH_LONG).show();

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
