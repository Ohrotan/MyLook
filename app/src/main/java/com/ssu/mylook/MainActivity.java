package com.ssu.mylook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn1;
    Button btn2;
    Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.park_btn);
        btn2 = findViewById(R.id.jo_btn);
        btn3 = findViewById(R.id.heo_btn);


    }

    @Override
    public void onClick(View v) {
        if(v== btn1){//박정은

        }else if(v== btn2){//조란
            Intent intent = new Intent(this,TestRanActivity.class);
            startActivity(intent);
        }else if(v== btn3){//허승민

        }
    }
}
