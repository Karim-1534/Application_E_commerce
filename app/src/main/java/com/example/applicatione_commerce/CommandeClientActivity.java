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

import com.example.applicatione_commerce.Model.Commandes.Commande;
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
    final ArrayList<MyListCCli> arrayList = new ArrayList<MyListCCli>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private MyListCCliAdapter cCliAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commande_client);
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
                            List<Commande> commandes = queryDocumentSnapshots.toObjects(Commande.class);

                            for(Commande c: commandes) {
                                if(c.getCOMMERCANT()!=null && c.getSTATUT()!=null) {
                                    arrayList.add(new MyListCCli(String.valueOf(c.hashCode()), c.getSTATUT()));
                                }
                                initActivity();
                            }
                        }
                        cCliAdapter = new MyListCCliAdapter(CommandeClientActivity.this, arrayList);
                        listView.setAdapter(cCliAdapter);
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
