package com.ssu.mylook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
    Button btn_red;
    Button btn_orange;
    Button btn_yellow;
    Button btn_green;
    Button btn_blue;
    Button btn_darkblue;
    Button btn_violet;
    Button btn_pink;
    Button btn_white;
    Button btn_black;
    Button btn_pattern;

    private String imageFilePath;
    private Uri photoUri;

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
        btn_red= findViewById(R.id.btn_red);
        btn_orange=findViewById(R.id.btn_orange);
        btn_yellow=findViewById(R.id.btn_yellow);
        btn_green=findViewById(R.id.btn_green);
        btn_blue=findViewById(R.id.btn_blue);
        btn_darkblue=findViewById(R.id.btn_darkblue);
        btn_violet=findViewById(R.id.btn_violet);
        btn_pink=findViewById(R.id.btn_pink);
        btn_white=findViewById(R.id.btn_white);
        btn_black=findViewById(R.id.btn_black);
        btn_pattern=findViewById(R.id.btn_pattern);

        //add_photo.setOnClickListener(this);
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
        btn_red.setOnClickListener(this);
        btn_orange.setOnClickListener(this);
        btn_yellow.setOnClickListener(this);
        btn_green.setOnClickListener(this);
        btn_blue.setOnClickListener(this);
        btn_darkblue.setOnClickListener(this);
        btn_violet.setOnClickListener(this);
        btn_pink.setOnClickListener(this);
        btn_white.setOnClickListener(this);
        btn_black.setOnClickListener(this);
        btn_pattern.setOnClickListener(this);

        add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(ClotheRegisterActivity.this, Manifest.permission.CAMERA);
                if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(ClotheRegisterActivity.this, new String[]{Manifest.permission.CAMERA}, 0);
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        File photoFile = null;
                        try {
                            photoFile = createImageFile();
                        } catch (IOException ex) {
                            // Error occurred while creating the File
                        }
                        photoUri = FileProvider.getUriForFile(ClotheRegisterActivity.this, getPackageName(), photoFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(intent, 1);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;

         if(v==btn_top){
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

        else if(v==btn_red){
            if (btn_red.getCurrentTextColor() != Color.TRANSPARENT) {
                btn_red.setBackground(getResources().getDrawable(R.drawable.red_button1, null));
                btn_red.setTextColor(Color.TRANSPARENT);
            } else {
                btn_red.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_red.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_orange){
            if (btn_orange.getCurrentTextColor() != Color.TRANSPARENT) {
                btn_orange.setBackground(getResources().getDrawable(R.drawable.orange_button, null));
                btn_orange.setTextColor(Color.TRANSPARENT);
            } else {
                btn_orange.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_orange.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_yellow){
            if (btn_yellow.getCurrentTextColor() != Color.TRANSPARENT) {
                btn_yellow.setBackground(getResources().getDrawable(R.drawable.yellow_button, null));
                btn_yellow.setTextColor(Color.TRANSPARENT);
            } else {
                btn_yellow.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_yellow.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_green){
            if (btn_green.getCurrentTextColor() != Color.TRANSPARENT) {
                btn_green.setBackground(getResources().getDrawable(R.drawable.green_button, null));
                btn_green.setTextColor(Color.TRANSPARENT);
            } else {
                btn_green.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_green.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_blue){
            if (btn_blue.getCurrentTextColor() != Color.TRANSPARENT) {
                btn_blue.setBackground(getResources().getDrawable(R.drawable.blue_button, null));
                btn_blue.setTextColor(Color.TRANSPARENT);
            } else {
                btn_blue.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_blue.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_darkblue){
            if (btn_darkblue.getCurrentTextColor() != Color.TRANSPARENT) {
                btn_darkblue.setBackground(getResources().getDrawable(R.drawable.darkblue_button, null));
                btn_darkblue.setTextColor(Color.TRANSPARENT);
            } else {
                btn_darkblue.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_darkblue.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_violet){
            if (btn_violet.getCurrentTextColor() != Color.TRANSPARENT) {
                btn_violet.setBackground(getResources().getDrawable(R.drawable.violet_button, null));
                btn_violet.setTextColor(Color.TRANSPARENT);
            } else {
                btn_violet.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_violet.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_pink){
            if (btn_pink.getCurrentTextColor() != Color.TRANSPARENT) {
                btn_pink.setBackground(getResources().getDrawable(R.drawable.pink_button, null));
                btn_pink.setTextColor(Color.TRANSPARENT);
            } else {
                btn_pink.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_pink.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_white){
            if (btn_white.getCurrentTextColor() != Color.TRANSPARENT) {
                btn_white.setBackground(getResources().getDrawable(R.drawable.white_button, null));
                btn_white.setTextColor(Color.TRANSPARENT);
            } else {
                btn_white.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_white.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_black){
            if (btn_black.getCurrentTextColor() != Color.TRANSPARENT) {
                btn_black.setBackground(getResources().getDrawable(R.drawable.black_button, null));
                btn_black.setTextColor(Color.TRANSPARENT);
            } else {
                btn_black.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_black.setTextColor(Color.DKGRAY);
            }
        }
        else if(v==btn_pattern){
            if (btn_pattern.getCurrentTextColor() != Color.TRANSPARENT) {
                btn_pattern.setTextColor(Color.TRANSPARENT);
            } else {
                btn_pattern.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                btn_pattern.setTextColor(Color.DKGRAY);
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

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode,permissions,grantResults);
            if (requestCode == 0)
                if (grantResults[0] == 0) {
                    Toast.makeText(this, "카메라 권한이 승인됨", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "카메라 권한이 거절되었습니다. 카메라를 이용하려면 권한을 승낙하세요.", Toast.LENGTH_SHORT).show();
                }
        }

        //카메라로 촬영한 사진을 가져옴
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent intent) {
            super.onActivityResult(requestCode, resultCode, intent);
            if(requestCode==1 && resultCode== Activity.RESULT_OK)
            {
                //Bitmap bitmap = (Bitmap)intent.getExtras().get("data");
                Bitmap bitmap= BitmapFactory.decodeFile(imageFilePath);
                Toast.makeText(this,"저장경로:"+imageFilePath,Toast.LENGTH_SHORT).show();
                if(bitmap !=null)
                {
                    add_photo.setImageBitmap(bitmap);
                }
            }
        }



    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEST_" + timeStamp + "_";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,      /* prefix */
                ".jpg",         /* suffix */
                storageDir          /* directory */
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }



    }

