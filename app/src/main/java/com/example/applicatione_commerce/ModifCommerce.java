package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModifCommerce extends Activity {
    private Button btn_modif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_commercant);
        initActivity();

    }

    private void initActivity() {

        btn_modif = (Button) findViewById(R.id.btn_modif);
        createOnClickModif();
    }

    private void createOnClickModif() {
        btn_modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = getResources().getString(R.string.valid_modif_commerce);
                Intent intent = new Intent(ModifCommerce.this, Confirm.class);
                Bundle bundle = new Bundle();
                bundle.putString("type",str);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
