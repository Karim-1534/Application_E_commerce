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
import android.widget.Spinner;


import com.example.applicatione_commerce.Service.CustomDialogClass;
import com.example.applicatione_commerce.Service.MyListPCliAdapter;
import com.example.applicatione_commerce.Service.MyListPComAdapter;
import com.example.applicatione_commerce.Service.MyListProduit;

import java.util.ArrayList;
import java.util.List;

public class ModifAnnonce extends Activity {
    private Button btn_valider ;
    private List<String> list = new ArrayList<String>();
    private Spinner spinner;
    private ListView listView ;
    String produit[]
            = {"Produit 1","Produit 2","Produit 3"};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_annonce);
        initActivity();
    }




    private void initActivity(){

        spinner = (Spinner) findViewById(R.id.filtre);
        initSpinnerDispo();

        btn_valider = (Button) findViewById(R.id.btn_modif_annonce);
        createOnClickValidationModifButton();

        listView = (ListView)findViewById(R.id.listView);
        initListView();

    }

    private void initListView() {

        final ArrayList<MyListProduit> arrayList = new ArrayList<MyListProduit>();
       /* arrayList.add(new MyListProduit(R.drawable.common_google_signin_btn_icon_dark, "basket","12€"));
        arrayList.add(new MyListProduit(R.drawable.confirmation, "T-shirt","100€"));
        arrayList.add(new MyListProduit(R.drawable.abc_vector_test, "bidule","15€"));
        arrayList.add(new MyListProduit(R.drawable.check, "bière","3.55€"));
        arrayList.add(new MyListProduit(R.drawable.common_full_open_on_phone, "chat","1099€"));
        arrayList.add(new MyListProduit(R.drawable.common_google_signin_btn_icon_light_normal_background, "Téléphone","10€"));
*/
        MyListPComAdapter produitAdapter = new MyListPComAdapter(this, arrayList);
        listView.setAdapter(produitAdapter);
    }


    /*
     *événement boutton ajout validation de la modification
     *
     * */

    private void createOnClickValidationModifButton(){
        btn_valider.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = getResources().getString(R.string.valid_modif_annonce);
                Intent intent = new Intent(ModifAnnonce.this, Confirm.class);
                Bundle bundle = new Bundle();
                bundle.putString("type",str);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onResume() {

        super.onResume();
        initActivity();
    }
    /*
     * initialisation du spinner disponibilité
     * */
    private void initSpinnerDispo(){
        list.clear();
        list.add("------");
        list.add("Disponible");
        list.add("Indisponible");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,list);
        spinner.setAdapter(adapter);

    }


    public void supprimer(View v){

        CustomDialogClass cdd = new CustomDialogClass(ModifAnnonce.this);
        cdd.setResult("Produit supprimé");
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.text.setText("Voulez-vous vraiment supprimer ce produit ?");


    }


}