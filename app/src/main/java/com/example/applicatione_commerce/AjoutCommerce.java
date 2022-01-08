package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AjoutCommerce extends Activity {

    private Button btn_valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_commercant);
        initActivity();

    }

    private void initActivity(){

        btn_valider = (Button) findViewById(R.id.btn_ajout_commerce);
        createOnClickValidationAjoutButton();

        //@BindView(R.id.nom_commer_)TextView textView;
    }

    private void createOnClickValidationAjoutButton(){
        btn_valider.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = getResources().getString(R.string.valid_ajout_commerce);
                Intent intent = new Intent(AjoutCommerce.this, Confirm.class);
                Bundle bundle = new Bundle();
                bundle.putString("type",str);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
