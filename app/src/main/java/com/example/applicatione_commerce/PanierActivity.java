package com.example.applicatione_commerce;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.applicatione_commerce.Service.MyListProduit;
import com.example.applicatione_commerce.Service.MyListPanierAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

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
        Log.d("produits", String.valueOf(arrayList));
        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = getResources().getString(R.string.valid_panier);
                Intent intent = new Intent(PanierActivity.this, Confirm.class);
                Bundle bundle = new Bundle();
                bundle.putString("type",str);
                intent.putExtras(bundle);
                startActivity(intent);
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
