package com.ssu.mylook;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpperBarActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        TextView text1=(TextView)findViewById(R.id.upper_spring);
        TextView text2=(TextView)findViewById(R.id.upper_summer);
        TextView text3=(TextView)findViewById(R.id.upper_fall);
        TextView text4=(TextView)findViewById(R.id.upper_winter);
        TextView textA=(TextView)findViewById(R.id.upper_top);
        TextView textB=(TextView)findViewById(R.id.upper_bottom);
        TextView textC=(TextView)findViewById(R.id.upper_hat);
        TextView textD=(TextView)findViewById(R.id.upper_shoes);
        TextView textE=(TextView)findViewById(R.id.upper_etc);

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "봄", Toast.LENGTH_SHORT).show();
            }
        });


        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "여름", Toast.LENGTH_SHORT).show();
            }
        });
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "가을", Toast.LENGTH_SHORT).show();
            }
        });
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "겨울", Toast.LENGTH_SHORT).show();
            }
        });

        textA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "상의", Toast.LENGTH_SHORT).show();
            }
        });
        textB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "하의", Toast.LENGTH_SHORT).show();
            }
        });
        textC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "모자", Toast.LENGTH_SHORT).show();
            }
        });
        textD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "신발", Toast.LENGTH_SHORT).show();
            }
        });
        textE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "기타", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.upper_spring:
                Toast.makeText(getApplicationContext(), "봄 선택", Toast.LENGTH_LONG).show();
                break;
            case R.id.upper_summer:
                Toast.makeText(getApplicationContext(), "여름 선택", Toast.LENGTH_LONG).show();
                break;
            case R.id.upper_fall:
                Toast.makeText(getApplicationContext(), "가을 선택", Toast.LENGTH_LONG).show();
                break;
            case R.id.upper_winter:
                Toast.makeText(getApplicationContext(), "겨울 선택", Toast.LENGTH_LONG).show();
                break;
            case R.id.upper_top:
                Toast.makeText(getApplicationContext(), "상의 선택", Toast.LENGTH_LONG).show();
                break;
            case R.id.upper_bottom:
                Toast.makeText(getApplicationContext(), "하의 선택", Toast.LENGTH_LONG).show();
                break;
            case R.id.upper_hat:
                Toast.makeText(getApplicationContext(), "모자 선택", Toast.LENGTH_LONG).show();
                break;
            case R.id.upper_shoes:
                Toast.makeText(getApplicationContext(), "신발 선택", Toast.LENGTH_LONG).show();
                break;
            case R.id.upper_etc:
                Toast.makeText(getApplicationContext(), "기타 선택", Toast.LENGTH_LONG).show();
                break;

        }
    }
}
