package com.example.applicatione_commerce;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    private ArrayList<String> produits = new ArrayList<>();
    private Button btn_valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panier);
        initActivity();

    }

    private void initActivity(){
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
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(
                this,
                R.layout.list_panier,
                R.id.produit,
                produits);
        listView.setAdapter(arr);
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

    public void deletepanier(View view){
        TextView produit = (TextView) findViewById(R.id.produit);
        produits.removeIf(s -> s.contentEquals(produit.getText()));
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(
                this,
                R.layout.list_panier,
                R.id.produit,
                produits);
        listView.setAdapter(arr);
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
