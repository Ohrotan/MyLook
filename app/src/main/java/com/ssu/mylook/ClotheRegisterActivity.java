package com.ssu.mylook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClotheRegisterActivity extends AppCompatActivity implements View.OnClickListener{


    ImageView add_photo;
    Button btn_top;
    Button btn_bottom;
    Button btn_hat;
    Button btn_shoes;
    Button btn_etc;
    Button btn_spring;
    Button btn_summer;
    Button btn_fall;
    Button btn_winter;
    Button btn_back;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothe_register);


        add_photo= findViewById(R.id.add_photo);
        btn_top= findViewById(R.id.button_top_clothe);
        btn_bottom= findViewById(R.id.button_bottom_clothe);
        btn_hat= findViewById(R.id.button_hat);
        btn_shoes= findViewById(R.id.button_shoes);
        btn_etc= findViewById(R.id.button_etc);
        btn_spring= findViewById(R.id.spr_btn);
        btn_summer= findViewById(R.id.sum_btn);
        btn_fall= findViewById(R.id.fal_btn);
        btn_winter= findViewById(R.id.win_btn);
        btn_back= findViewById(R.id.cancel_btn);
        btn_save= findViewById(R.id.save_btn);

        add_photo.setOnClickListener(this);
        btn_top.setOnClickListener(this);
        btn_bottom.setOnClickListener(this);
        btn_hat.setOnClickListener(this);
        btn_shoes.setOnClickListener(this);
        btn_etc.setOnClickListener(this);
        btn_spring.setOnClickListener(this);
        btn_summer.setOnClickListener(this);
        btn_fall.setOnClickListener(this);
        btn_winter.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v == add_photo){
            intent = new Intent(this,CameraActivity.class);
            startActivityForResult(intent,672);
        }
        else if(v==btn_top){
            //색깔 바뀌도록
            if (btn_top.getCurrentTextColor() != Color.WHITE) {
                btn_top.setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                btn_top.setTextColor(Color.WHITE);
            } else {
                btn_top.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_top.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_bottom){
            if (btn_bottom.getCurrentTextColor() != Color.WHITE) {
                btn_bottom.setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                btn_bottom.setTextColor(Color.WHITE);
            } else {
                btn_bottom.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_bottom.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_hat){
            if (btn_hat.getCurrentTextColor() != Color.WHITE) {
                btn_hat.setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                btn_hat.setTextColor(Color.WHITE);
            } else {
                btn_hat.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_hat.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_shoes){
            if (btn_shoes.getCurrentTextColor() != Color.WHITE) {
                btn_shoes.setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                btn_shoes.setTextColor(Color.WHITE);
            } else {
                btn_shoes.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_shoes.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_etc){
            if (btn_etc.getCurrentTextColor() != Color.WHITE) {
                btn_etc.setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                btn_etc.setTextColor(Color.WHITE);
            } else {
                btn_etc.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_etc.setTextColor(Color.DKGRAY);
            }
        }

        else if(v==btn_spring){
            if (btn_spring.getCurrentTextColor() != Color.WHITE) {
                btn_spring.setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                btn_spring.setTextColor(Color.WHITE);
            } else {
                btn_spring.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_spring.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_summer){
            if (btn_summer.getCurrentTextColor() != Color.WHITE) {
                btn_summer.setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                btn_summer.setTextColor(Color.WHITE);
            } else {
                btn_summer.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_summer.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_fall){
            if (btn_fall.getCurrentTextColor() != Color.WHITE) {
                btn_fall.setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                btn_fall.setTextColor(Color.WHITE);
            } else {
                btn_fall.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_fall.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_winter){
            if (btn_winter.getCurrentTextColor() != Color.WHITE) {
                btn_winter.setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                btn_winter.setTextColor(Color.WHITE);
            } else {
                btn_winter.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_winter.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_back){
            //저장하지 않고
            intent = new Intent(this,ClosetActivity.class);
            startActivity(intent);
        }
        else if(v==btn_save){
            //저장하고
            intent = new Intent(this,ClosetActivity.class);
            startActivity(intent);
        }

    }

    }

