package com.ssu.mylook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestSeungminActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_seungmin);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v == btn1){
          /*  intent = new Intent(this,ClosetActivity.class);
            startActivity(intent);*/
        }
        else if(v==btn2){
            intent=new Intent(this,ClotheViewActivity.class);
            startActivity(intent);
        }
        else if(v==btn3){
            intent = new Intent(this,ClotheRegister.class);
            startActivity(intent);}
        else if(v==btn4){}
        else if(v==btn5){}
        else if(v==btn6){
            intent = new Intent(this,UpperBarActivity.class);
            startActivity(intent);
        }
}}
