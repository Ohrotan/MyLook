package com.ssu.mylook;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CoordiInfoEditActivity extends CoordiInfoRegisterActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //데이터베이스에서 정보가져와서 기본값 세팅하기
        coordi_name_etv.setText("예쁜 원피스");
        clicked(season_btn[0]);
        clicked(season_btn[1]);
        clicked(tag_btn[3]);
        rating.setRating((float)3.5);
    }

    @Override
    public void onClick(View v) {
        if (v == save_btn) {
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
