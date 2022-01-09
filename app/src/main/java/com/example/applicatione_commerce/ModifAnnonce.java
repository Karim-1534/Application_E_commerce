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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;


import com.example.applicatione_commerce.Model.Produit;
import com.example.applicatione_commerce.Model.Utilisateurs.Commercant;
import com.example.applicatione_commerce.Service.CustomDialogClass;
import com.example.applicatione_commerce.Service.MyListPCliAdapter;
import com.example.applicatione_commerce.Service.MyListPComAdapter;
import com.example.applicatione_commerce.Service.MyListProduit;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class ModifAnnonce extends Activity {
    private Button btn_valider ;
    private List<String> list = new ArrayList<String>();
    private Spinner spinner;
    private ListView listView ;
    final ArrayList<MyListProduit> arrayList = new ArrayList<MyListProduit>();
    private MyListPComAdapter produitAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String produit[]
            = {"Produit 1","Produit 2","Produit 3"};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_annonce);
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
                        listView = (ListView)findViewById(R.id.listView);
                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                        } else {
                            List<Commercant> commercant = queryDocumentSnapshots.toObjects(Commercant.class);
                            commercant.get(0).getPRODUITS().forEach(documentReference -> {
                                        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                Produit produit = (documentSnapshot.toObject(Produit.class));
                                                try {
                                                    arrayList.add(new MyListProduit(produit.getUrlPicture(), produit.getNOM(),produit.getPRIX()));
                                                } catch (MalformedURLException e) {
                                                    e.printStackTrace();
                                                }
                                                initActivity();
                                            }
                                        });
                                    });
                        }
                        produitAdapter = new MyListPComAdapter(ModifAnnonce.this, arrayList);
                        listView.setAdapter(produitAdapter);
                    }

                });
        }



    private void initActivity(){

        spinner = (Spinner) findViewById(R.id.filtre);
        initSpinnerDispo();

        btn_valider = (Button) findViewById(R.id.btn_modif_annonce);
        createOnClickValidationModifButton();

    }


    /*
     *événement boutton ajout validation de la modification
     *
     * */

    private void createOnClickValidationModifButton(){
        btn_valider.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = getResources().getString(R.string.valid_modif_annonce);
                Intent intent = new Intent(ModifAnnonce.this, Confirm.class);
                Bundle bundle = new Bundle();
                bundle.putString("type",str);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onResume() {

        super.onResume();
        initActivity();
    }
    /*
     * initialisation du spinner disponibilité
     * */
    private void initSpinnerDispo(){
        list.clear();
        list.add("------");
        list.add("Disponible");
        list.add("Indisponible");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,list);
        spinner.setAdapter(adapter);

    }


    public void supprimer(View v){

        CustomDialogClass cdd = new CustomDialogClass(ModifAnnonce.this);
        cdd.setResult("Produit supprimé");
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.text.setText("Voulez-vous vraiment supprimer ce produit ?");


    }


}