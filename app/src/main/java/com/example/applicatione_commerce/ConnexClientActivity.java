package com.example.applicatione_commerce;

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

public class ConnexClientActivity extends Activity {
    String produits[]
            = {"Tee-shirt","Chemise","Pantalon","AK-47"};
    private ArrayList<String> panier = new ArrayList<>();
    private ListView listView;
    private Intent intent;
    private Bundle bundle;
    private Button btn_connexion;
    private ImageButton btn_commande;
    private NumberPicker np;
    private Spinner spinner;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilclient);
        initActivity();

    }

    private void initActivity(){

        listView = (ListView)findViewById(R.id.listView);
        initListView();

        intent = getIntent();
        bundle = intent.getExtras();
        btn_connexion = (Button) findViewById(R.id.btn_connexion);
        btn_commande = (ImageButton) findViewById(R.id.btn_commandes);
        setBtn();

        /*np = (NumberPicker) findViewById(R.id.quantite);
        configQuantite();*/

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

    private void configQuantite() {
        np.setMinValue(0);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(true);
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
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(
                this,
                R.layout.list_produit_client,
                R.id.produit,
                produits);
        listView.setAdapter(arr);
    }

    public void connexion(View view) {
        intent = new Intent(ConnexClientActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void panier(View view) {
        Intent intentpanier = new Intent(this, PanierActivity.class);
        Bundle bundlepanier = new Bundle();
        bundlepanier.putString("type",bundle.getString("type"));
        bundlepanier.putStringArrayList("panier",panier);
        intentpanier.putExtras(bundlepanier);
        startActivity(intentpanier);
    }

    public void addpanier(View view) {
        TextView produit = (TextView) findViewById(R.id.produit);
        panier.add((String) produit.getText());
        Toast.makeText(ConnexClientActivity.this, produit.getText()+" ajouté au panier", Toast.LENGTH_SHORT).show();
    }

    public void commandes(View view) {
        Intent intentcommande = new Intent(this, CommandeClientActivity.class);
        Bundle bundlecommande = new Bundle();
        bundlecommande.putString("type",bundle.getString("type"));
        intentcommande.putExtras(bundlecommande);
        startActivity(intentcommande);
    }
}
