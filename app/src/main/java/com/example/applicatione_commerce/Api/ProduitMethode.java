package com.example.applicatione_commerce.Api;

import static android.content.ContentValues.TAG;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.applicatione_commerce.MainActivity;
import com.example.applicatione_commerce.Model.Produit;
import com.example.applicatione_commerce.Model.Test;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class ProduitMethode {
    private static final DocumentReference RAYON = null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Produit produit;

    public Produit getDataProduit() {

        db.collection("PRODUITS")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                        } else {
                            List<Produit> produits = queryDocumentSnapshots.toObjects(Produit.class);
                            Collection<Produit> prod = queryDocumentSnapshots.toObjects(Produit.class);
                            ArrayList<Produit> listproduit = new ArrayList<>();
                            listproduit.addAll(produits);
                            for (Produit p: listproduit){
                                Log.d(TAG, "L'entité est : "+ p.getAll());
                                produit = p;
                            }
                           
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();

            }
        });
        return produit;
    }


    public void addProduitToFirestore(String DESCRIPTION, String NOM, Integer OFFRE, double PRIX, String urlPicture){


        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbTest = db.collection("PRODUITS");

        // adding our data to our courses object class.
        Produit produit = new Produit(DESCRIPTION, NOM, OFFRE, PRIX, RAYON, urlPicture);

        dbTest.add(produit).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                // Toast.makeText(MainActivity.this, "Your Course has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                //  Toast.makeText(MainActivity.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
            }
        });

    }

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
                        //    Toast.makeText(MainActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
              //  Toast.makeText(MainActivity.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
