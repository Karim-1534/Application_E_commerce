package com.example.applicatione_commerce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Confirm extends Activity {
    private TextView txt;
    private Button btn_ok;
    private Intent intent;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
        initActivity();


    }

    private void initActivity(){

        txt = (TextView) findViewById(R.id.message_val);
        intent = getIntent();
        bundle = intent.getExtras();
        setMsgValidation();
        
        btn_ok = (Button)findViewById(R.id.ok);
        createOnClickValidation();

    }

    private void createOnClickValidation() {
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Confirm.this.finish();
            }
        });
    }

    private void setMsgValidation() {
        txt.setText(bundle.getString("type"));
        txt.setTextAppearance(R.style.confirm_commerce);
    }
}

