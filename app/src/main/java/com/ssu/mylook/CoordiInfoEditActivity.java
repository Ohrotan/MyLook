package com.ssu.mylook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ssu.mylook.util.DBUtil;

import java.util.ArrayList;

public class CoordiInfoEditActivity extends CoordiInfoRegisterActivity implements View.OnClickListener {

    String coordiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //데이터베이스에서 정보가져와서 기본값 세팅하기
        coordiId = getIntent().getStringExtra("coordiId");
        if (coordiId == null) {
            coordiId = "xUVeQ0SBKJVipGqaCYtt";
        }
        coordiId = "xUVeQ0SBKJVipGqaCYtt";
        DBUtil db = new DBUtil();

        db.getData("coordi", coordiId);
        SystemClock.sleep(500);
        result = db.getCoordiDTO();
        if (result == null) {
            Log.v("dto", "null");
        } else {
            Log.v("dto", result.toString());
        }
        Log.v("dto", "jhj");
/*
        coordi_name_etv.setText(result.getName());
        for (String s : result.getSeasons()) {
            switch (s) {
                case "봄":
                    clicked(season_btn[0]);
                    break;
                case "여름":
                    clicked(season_btn[1]);
                    break;
                case "가을":
                    clicked(season_btn[2]);
                    break;
                case "겨울":
                    clicked(season_btn[3]);
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (tag_btn[i].getText().toString().equals(result.getTag())) {
                clicked(tag_btn[i]);
                break;
            }
        }
        rating.setRating(result.getRating());

        new DBUtil().setImageViewFromDB(this, coordi_img, result.getImg());

*/
    }

    @Override
    public void onClick(View v) {
        if (v == save_btn) {
            String str = "코디이름: " + coordi_name_etv.getText()
                    + "/계절:";

            for (int i = 0; i < 4; i++) {
                if (season_btn[i].getCurrentTextColor() == Color.WHITE) {
                    str += season_btn[i].getText();
                }
            }
            str += "/태그:";

            for (int i = 0; i < 9; i++) {
                if (tag_btn[i].getCurrentTextColor() == Color.WHITE) {
                    str += tag_btn[i].getText();
                    break;
                }
            }

            str += "/평점:" + rating.getRating();

            Toast.makeText(this, str + "점 ", Toast.LENGTH_LONG).show();
            //데이터베이스에 저장


            //데이터베이스에 저장
            result.setName(coordi_name_etv.getText().toString());
            result.setRating(rating.getRating());
            for (int i = 0; i < 9; i++) {
                if (tag_btn[i].getCurrentTextColor() == Color.WHITE) {
                    result.setTag(tag_btn[i].getText().toString());
                    break;
                }
            }
            ArrayList<String> seletedSeasons = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                if (season_btn[i].getCurrentTextColor() == Color.WHITE) {
                    seletedSeasons.add(season_btn[i].getText().toString());
                }
            }
            result.setSeasons(seletedSeasons);

            new DBUtil().updateCoordi(coordiId, result);

            Intent intent = new Intent(this, CoordiMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();

        } else if (v == cancel_btn) {
            onBackPressed();
        }
    }
}
