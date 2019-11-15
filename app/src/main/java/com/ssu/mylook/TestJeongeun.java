package com.ssu.mylook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestJeongeun extends AppCompatActivity implements View.OnClickListener {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_jeongeun);

        btn1 = findViewById(R.id.jbtn1);
        btn2 = findViewById(R.id.jbtn2);
        btn3 = findViewById(R.id.jbtn3);
        btn4 = findViewById(R.id.jbtn4);
        btn5 = findViewById(R.id.jbtn5);
        btn6 = findViewById(R.id.jbtn6);
        btn7 = findViewById(R.id.jbtn7);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v==btn1){
            intent = new Intent(this,CoordiMainActivity.class);
            startActivity(intent);
        }
        else if(v==btn2){
            intent = new Intent(this,CoordiViewActivity.class);
            startActivity(intent);
        }
        else if(v==btn3){
            intent = new Intent(this,StyleAnalysisActivity.class);
            startActivity(intent);
        }
        else if(v==btn4){
            intent = new Intent(this,UnfavoriteClotheActivity.class);
            startActivity(intent);
        }
        else if(v==btn5){
            intent = new Intent(this,FavoriteTagActivity.class);
            startActivity(intent);
        }
        else if(v==btn6){
            intent = new Intent(this,FavoriteColorActivity.class);
            startActivity(intent);
        }
        else if(v==btn7){
            intent = new Intent(this,FavoriteClotheActivity.class);
            startActivity(intent);
        }

    }
}
