package com.ssu.mylook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CoordiInfoRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView coordi_img;
    EditText coordi_name_etv;

    Button[] season_btn =new Button[4];

    Button[] tag_btn = new Button[9];

    RatingBar rating;

    Button cancel_btn;
    Button save_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordi_info_register);

        coordi_img = findViewById(R.id.coordi_img);
        coordi_name_etv = findViewById(R.id.coordi_name_etv);

        season_btn[0] = findViewById(R.id.spr_btn);
        season_btn[1] = findViewById(R.id.sum_btn);
        season_btn[2] = findViewById(R.id.fal_btn);
        season_btn[3] = findViewById(R.id.win_btn);

        tag_btn[0] = findViewById(R.id.tag1_btn);
        tag_btn[1] = findViewById(R.id.tag2_btn);
        tag_btn[2] = findViewById(R.id.tag3_btn);
        tag_btn[3] = findViewById(R.id.tag4_btn);
        tag_btn[4] = findViewById(R.id.tag5_btn);
        tag_btn[5] = findViewById(R.id.tag6_btn);
        tag_btn[6] = findViewById(R.id.tag7_btn);
        tag_btn[7] = findViewById(R.id.tag8_btn);
        tag_btn[8] = findViewById(R.id.tag9_btn);

        rating = findViewById(R.id.ratingBar);

        cancel_btn = findViewById(R.id.canel_btn);
        save_btn = findViewById(R.id.save_btn);


    }
    public void clicked(Button btn) {
        btn.setBackground(getResources().getDrawable(R.drawable.purple_button, null));
        btn.setTextColor(Color.WHITE);
    }

    public void unclicked(Button btn) {
        btn.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
        btn.setTextColor(Color.DKGRAY);
    }

    public void clickSeason(View v) {
        if (((Button) v).getCurrentTextColor() == Color.WHITE) {
            unclicked((Button) v);
        } else {
            clicked((Button) v);
        }

    }

    public void clickTag(View v) {

        clicked((Button) v);

        for (int i = 0; i < 9; i++) {
            if (tag_btn[i] != v && tag_btn[i].getCurrentTextColor() == Color.WHITE) {
                unclicked(tag_btn[i]);
            }
        }

    }
    public boolean checkValue(){

        if(coordi_name_etv.getText().toString().trim().equals("")) {
            Toast.makeText(this,"코디 이름을 입력해주세요.",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }

    @Override
    public void onClick(View v) {
        if (v == save_btn) {
            if(!checkValue()) return;

            String str = "코디이름: "+ coordi_name_etv.getText()
                    +"/계절:";

            for(int i = 0; i<4; i++)
            {
                if(season_btn[i].getCurrentTextColor()==Color.WHITE) {
                    str += season_btn[i].getText();
                }
            }
            str += "/태그:";

            for(int i = 0; i<9; i++)
            {
                if(tag_btn[i].getCurrentTextColor()==Color.WHITE) {
                    str += tag_btn[i].getText();
                    break;
                }
            }

            str += "/평점:"+rating.getRating();

            Toast.makeText(this,str+"점 ",Toast.LENGTH_LONG).show();

            //데이터베이스에 저장

          /*
            Intent intent = new Intent(this,CoordiViewActivity.class);
            startActivity(intent);
          */

        } else if (v == cancel_btn) {
            onBackPressed();
        }
    }
}
