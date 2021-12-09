package com.example.applicatione_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.applicatione_commerce.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.GoogleAuthCredential;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.prefs.PreferenceChangeEvent;


public class MainActivity extends AppCompatActivity {
    GoogleCredential credential = GoogleCredential.fromStream(resourceAsStream);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulaireacceuilconnexion);

        FileInputStream serviceAccount =
                new FileInputStream("path/to/serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://application-e-commerce-b47b4-default-rtdb.europe-west1.firebasedatabase.app")
                .build();

        FirebaseApp.initializeApp(options);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

}
