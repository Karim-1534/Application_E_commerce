package com.example.applicatione_commerce.Api;

import static android.content.ContentValues.TAG;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.applicatione_commerce.MainActivity;
import com.example.applicatione_commerce.Model.Test;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AppliApi {

    private static final String COLLECTION_NAME = "Test";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static CollectionReference getTestCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);    }



    private void getData() {
        db.collection(COLLECTION_NAME)
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

}
