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

import com.example.applicatione_commerce.Service.CustomDialogClass;
import com.example.applicatione_commerce.Service.MyListCCom;
import com.example.applicatione_commerce.Service.MyListCComAdapter;

import java.util.ArrayList;

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
        final ArrayList<MyListCCom> arrayList = new ArrayList<MyListCCom>();
        arrayList.add(new MyListCCom(R.drawable.common_google_signin_btn_icon_dark, "basket","robert", "27/01/1998"));
        arrayList.add(new MyListCCom(R.drawable.confirmation, "T-shirt","Apple", "14/09/2005"));
        arrayList.add(new MyListCCom(R.drawable.abc_vector_test, "bidule","Amazon", "14/11/1964"));
        arrayList.add(new MyListCCom(R.drawable.check, "bière","Boulanger", "24/01/1998"));
        arrayList.add(new MyListCCom(R.drawable.common_full_open_on_phone, "chat","Mavic", "02/07/1993"));
        arrayList.add(new MyListCCom(R.drawable.common_google_signin_btn_icon_light_normal_background, "Téléphone","Prime", "27/01/1998"));

        MyListCComAdapter cComAdapter = new MyListCComAdapter(this, arrayList);
        listView.setAdapter(cComAdapter);
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
