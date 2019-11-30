package com.ssu.mylook;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CoordiViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordi_view);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("코디 상세");

//        MyPlusMinusView plusMinusView = findViewById(R.id.plus_minus_View);
//        plusMinusView.setOnMyChangeListener((OnMyChangeListener) this);
    }
}
