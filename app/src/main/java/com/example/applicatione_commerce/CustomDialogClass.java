package com.example.applicatione_commerce;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Button oui, non;


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

        oui = (Button) findViewById(R.id.oui);
        oui.setOnClickListener(this);

        non = (Button) findViewById(R.id.non);
        non.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.oui:
                Toast.makeText(v.getContext(), "Supprimé", Toast.LENGTH_SHORT).show();
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

