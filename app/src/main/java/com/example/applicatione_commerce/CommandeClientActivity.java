package com.example.applicatione_commerce;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

import com.example.applicatione_commerce.Model.Produit;
import com.example.applicatione_commerce.Model.Utilisateurs.Commercant;
import com.example.applicatione_commerce.Service.MyListCCli;
import com.example.applicatione_commerce.Service.MyListCCliAdapter;
import com.example.applicatione_commerce.Service.MyListPCliAdapter;
import com.example.applicatione_commerce.Service.MyListProduit;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeClientActivity extends Activity {
    private ListView listView;
    private Intent intent;
    private Bundle bundle;
    private Button btn_connexion;
    private ImageButton btn_commande;
    private ArrayList<String> commandes = new ArrayList<>();
    final ArrayList<MyListProduit> arrayList = new ArrayList<MyListProduit>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commande_client);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getData();

    }

    private void getData() {
        db.collection("COMMANDES")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        listView = (ListView)findViewById(R.id.listView);
                        if (queryDocumentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                        } else {
                            List<Commandes> commercant = queryDocumentSnapshots.toObjects(Commercant.class);
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
                                }
                            }
                        }
                        produitAdapter = new MyListPCliAdapter(ConnexClientActivity.this, arrayList);
                        listView.setAdapter(produitAdapter);
                    }

                });
    }

    private void initActivity(){
        intent = getIntent();
        bundle = intent.getExtras();

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
        final ArrayList<MyListCCli> arrayList = new ArrayList<MyListCCli>();
        arrayList.add(new MyListCCli(R.drawable.fui_idp_button_background_apple, "654fdgfs","En livraison"));
        arrayList.add(new MyListCCli(R.drawable.googleg_standard_color_18, "g876r7g6","En préparation"));
        arrayList.add(new MyListCCli(R.drawable.rectangle_6, "sf86sf684s","Livré"));

        MyListCCliAdapter cCliAdapter = new MyListCCliAdapter(this, arrayList);
        listView.setAdapter(cCliAdapter);
    }



    public void home(View view) {
        Intent intenthome = new Intent(this, ConnexClientActivity.class);
        Bundle bundlehome = new Bundle();
        bundlehome.putString("type",bundle.getString("type"));
        intenthome.putExtras(bundlehome);
        startActivity(intenthome);
    }


    public void panier(View view) {
        Intent intentpanier = new Intent(this, PanierActivity.class);
        startActivity(intentpanier);
    }


    public void service(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:example.cute-shop.com"));
        startActivity(intent);
    }
}
