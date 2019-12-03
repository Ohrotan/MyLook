package com.ssu.mylook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ClotheEditActivity extends AppCompatActivity implements View.OnClickListener {

    Button save;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothe_register);

        save= findViewById(R.id.cancel_btn);
        back= findViewById(R.id.save_btn);
    }

    @Override
    public void onClick(View v) {
    Intent intent;

    if(v==save){
        //저장
        intent = new Intent(this, ClosetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();

    }
    else if(v==back){
        //이전액티비티
        onBackPressed();
    }
    }
}