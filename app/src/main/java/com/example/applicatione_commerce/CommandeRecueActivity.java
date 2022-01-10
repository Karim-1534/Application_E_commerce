package com.example.applicatione_commerce;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.applicatione_commerce.Model.Commandes.Commande;
import com.example.applicatione_commerce.Service.CustomDialogClass;
import com.example.applicatione_commerce.Service.MyListCCli;
import com.example.applicatione_commerce.Service.MyListCCliAdapter;
import com.example.applicatione_commerce.Service.MyListCCom;
import com.example.applicatione_commerce.Service.MyListCComAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CommandeRecueActivity extends Activity {

    private ListView listView;
    private Button btn_valider;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final ArrayList<MyListCCom> arrayList = new ArrayList<MyListCCom>();


    String produit[]
            = {"Produit 1","Produit 2","Produit 3"};
    private MyListCComAdapter cComAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commandes_commercant);
        getData();

    }

    private void initActivity() {
        btn_valider = (Button)findViewById(R.id.btn_modif_commande);
        createOnClickValidationModifButton();
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
                                    arrayList.add(new MyListCCom( String.valueOf(c.hashCode()),c.getCLIENT().getPath(), c.getDATE()));
                                initActivity();
                            }
                        }
                        cComAdapter = new MyListCComAdapter(CommandeRecueActivity.this, arrayList);
                        listView.setAdapter(cComAdapter);
                    }

                });
    }


    public void confirmer(View v){
        Toast.makeText(CommandeRecueActivity.this, "Commande confirmé", Toast.LENGTH_SHORT).show();

    }

    public void annuler(View v){
        CustomDialogClass cdd = new CustomDialogClass(CommandeRecueActivity.this);
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.setResult("Commande annulé");
        cdd.show();
        cdd.text.setText("Voulez-vous vraiment annuler cette commande ?");

    }

    private void createOnClickValidationModifButton(){
        btn_valider.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = getResources().getString(R.string.valid_modif_commande);
                Intent intent = new Intent(CommandeRecueActivity.this, Confirm.class);
                Bundle bundle = new Bundle();
                bundle.putString("type",str);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
