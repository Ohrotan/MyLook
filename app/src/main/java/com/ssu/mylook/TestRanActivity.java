package com.ssu.mylook;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TestRanActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;

    //옷 추가 레이아웃
    AlertDialog dialog;
    GridView listView;
    ClotheListAdapter myListAdapter;
    ArrayList<ClotheListItem> list_itemArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ran);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);

        //옷 추가 레이아웃
        list_itemArrayList = new ArrayList<ClotheListItem>();
        listView = (GridView)findViewById(R.id.coordi_clothe_result_view);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v == btn1){ //코디할 옷 추가 레이아웃
           final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            final LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View clotheAddPopup = inflater.inflate(R.layout.layout_clothe_add_search,null);
            builder.setView(clotheAddPopup);
            builder.setPositiveButton("다음",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialo, int which) {
                    View clotheAddPopup = inflater.inflate(R.layout.layout_clothe_add,null);
                    builder.setView(clotheAddPopup);
                    builder.setPositiveButton("다음",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setNegativeButton("취소",null);


                    dialog = builder.create();
                    dialog.show();

                }
            });

            builder.setNegativeButton("취소",null);


            dialog = builder.create();
            dialog.show();
            return;
        }else if(v == btn2){
             intent = new Intent(this,CoordiRegisterActivity.class);
            startActivity(intent);

        }else if(v == btn3){
             intent = new Intent(this,CoordiInfoRegisterActivity.class);
            startActivity(intent);

        }else if(v == btn4){
            intent = new Intent(this,CoordiEditActivity.class);
            startActivity(intent);
        }else if(v == btn5){
            intent = new Intent(this,CoordiInfoEditActivity.class);
            startActivity(intent);
        }else if(v == btn6){
            //디비 테스트

        }

    }
}
