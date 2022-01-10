package com.example.applicatione_commerce;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.applicatione_commerce.Model.Commandes.Commande;
import com.example.applicatione_commerce.Model.Commandes.CommandesClient;
import com.example.applicatione_commerce.Model.Panier;
import com.example.applicatione_commerce.Model.Produit;
import com.example.applicatione_commerce.Model.Utilisateurs.Commercant;
import com.example.applicatione_commerce.Service.MyListProduit;
import com.example.applicatione_commerce.Service.MyListPanierAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class PanierActivity extends Activity {

    private ListView listView;
    private Intent intent;
    private Bundle bundle;
    private Button btn_connexion;
    private ImageButton btn_commande;
    private NumberPicker np;
    private Spinner spinner;
    private ArrayList<String> list;
    private ArrayList<String> produits ;
    private Button btn_valider;
    final ArrayList<MyListProduit> arrayList = new ArrayList<MyListProduit>();
    List<DocumentReference> prod = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panier);
        initActivity();

    }

    private void initActivity() {
        intent = getIntent();
        bundle = intent.getExtras();

        listView = (ListView)findViewById(R.id.listView);
        initListView();


        btn_connexion = (Button) findViewById(R.id.btn_connexion);
        btn_commande = (ImageButton) findViewById(R.id.btn_commandes);
        setBtn();

        btn_valider = (Button) findViewById(R.id.valider);
        createPanierandCommande();


    }

    private void createPanierandCommande() {
        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Panier panier = new Panier();
                panier.setTOTALE(0);
                List<DocumentReference> produitpanier = new ArrayList<>();
                db.collection("PRODUITS")
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                //DocumentReference referencesPanier =
                                if (queryDocumentSnapshots.isEmpty()) {
                                    Log.d(TAG, "onSuccess: LIST EMPTY");
                                } else {
                                    List<DocumentSnapshot> produitSnap = queryDocumentSnapshots.getDocuments();
                                    List<DocumentReference> produitRef = new ArrayList<>();
                                    produitSnap.forEach(documentSnapshot -> {
                                        produitRef.add(documentSnapshot.getReference());
                                    });
                                    List<Produit> listProduit = queryDocumentSnapshots.toObjects(Produit.class);
                                    for(int i=0; i<listProduit.size(); i++) {
                                        int finalI = i;
                                        arrayList.forEach(myListProduit -> {
                                            if (myListProduit.getProduit().equals(listProduit.get(finalI).getNOM())) {
                                                for(int j=0; j<=myListProduit.getQuantite(); j++) {
                                                    produitpanier.add(produitRef.get(finalI));
                                                    panier.setTOTALE(panier.getTOTALE()+Float.parseFloat(myListProduit.getPrix()));
                                                }
                                            }
                                        });
                                    }
                                    panier.setPRODUITS(produitpanier);
                                    db.collection("PANIER").add(panier).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                @Override
                                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                    db.collection("COMMERCANT").get()
                                                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                @Override
                                                                public void onSuccess(QuerySnapshot querySnapshot) {
                                                                    List<DocumentReference> refProduit =  documentSnapshot.toObject(Panier.class).getPRODUITS();
                                                                    List<DocumentReference> refCommers = new ArrayList<>();
                                                                    DocumentReference refComm = null;
                                                                    for(DocumentSnapshot q : querySnapshot){
                                                                            refComm = q.getReference();
                                                                            refCommers.add(refComm);
                                                                            Commercant comme = q.toObject(Commercant.class);

                                                                        DocumentReference finalRefComm = refComm;
                                                                        refProduit.forEach(documentReference1 -> {
                                                                                 refCommers.forEach(documentReference2 -> {
                                                                                  documentReference2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                                         @Override
                                                                                         public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                                             Commercant c = documentSnapshot.toObject(Commercant.class);
                                                                                            // Log.d("La réf du produit du c", "" + c.getPRODUITS());
                                                                                             List<DocumentReference> rp = new ArrayList<>();

                                                                                                     if(c.getPRODUITS()!=null){
                                                                                                         if(c.getPRODUITS().contains(documentReference1)){
                                                                                                             if(!prod.contains(documentReference1)) {
                                                                                                                 prod.add(documentReference1);
                                                                                                                 //DocumentReference refc = c;
                                                                                                                 Log.d("Réf commer",""+ documentSnapshot.getReference());
                                                                                                                 DocumentReference rc = documentSnapshot.getReference();
                                                                                                                 Log.d("La réf du prodiut 1", "" + prod);
                                                                                                                    addCommandeToFirestore(prod, rc, rc, ""+ Timestamp.now().toDate().toString(), "");
                                                                                                             }
                                                                                                         }
                                                                                                     }
                                                                                         }
                                                                                     });

                                                                                 });
                                                                             });

                                                                    }

                                                                }
                                                            });
                                                }
                                            });
                                            //db.collection("PANIER").getFirestore().clearPersistence();

                                            String str = getResources().getString(R.string.valid_panier);
                                            Intent intent = new Intent(PanierActivity.this, Confirm.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("type",str);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }


                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(PanierActivity.this, "Echec commande \n" + e, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
            }
        });
    }

    public void addCommandeToFirestore(List<DocumentReference> PRODUITS, DocumentReference CLIENT, DocumentReference COMMERCANT, String DATE, String STATUT){

        CollectionReference dbTest = db.collection("COMMANDES");
        Commande commande = new Commande(PRODUITS, CLIENT, COMMERCANT, DATE, STATUT);
        dbTest.add(commande).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(PanierActivity.this, "La commande a bien été ajouté", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PanierActivity.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setBtn() {
        if(bundle.getString("type").equals("connecté")){
            btn_connexion.setVisibility(View.GONE);
            btn_commande.setVisibility(View.VISIBLE);

        }else if(bundle.getString("type").equals("anonyme")){
            btn_connexion.setVisibility(View.VISIBLE);
            btn_commande.setVisibility(View.GONE);

        }

    }

    private void initListView() {
        produits= bundle.getStringArrayList("panier");

        for (String p: produits ) {
            Log.d("produit courant :", p);
            JSONObject produitstojson = null;
            try {
                produitstojson = new JSONObject(p);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (produitstojson != null) {
                try {
                    arrayList.add(new MyListProduit(produitstojson.getString("img_produit"), produitstojson.getString("produit"), produitstojson.getDouble("prix"),  produitstojson.getString("commercant")));
                } catch (JSONException | MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }

        MyListPanierAdapter panierAdapter = new MyListPanierAdapter(this, arrayList);
        listView.setAdapter(panierAdapter);
    }

    public void connexion(View view) {
        intent = new Intent(PanierActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intenthome = new Intent(this, ConnexClientActivity.class);
        Bundle bundlehome = new Bundle();
        bundlehome.putString("type",bundle.getString("type"));
        intenthome.putExtras(bundlehome);
        startActivity(intenthome);
    }

    public void commandes(View view) {
        Intent intentcommande = new Intent(this, CommandeClientActivity.class);
        Bundle bundlecommande = new Bundle();
        bundlecommande.putString("type",bundle.getString("type"));
        intentcommande.putExtras(bundlecommande);
        startActivity(intentcommande);
    }

    public void vider(View view) {

        db.collection("COMMANDES").document()
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });

        produits.clear();
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(
                this,
                R.layout.list_panier,
                R.id.produit,
                produits);
        listView.setAdapter(arr);
    }
}
