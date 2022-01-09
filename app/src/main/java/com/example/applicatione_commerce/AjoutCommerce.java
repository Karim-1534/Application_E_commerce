package com.example.applicatione_commerce;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.applicatione_commerce.Api.ICallback;
import com.example.applicatione_commerce.Model.Rayon;
import com.example.applicatione_commerce.Model.Utilisateurs.Commercant;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AjoutCommerce extends Activity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button btn_valider;
    private CheckBox serviceAnimeaux;
    private CheckBox serviceaModeEtBeau;
    private CheckBox serviceLivre;
    private CheckBox serviceAuto;
    private CheckBox serviceJouets;
    private CheckBox serviceMaison;
    private CheckBox serviceAppreils;
    private CheckBox serviceMusique;
    private CheckBox serviceSport;
    private CheckBox serviceBureau;
    private List<CheckBox> checkBoxList= new ArrayList<>();
    List<DocumentReference> rayonSelectionne = new ArrayList<>();
    public List<DocumentReference> documts = new ArrayList<>();
    String choixSelectionne = "";

    private EditText nom;
    private EditText nSiret;
    private EditText email;
    private EditText mdp;

    String id;
    List<DocumentReference> dr = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_commercant);
        initActivity();
        onStart();

    }

    private void initActivity(){
        checkBoxList.clear();
        checkBoxList.add(serviceAnimeaux = (CheckBox) findViewById(R.id.animaux));
        checkBoxList.add(serviceLivre = (CheckBox) findViewById(R.id.livre));
        checkBoxList.add(serviceaModeEtBeau = (CheckBox) findViewById(R.id.mode));
        checkBoxList.add(serviceMaison = (CheckBox) findViewById(R.id.maison));
        checkBoxList.add( serviceAppreils = (CheckBox) findViewById(R.id.appareil));
        checkBoxList.add(serviceJouets = (CheckBox) findViewById(R.id.jouet));
        checkBoxList.add( serviceBureau = (CheckBox) findViewById(R.id.bureau));
        checkBoxList.add( serviceAuto = (CheckBox) findViewById(R.id.auto));
        checkBoxList.add( serviceSport = (CheckBox) findViewById(R.id.sport));
        checkBoxList.add(serviceMusique = (CheckBox) findViewById(R.id.musique));

        nom = (EditText) findViewById(R.id.nom_text);
        nSiret = (EditText) findViewById(R.id.num_siret);
        email = (EditText) findViewById(R.id.email_text);
        mdp = (EditText) findViewById(R.id.mdp_text);
        btn_valider = (Button) findViewById(R.id.btn_ajout_commerce);
        createOnClickValidationAjoutButton();

    }


    private void addDataToFirestore(String EMAIL, String MOTDEPASSE, String NOM, String NUMSIRET, List<DocumentReference> SERVICES){
        CollectionReference dbTest = db.collection("COMMERCANT");
        Commercant commercant = new Commercant(EMAIL, MOTDEPASSE, NOM, NUMSIRET, SERVICES);
        dbTest.add(commercant).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(AjoutCommerce.this, "Le commerçant a bien été ajouté", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AjoutCommerce.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private List<String> getTextCheckedBox(){

        List<String> rayonSelectionnes = new ArrayList<>();
        for (CheckBox c: checkBoxList){
            if(c.isChecked()){
                choixSelectionne = c.getText().toString().toUpperCase();
                rayonSelectionnes.add(choixSelectionne);
            }
        }
        return rayonSelectionnes;
    }



    private void getDataService(){
             //rayonSelectionne.clear();
            documts.clear();
            db.collection("RAYON").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            public void onSuccess(QuerySnapshot querySnapshot) {
               // Log.d(TAG, " résulat récupérer  avant" + querySnapshot.getQuery());
                if (querySnapshot.isEmpty()) {
                    Log.d(TAG, "onSuccess: LIST EMPTY");
                } else {
                    for (QueryDocumentSnapshot q: querySnapshot){
                        Iterator<QueryDocumentSnapshot> rayon = querySnapshot.iterator();
                        rayon.forEachRemaining(queryDocumentSnapshot ->{
                            List<String> nomRayons= getTextCheckedBox();
                            //Log.d("De la check box on récupére ça  ","" +nomRayons);
                            for (String nomRayon: nomRayons) {
                                if (nomRayon.equals(queryDocumentSnapshot.toObject(Rayon.class).getNOM())) {
                                    queryDocumentSnapshot.getData();
                                    id = queryDocumentSnapshot.getId();
                                    dr.add(queryDocumentSnapshot.getReference());
                                    //  Log.d("La référence récupérer est bien ", " " + queryDocumentSnapshot.getReference());
                                    if (!rayonSelectionne.contains(queryDocumentSnapshot.getReference())) {
                                        rayonSelectionne.add(queryDocumentSnapshot.getReference());
                                        initActivity();
                                      //  Log.d("Avant "," " +rayonSelectionne);
                                    }
                                }
                            }
                        });
                    }   documts.addAll(rayonSelectionne);
                    Log.d("La référence récupérer est bien ", " " + documts);
                    addDataToFirestore( email.getText().toString(),
                            mdp.getText().toString(),
                            nom.getText().toString(),
                            nSiret.getText().toString(),documts);


                }


            }

        });
            //getDataService();
    }

/*
    public void referencesRecuperer( List<DocumentReference> references){
        dr.addAll(references);
        Log.d("référence recuperer "," " + dr);
    }
*/


    private void createOnClickValidationAjoutButton(){
        btn_valider.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.getText().toString();
                mdp.getText().toString();
                nom.getText().toString();
                nSiret.getText().toString();
                getDataService();

                /*addDataToFirestore( email.getText().toString(),
                        mdp.getText().toString(),
                        nom.getText().toString(),
                        nSiret.getText().toString(),documts);*/

                Log.d(TAG, " Les documents récupérer "+documts);
                String str = getResources().getString(R.string.valid_ajout_commerce);
                Intent intent = new Intent(AjoutCommerce.this, Confirm.class);
                Bundle bundle = new Bundle();
                bundle.putString("type",str);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }



}
