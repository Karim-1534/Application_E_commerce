package com.example.applicatione_commerce;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.applicatione_commerce.Model.Rayon;
import com.example.applicatione_commerce.Model.Utilisateurs.Commercant;
import com.example.applicatione_commerce.Service.CustomDialogClass;
import com.example.applicatione_commerce.Service.MyListCommercant;
import com.example.applicatione_commerce.Service.MyListCommercantAdapter;
import com.example.applicatione_commerce.Service.MyListProduit;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class ConnexGestActivity extends Activity {
    private ListView listView;
    final ArrayList<MyListCommercant> arrayList = new ArrayList<MyListCommercant>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<DocumentReference> listservice = new ArrayList<>();
    private MyListCommercantAdapter commercantAdapter;

    String commerce[]
            = {"Commerçant 1","Commerçant 2","Commerçant 3","Commerçant 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilgestionnaire);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getData();

    }

    private void getData() {
        db.collection("COMMERCANT")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        listView = (ListView) findViewById(R.id.listView);
                        if (queryDocumentSnapshots.isEmpty()) {
                        } else {
                            List<Commercant> commercant = queryDocumentSnapshots.toObjects(Commercant.class);
                            ArrayList<Commercant> listcommercnt = new ArrayList<>();
                            listcommercnt.addAll(commercant);
                            for (Commercant c : listcommercnt) {
                                arrayList.add(new MyListCommercant(c.getNOM(), ""));
                            }
                        }

                        commercantAdapter = new MyListCommercantAdapter(ConnexGestActivity.this, arrayList);
                        listView.setAdapter(commercantAdapter);

                    }

                });
    }



    public void ajout(View v){
        Intent intent = new Intent(this, AjoutCommerce.class);
        startActivity(intent);
    }

    public void modifier(View v){
        Intent intent = new Intent(this, ModifCommerce.class);
        startActivity(intent);
    }

    public void supprimer(View v){

        CustomDialogClass cdd = new CustomDialogClass(ConnexGestActivity.this);
        cdd.setResult("Commerçant supprimé");
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.text.setText("Voulez-vous vraiment supprimer ce commerçant ?");


    }


}
