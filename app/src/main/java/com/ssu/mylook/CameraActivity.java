package com.ssu.mylook;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity  {

    private String imageFilePath;
    private Uri photoUri;
    ImageButton btn_take;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstacnceState){
        super.onCreate(savedInstacnceState);
        setContentView(R.layout.activity_camera);

        btn_take =findViewById(R.id.take_photo);
        imageView=findViewById(R.id.camera_imageview);

        btn_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA);
                if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA}, 0);
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
                }
            }
        });
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode==1&& resultCode== Activity.RESULT_OK)
        {
            Bitmap bitmap = (Bitmap)intent.getExtras().get("data");
            if(bitmap !=null)
            {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
    }
