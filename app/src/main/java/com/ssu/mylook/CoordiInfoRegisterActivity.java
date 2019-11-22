package com.ssu.mylook;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CoordiInfoRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView coordi_img;
    EditText coordi_name_etv;

    Button spr_btn;
    Button sum_btn;
    Button fal_btn;
    Button win_btn;

    Button tag1_btn;
    Button tag2_btn;
    Button tag3_btn;
    Button tag4_btn;
    Button tag5_btn;
    Button tag6_btn;
    Button tag7_btn;
    Button tag8_btn;
    Button tag9_btn;

    Button cancel_btn;
    Button save_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordi_info_register);

        coordi_img = findViewById(R.id.coordi_img);
        coordi_name_etv = findViewById(R.id.coordi_name_etv);

        spr_btn = findViewById(R.id.spr_btn);
        sum_btn = findViewById(R.id.sum_btn);
        fal_btn = findViewById(R.id.fal_btn);
        win_btn = findViewById(R.id.win_btn);

        tag1_btn = findViewById(R.id.tag1_btn);
        tag2_btn = findViewById(R.id.tag2_btn);
        tag3_btn = findViewById(R.id.tag3_btn);
        tag4_btn = findViewById(R.id.tag4_btn);
        tag5_btn = findViewById(R.id.tag5_btn);
        tag6_btn = findViewById(R.id.tag6_btn);
        tag7_btn = findViewById(R.id.tag7_btn);
        tag8_btn = findViewById(R.id.tag8_btn);
        tag9_btn = findViewById(R.id.tag9_btn);

        cancel_btn = findViewById(R.id.canel_btn);
        save_btn = findViewById(R.id.save_btn);


    }

    public void clickSeason(View v) {
        Button btn = null;
        String btn_name = ((Button) v).getText().toString();
        switch (btn_name) {
            case "봄":
                btn = spr_btn;
                break;
            case "여름":
                btn = sum_btn;
                break;
            case "가을":
                btn = fal_btn;
                break;
            case "겨울":
                btn = win_btn;
                break;

        }
        if (btn == null) return;
        if (btn.getCurrentTextColor() == Color.WHITE) {
            btn.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
            btn.setTextColor(Color.DKGRAY);
        } else {
            btn.setBackground(getResources().getDrawable(R.drawable.purple_button, null));
            btn.setTextColor(Color.WHITE);
        }

    }

    public void clickTag(View v) {
        Button btn = null;
        String btn_name = ((Button) v).getText().toString();
        switch (btn_name) {
            case "심플베이직":
                btn = spr_btn;
                break;
            case "여름":
                btn = sum_btn;
                break;
            case "가을":
                btn = fal_btn;
                break;
            case "겨울":
                btn = win_btn;
                break;

        }
        if (btn == null) return;
        if (btn.getCurrentTextColor() == Color.WHITE) {
            btn.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
            btn.setTextColor(Color.DKGRAY);
        } else {
            btn.setBackground(getResources().getDrawable(R.drawable.purple_button, null));
            btn.setTextColor(Color.WHITE);
        }

    }

    @Override
    public void onClick(View v) {
        if (v == save_btn) {


        } else if (v == cancel_btn) {

        }
    }
}
