package com.example.applicatione_commerce;



import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.applicatione_commerce.Api.ProduitMethode;
import com.example.applicatione_commerce.Model.Panier;
import com.example.applicatione_commerce.Model.Produit;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends Activity{
    private  List<String> list;
    private Spinner spinner;
    private Intent intent;
    private Bundle bundle = new Bundle();


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProduitMethode pm ;
    Panier p;


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

            List<String> list = new ArrayList<String>();
            list.add("Gestionnaire");
            list.add("Commerçant");
            list.add("Client");
            Spinner spinner = (Spinner) findViewById(R.id.choixcatego);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,list);
            spinner.setAdapter(adapter);
            getDataProduit();

/*
            double prixTest = 1250.01;
            String TestString = "CHIEN";
            addDataToFirestore(TestString, prixTest);*/
    }
/*
            private void getListTest() {


                db.collection("Test")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (queryDocumentSnapshots.isEmpty()) {
                                    Log.d(TAG, "onSuccess: LIST EMPTY");
                                } else {
                                    List<Test> produits = queryDocumentSnapshots.toObjects(Test.class);

                                    ArrayList<Test> listproduit = new ArrayList<>();
                                    listproduit.addAll(produits);

                                    Log.d(TAG, "onSuccess: " + listproduit.toString());
                                    for (Test t: listproduit){
                                        Log.d(TAG, "L'entité est : ");
                                        Log.d(TAG, "Le Prix est: " + t.getPrix());
                                        Log.d(TAG, "Le rien est: " + t.getRien());
                                    }
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
                    }
                });
            }
*/
/*
    private void addDataToFirestore(String Testrien, double TestPrix ){
        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbTest = db.collection("Test");

        // adding our data to our courses object class.
        Test test = new Test(Testrien, TestPrix);

        dbTest.add(test).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(MainActivity.this, "Your Course has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(MainActivity.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
            }
        });

    }
*/
/*
    private void updateData(){
         ArrayList<Test> testsArrayList = null;
        db.collection("Test").get().
                addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                Test t = d.toObject(Test.class);
                                t.setRien(d.getId());
                                testsArrayList.add(t);
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
    /*
        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                @Override
                                                public void onSuccess(QuerySnapshot querySnapshot) {
                                                    if(querySnapshot.isEmpty()){
                                                        Log.d(TAG, "onSuccess: LIST EMPTY");
                                                    }else {
                                                        List<Produit> produits = querySnapshot.toObjects(Produit.class);
                                                        ArrayList<Produit> listProduits = new ArrayList<>();

                                                    }
                                                }
                                            });
    });*/

    public void getDataProduit() {
       // List<String> listDesRefproduits = null;
        db.collection("PANIER")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d(TAG, " Réf panier "+(db.collection("PANIER").get().toString()));
                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                        } else {
                            List<Panier> paniers = queryDocumentSnapshots.toObjects(Panier.class);
                            ArrayList<Panier> listpanier = new ArrayList<>();
                            listpanier.addAll(paniers);
                            for (Panier p: listpanier){
                                p.getPRODUITS();
                                p.getPRODUITS().forEach(documentReference -> {
                                    documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            Produit produits = (documentSnapshot.toObject(Produit.class));
                                            ArrayList<Produit> listProduit = new ArrayList<>();
                                            listProduit.add(produits);
                                            for (Produit produit: listProduit){

                                                Log.d(TAG, "Le produit mis dans le panier "+ produit.getAll());
                                                Log.d(TAG, "La quantité du produit "+ p.getQUANTITE() );
                                                Log.d(TAG,"Le totale du panier est  " + p.getTOTALE());
                                            }
                                        }
                                    });
                                });
                            }

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();

            }
        });
      //  return listDesRefproduits;
    }
/*

    public void produitDuPanier(List<String> stringList){
        stringList = getDataProduit();
    }*/


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
