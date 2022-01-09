package com.example.applicatione_commerce;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicatione_commerce.Model.Panier;
import com.example.applicatione_commerce.Model.Produit;
import com.example.applicatione_commerce.Model.Utilisateurs.Commercant;
import com.example.applicatione_commerce.Service.MyListPCliAdapter;
import com.example.applicatione_commerce.Service.MyListPanierAdapter;
import com.example.applicatione_commerce.Service.MyListProduit;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnexClientActivity extends Activity {
    String produits[]
            = {"Tee-shirt","Chemise","Pantalon","AK-47"};
    ArrayList<MyListProduit> panier = new ArrayList<MyListProduit>();
    private ListView listView;
    private Intent intent;
    private Bundle bundle;
    private Button btn_connexion;
    private ImageButton btn_commande;
    private NumberPicker np;
    private Spinner spinner;
    private ArrayList<String> list;
    final ArrayList<MyListProduit> arrayList = new ArrayList<MyListProduit>();
    private MyListPCliAdapter produitAdapter;
    Intent intentpanier = new Intent(this, PanierActivity.class);
    Bundle bundlepanier = new Bundle();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<DocumentReference> listProduit = new ArrayList<>();
    List<Produit> listProduits = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilclient);
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
                        HashMap<String, List<Produit>> produitHashMap = new HashMap<>();
                        Log.d(TAG, " Réf commercant " + (db.collection("COMMERCANT").get().toString()));
                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                        } else {
                            List<Commercant> commercant = queryDocumentSnapshots.toObjects(Commercant.class);
                            ArrayList<Commercant> listcommercnt = new ArrayList<>();
                            listcommercnt.addAll(commercant);
                            for(Commercant c: listcommercnt) {
                                if (c.getPRODUITS() != null) {
                                    listProduit = c.getPRODUITS();
                                    Log.d("liste", String.valueOf(listProduit));
                                    listProduit.forEach(documentReference -> {
                                        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                Produit produit = (documentSnapshot.toObject(Produit.class));
                                                try {
                                                    arrayList.add(new MyListProduit(produit.getUrlPicture(), produit.getNOM(), produit.getPRIX(), c.getNOM()));
                                                } catch (MalformedURLException e) {
                                                    e.printStackTrace();
                                                }

                                                initActivity();
                                            }
                                        });
                                    });
                                    produitHashMap.put(c.getNOM(), listProduits);
                                }
                            }
                        }
                        produitHashMap.forEach((commercant, produits) -> {
                            produits.forEach(produit -> {
                            });
                        });
                        produitAdapter = new MyListPCliAdapter(ConnexClientActivity.this, arrayList);
                        listView.setAdapter(produitAdapter);
                    }

                });
    }

    private void initActivity(){

        intent = getIntent();
        bundle = intent.getExtras();
        btn_connexion = (Button) findViewById(R.id.btn_connexion);
        btn_commande = (ImageButton) findViewById(R.id.btn_commandes);
        setBtn();

        list = new ArrayList<String>();
        spinner = (Spinner) findViewById(R.id.filtre);
        createSpinnerFiltre();

    }

    private void createSpinnerFiltre() {
        list.clear();
        list.add("Filtrer par:");
        list.add("Prix");
        list.add("Nom");
        list.add("Commerçant");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,list);
        spinner.setAdapter(adapter);
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


    public void connexion(View view) {
        intent = new Intent(ConnexClientActivity.this,MainActivity.class);
        startActivity(intent);
    }

   public void panier(View view) {
        panier = produitAdapter.getPanier();
        final String regex = "\\{[^}]*\\}";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        String panierJsonString = MyListPCliAdapter.getGsonParser().toJson(panier);

        final Matcher matcher = pattern.matcher(panierJsonString);
        ArrayList<String> produits = new ArrayList<>();
         while (matcher.find()) {
             produits.add(matcher.group(0));
       }

       Log.d("My App", String.valueOf(produits));
       bundlepanier.putString("type",bundle.getString("type"));
        bundlepanier.putStringArrayList("panier", produits);
        intentpanier.putExtras(bundlepanier);
        startActivity(intentpanier);
    }

    public void commandes(View view) {
        Intent intentcommande = new Intent(this, CommandeClientActivity.class);
        Bundle bundlecommande = new Bundle();
        bundlecommande.putString("type",bundle.getString("type"));
        intentcommande.putExtras(bundlecommande);
        startActivity(intentcommande);
    }


}
