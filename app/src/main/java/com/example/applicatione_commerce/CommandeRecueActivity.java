package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class CommandeRecueActivity extends Activity {

    private ListView listView;
    private Button btn_valider;
    String produit[]
            = {"Produit 1","Produit 2","Produit 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commandes_commercant);
        initActivity();

    }

    private void initActivity() {
        listView = (ListView)findViewById(R.id.listView);
        initListView();

        btn_valider = (Button)findViewById(R.id.btn_modif_commande);
        createOnClickValidationModifButton();
    }

    private void initListView() {

        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(
                this,
                R.layout.list_commande_commercant,
                R.id.produit,
                produit);
        listView.setAdapter(arr);
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
