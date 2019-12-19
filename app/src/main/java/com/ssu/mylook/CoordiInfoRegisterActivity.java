package com.ssu.mylook;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.ssu.mylook.dto.CoordiDTO;
import com.ssu.mylook.util.DBUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.appcompat.app.AppCompatActivity;

public class CoordiInfoRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView coordi_img;
    EditText coordi_name_etv;

    Button[] season_btn = new Button[4];

    Button[] tag_btn = new Button[9];

    RatingBar rating;

    Button cancel_btn;
    Button save_btn;

    CoordiDTO result = new CoordiDTO();

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

        final String imgId = getIntent().getStringExtra("imgId");

        if (imgId != null) {
            result.setImg(imgId);
        }
        result.setUsed(getIntent().getStringArrayListExtra("clothesIds"));


    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            //Thread.sleep(1000);
        } catch (Exception e) {

        }

        String imgId = getIntent().getStringExtra("imgId");
        byte[] BYTE = (byte[]) getIntent().getByteArrayExtra("img");
        if (BYTE != null) {
            // BitmapFactory.decodeByteArray(BYTE,0,BYTE.length)
            coordi_img.setImageBitmap(BitmapFactory.decodeByteArray(BYTE, 0, BYTE.length));
            // new DBUtil().setImageViewFromDB(CoordiInfoRegisterActivity.this, coordi_img, imgId);
        } else {
            new DBUtil().setImageViewFromDB(this, coordi_img, imgId);
        }

        if (getIntent().getStringExtra("mode") != null) {
            result = getIntent().getParcelableExtra("coordiDTO");

            //name
            coordi_name_etv.setText(result.getName());

            //rating
            rating.setRating(result.getRating());

            //season
            for (String s : result.getSeasons()) {
                switch (s) {
                    case "봄":
                        season_btn[0].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                        season_btn[0].setTextColor(Color.WHITE);
                        break;
                    case "여름":
                        season_btn[1].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                        season_btn[1].setTextColor(Color.WHITE);
                        break;
                    case "가을":
                        season_btn[2].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                        season_btn[2].setTextColor(Color.WHITE);
                        break;
                    case "겨울":
                        season_btn[3].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                        season_btn[3].setTextColor(Color.WHITE);
                        break;
                    default:
                        break;
                }
            }


            //tag
            switch (result.getTag()) {
                case "심플베이직":
                    tag_btn[0].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    tag_btn[0].setTextColor(Color.WHITE);
                    break;
                case "캠퍼스룩":
                    tag_btn[1].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    tag_btn[1].setTextColor(Color.WHITE);
                    break;
                case "캐주얼":
                    tag_btn[2].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    tag_btn[2].setTextColor(Color.WHITE);
                    break;
                case "유니크":
                    tag_btn[3].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    tag_btn[3].setTextColor(Color.WHITE);
                    break;
                case "스포티":
                    tag_btn[4].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    tag_btn[4].setTextColor(Color.WHITE);
                    break;
                case "러블리":
                    tag_btn[5].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    tag_btn[5].setTextColor(Color.WHITE);
                    break;
                case "오피스룩":
                    tag_btn[6].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    tag_btn[6].setTextColor(Color.WHITE);
                    break;
                case "섹시글램":
                    tag_btn[7].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    tag_btn[7].setTextColor(Color.WHITE);
                    break;
                case "화려한":
                    tag_btn[8].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    tag_btn[8].setTextColor(Color.WHITE);
                    break;
                default:
                    break;
            }

        }
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

    public boolean checkValue() {

        if (coordi_name_etv.getText().toString().trim().equals("")) {
            Toast.makeText(this, "코디 이름을 입력해주세요.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }

    @Override
    public void onClick(View v) {
        if (v == save_btn) {
            if (!checkValue()) return;

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
            result.setCount(0);
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
            Calendar c = new GregorianCalendar();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH) + 1;
            int d = c.get(Calendar.DAY_OF_MONTH);
            int h = c.get(Calendar.HOUR_OF_DAY);
            int min = c.get(Calendar.MINUTE);

            String date = y + "-";
            if (m < 10) {
                date = date + "0" + m + "-";
            } else {
                date = date + m + "-";
            }
            if (d < 10) {
                date = date + "0" + d + " ";
            } else {
                date = date + d + " ";
            }
            if (h < 10) {
                date = date + "0" + h + ":";
            } else {
                date = date + h + ":";
            }
            if (min < 10) {
                date = date + "0" + min;
            } else {
                date = date + min;
            }

            result.setRegDate(date);
            result.setUserId("admin");//나중에 shared preference 이용하기
            new DBUtil().addCoordi(result);
            for (String cid : result.getUsed()) {
                new DBUtil().updateClotheCount(cid);
            }

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
