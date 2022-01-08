package com.example.applicatione_commerce;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Button oui, non;
    public TextView text;
    public String result;
    private Intent intent;


    public void setResult(String string){
        result = string;
    }

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.supprimer);
        initActivity();

    }

    private void initActivity() {
        text = (TextView) findViewById(R.id.voulez_vous);
        text.setText("null");
        oui = (Button) findViewById(R.id.oui);
        oui.setOnClickListener(this);

        non = (Button) findViewById(R.id.non);
        non.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.oui:
                Toast.makeText(v.getContext(),result, Toast.LENGTH_SHORT).show();
                break;
            case R.id.non:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

}

