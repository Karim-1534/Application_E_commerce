package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.example.applicatione_commerce.Service.MyListCCli;
import com.example.applicatione_commerce.Service.MyListCCliAdapter;

import java.util.ArrayList;

public class CommandeClientActivity extends Activity {
    private ListView listView;
    private Intent intent;
    private Bundle bundle;
    private Button btn_connexion;
    private ImageButton btn_commande;
    private ArrayList<String> commandes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commande_client);
        initActivity();

    }

    private void initActivity(){
        intent = getIntent();
        bundle = intent.getExtras();

        listView = (ListView)findViewById(R.id.listView);
        initListView();
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
