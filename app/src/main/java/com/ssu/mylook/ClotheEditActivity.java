package com.ssu.mylook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ssu.mylook.dto.ClotheDTO;
import com.ssu.mylook.util.DBUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

public class ClotheEditActivity extends ClotheRegisterActivity implements View.OnClickListener {

    String clotheID;
    Button save;
    Button back;

    String setSort=result.getSORT();
    String setColor=result.getCOLOR();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("옷 정보 수정");
        save= findViewById(R.id.cancel_btn);
        back= findViewById(R.id.save_btn);

        clotheID = getIntent().getStringExtra("coltheID");
        if (clotheID == null) {
            clotheID = "bP9S6FQCAUSXRWsxD6Id";
        }
        clotheID = "bP9S6FQCAUSXRWsxD6Id";

        getData(clotheID);
    }

    @Override
    public void onClick(View v) {
    Intent intent;

    if(v==save){
        //저장

        String uniqueID = UUID.randomUUID().toString();
        new DBUtil().uploadImage(getBitmap, uniqueID);


        String str = "옷 이름: " + clothe_title.getText()
                + "/계절:";

        for (int i = 0; i < 4; i++) {
            if (season_btn[i].getCurrentTextColor() == Color.WHITE) {
                str += season_btn[i].getText();
            }
        }
        str += "/구분:";

        for (int i = 0; i < 5; i++) {
            if (sort_btn[i].getCurrentTextColor() == Color.WHITE) {
                str += sort_btn[i].getText();
                break;
            }
        }

        str += "/색깔:";

        for (int i = 0; i < 11; i++) {
            if (color_btn[i].getCurrentTextColor() == Color.TRANSPARENT) {
                str += color_btn[i].getText();
                break;
            }
        }

        Toast.makeText(this, str + "색 ", Toast.LENGTH_LONG).show();

        //데이터베이스에 저장
        //result.setIMAGE(uniqueID);
        result.setTTL(clothe_title.getText().toString());
        result.setMEMO(memo.getText().toString());
        for (int i = 0; i < 11; i++) {
            if (color_btn[i].getCurrentTextColor() == Color.TRANSPARENT) {
                result.setCOLOR(color_btn[i].getText().toString());
                break;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (sort_btn[i].getCurrentTextColor() == Color.WHITE) {
                result.setSORT(sort_btn[i].getText().toString());
                break;
            }
        }

        ArrayList<String> seletedSeasons = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (season_btn[i].getCurrentTextColor() == Color.WHITE) {
                seletedSeasons.add(season_btn[i].getText().toString());
            }
        }

        result.setSEASON(seletedSeasons);
        Calendar c = new GregorianCalendar();
        c.add(Calendar.HOUR_OF_DAY, 9);
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH) + 1;
        int d = c.get(Calendar.DAY_OF_MONTH);
        int h = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);

        new DBUtil().addClothe(result);

        //저장하고
        intent = new Intent(this,ClosetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    else if(v==back){
        //이전액티비티
        onBackPressed();
    }

    intent = new Intent(this, ClosetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    startActivity(intent);
    overridePendingTransition(0, 0);
    finish();

}

    public void getData(String id) {
        final String TAG = "clothe database";
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("clothes").document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                result = document.toObject(ClotheDTO.class);
                                Log.v(TAG, "DocumentSnapshot data: " + result.toString());
                                setField(result);
                            } else {
                                Log.v(TAG, "No such document");
                            }
                        } else {
                            Log.v(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }

    public void setField(ClotheDTO result) {

        //image
        new DBUtil().setImageViewFromDB(this,add_photo,result.getIMAGE());

        //title
        clothe_title.setText(result.getTTL());

        //memo
        memo.setText(result.getMEMO());

        //season
        for (String s : result.getSEASON()) {
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
        //sort
            switch (setSort) {
                case "상의":
                    sort_btn[0].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    sort_btn[0].setTextColor(Color.WHITE);
                    break;
                case "하의":
                    sort_btn[1].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    sort_btn[1].setTextColor(Color.WHITE);
                    break;
                case "모자":
                    sort_btn[2].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    sort_btn[2].setTextColor(Color.WHITE);
                    break;
                case "신발":
                    sort_btn[3].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    sort_btn[3].setTextColor(Color.WHITE);
                    break;
                case "기타":
                    sort_btn[4].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                    sort_btn[4].setTextColor(Color.WHITE);
                    break;
                default:
                    break;
            }
        //color
        switch (setColor) {
            case "빨강" :
                color_btn[0].setBackground(getResources().getDrawable(R.drawable.red_button1, null));
                color_btn[0].setTextColor(Color.TRANSPARENT);
                break;
            case "주황":
                color_btn[1].setBackground(getResources().getDrawable(R.drawable.orange_button, null));
                color_btn[1].setTextColor(Color.TRANSPARENT);
                break;
            case "노랑":
                color_btn[2].setBackground(getResources().getDrawable(R.drawable.yellow_button, null));
                color_btn[2].setTextColor(Color.TRANSPARENT);
                break;
            case "초록":
                color_btn[3].setBackground(getResources().getDrawable(R.drawable.green_button, null));
                color_btn[3].setTextColor(Color.TRANSPARENT);
                break;
            case "파랑":
                color_btn[4].setBackground(getResources().getDrawable(R.drawable.blue_button, null));
                color_btn[4].setTextColor(Color.TRANSPARENT);
                break;
            case "남색":
                color_btn[5].setBackground(getResources().getDrawable(R.drawable.darkblue_button, null));
                color_btn[5].setTextColor(Color.TRANSPARENT);
                break;
            case "보라":
                color_btn[6].setBackground(getResources().getDrawable(R.drawable.violet_button, null));
                color_btn[6].setTextColor(Color.TRANSPARENT);
                break;
            case "분홍":
                color_btn[7].setBackground(getResources().getDrawable(R.drawable.pink_button, null));
                color_btn[7].setTextColor(Color.TRANSPARENT);
                break;
            case "흰색":
                color_btn[8].setBackground(getResources().getDrawable(R.drawable.white_button, null));
                color_btn[8].setTextColor(Color.TRANSPARENT);
                break;
            case "검정":
                color_btn[9].setBackground(getResources().getDrawable(R.drawable.black_button, null));
                color_btn[9].setTextColor(Color.TRANSPARENT);
                break;
            case "회색":
                color_btn[10].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                color_btn[10].setTextColor(Color.DKGRAY);
                break;
            default:
                break;
        }

        }

    }

