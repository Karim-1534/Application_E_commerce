package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.applicatione_commerce.Service.CustomDialogClass;
import com.example.applicatione_commerce.Service.MyListCommercant;
import com.example.applicatione_commerce.Service.MyListCommercantAdapter;

import java.util.ArrayList;


public class ConnexGestActivity extends Activity {
    private ListView listView;
    String commerce[]
            = {"Commerçant 1","Commerçant 2","Commerçant 3","Commerçant 4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceuilgestionnaire);
        initActivity();

    }

    private void initActivity(){

        listView = (ListView)findViewById(R.id.listView);
        initListView();
    }

    private void initListView() {
        final ArrayList<MyListCommercant> arrayList = new ArrayList<MyListCommercant>();
        arrayList.add(new MyListCommercant("Apple","Multimedia,Informatique"));
        arrayList.add(new MyListCommercant("Carrefour","Alimentaire"));
        arrayList.add(new MyListCommercant("Boulanger","Multimedia,Electromenager"));
        arrayList.add(new MyListCommercant("Zara","Mode"));

        MyListCommercantAdapter commercantAdapter = new MyListCommercantAdapter(this, arrayList);
        listView.setAdapter(commercantAdapter);
    }


    public void ajout(View v){
        Intent intent = new Intent(this, AjoutCommerce.class);
        startActivity(intent);
    }

    public void modifier(View v){
        Intent intent = new Intent(this, ModifCommerce.class);
        startActivity(intent);
    }

    public void supprimer(View v){

        CustomDialogClass cdd = new CustomDialogClass(ConnexGestActivity.this);
        cdd.setResult("Commerçant supprimé");
        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cdd.show();
        cdd.text.setText("Voulez-vous vraiment supprimer ce commerçant ?");


    }


}
