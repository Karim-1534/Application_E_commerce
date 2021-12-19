package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Confirm extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        TextView txt = (TextView) findViewById(R.id.le_commer_a);
        txt.setText(bundle.getString("type"));
        txt.setTextAppearance(R.style.confirm_commerce);
        Button btn_ok = (Button)findViewById(R.id.ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Confirm.this, ConnexGestActivity.class);
                startActivity(intent1);
            }
        });

    }
}

