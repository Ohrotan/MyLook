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
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ssu.mylook.dto.ClotheDTO;
import com.ssu.mylook.util.DBUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.UUID;


public class ClotheRegisterActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView add_photo;
    String image;
    EditText clothe_title;
    EditText memo;
    Bitmap getBitmap;

    Button btn_back;
    Button btn_save;

    String imageFilePath;
    private Uri photoUri;

    Button[] season_btn = new Button[4];
    Button[] color_btn = new Button[11];
    Button[] sort_btn = new Button[5];

    ClotheDTO result = new ClotheDTO();
    ArrayList<String> selectedSeasons = new ArrayList<>();
    String selectedSort;
    String selectedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothe_register);

        getSupportActionBar().setTitle("옷 등록");
        //findViewById
        {
            memo=findViewById(R.id.memo_box);
            clothe_title=findViewById(R.id.clothe_name_textbox);
            add_photo = findViewById(R.id.add_photo);
            sort_btn[0] = findViewById(R.id.button_top_clothe);
            sort_btn[1] = findViewById(R.id.button_bottom_clothe);
            sort_btn[2] = findViewById(R.id.button_hat);
            sort_btn[3] = findViewById(R.id.button_shoes);
            sort_btn[4] = findViewById(R.id.button_etc);

            season_btn[0] = findViewById(R.id.spr_btn);
            season_btn[1] = findViewById(R.id.sum_btn);
            season_btn[2] = findViewById(R.id.fal_btn);
            season_btn[3] = findViewById(R.id.win_btn);

            color_btn[0] = findViewById(R.id.btn_red);
            color_btn[1] = findViewById(R.id.btn_orange);
            color_btn[2] = findViewById(R.id.btn_yellow);
            color_btn[3] = findViewById(R.id.btn_green);
            color_btn[4] = findViewById(R.id.btn_blue);
            color_btn[5] = findViewById(R.id.btn_darkblue);
            color_btn[6] = findViewById(R.id.btn_violet);
            color_btn[7] = findViewById(R.id.btn_pink);
            color_btn[8] = findViewById(R.id.btn_white);
            color_btn[9] = findViewById(R.id.btn_black);
            color_btn[10] = findViewById(R.id.btn_gray);
            btn_back = findViewById(R.id.cancel_btn);
            btn_save = findViewById(R.id.save_btn);
        }


        for(int i = 0; i < 11; i++){
                this.color_btn[i].setOnClickListener(this);
            }

        for(int i = 0; i < 5; i++){
            this.sort_btn[i].setOnClickListener(this);
        }

        for(int i = 0; i < 4; i++){
            this.season_btn[i].setOnClickListener(this);
        }

            btn_back.setOnClickListener(this);
            btn_save.setOnClickListener(this);

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
        Button btn = (Button) v;
         if(v==sort_btn[0]){
            //색깔 바뀌도록
            if (sort_btn[0].getCurrentTextColor() != Color.WHITE) {
                sort_btn[0].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                sort_btn[0].setTextColor(Color.WHITE);
                selectedSort=sort_btn[0].getText().toString();
            } else {
                sort_btn[0].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                sort_btn[0].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==sort_btn[1]){
            if (sort_btn[1].getCurrentTextColor() != Color.WHITE) {
                sort_btn[1].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                sort_btn[1].setTextColor(Color.WHITE);
                selectedSort=sort_btn[1].getText().toString();
            } else {
                sort_btn[1].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                sort_btn[1].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==sort_btn[2]){
            if (sort_btn[2].getCurrentTextColor() != Color.WHITE) {
                sort_btn[2].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                sort_btn[2].setTextColor(Color.WHITE);
                selectedSort=sort_btn[2].getText().toString();
            } else {
                sort_btn[2].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                sort_btn[2].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==sort_btn[3]){
            if (sort_btn[3].getCurrentTextColor() != Color.WHITE) {
                sort_btn[3].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                sort_btn[3].setTextColor(Color.WHITE);
                selectedSort=sort_btn[3].getText().toString();
            } else {
                sort_btn[3].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                sort_btn[3].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==sort_btn[4]){
            if (sort_btn[4].getCurrentTextColor() != Color.WHITE) {
                sort_btn[4].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                sort_btn[4].setTextColor(Color.WHITE);
                selectedSort=sort_btn[4].getText().toString();
            } else {
                sort_btn[4].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                sort_btn[4].setTextColor(Color.DKGRAY);
            }
        }

        else if(v==season_btn[0]){
            if (season_btn[0].getCurrentTextColor() != Color.WHITE) {
                season_btn[0].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                season_btn[0].setTextColor(Color.WHITE);
                String btnName = btn.getText().toString();
                selectedSeasons.add(btnName);
            } else {
                season_btn[0].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                season_btn[0].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==season_btn[1]){
            if (season_btn[1].getCurrentTextColor() != Color.WHITE) {
                season_btn[1].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                season_btn[1].setTextColor(Color.WHITE);
                String btnName = btn.getText().toString();
                selectedSeasons.add(btnName);
            } else {
                season_btn[1].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                season_btn[1].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==season_btn[2]){
            if (season_btn[2].getCurrentTextColor() != Color.WHITE) {
                season_btn[2].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                season_btn[2].setTextColor(Color.WHITE);
                String btnName = btn.getText().toString();
                selectedSeasons.add(btnName);
            } else {
                season_btn[2].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                season_btn[2].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==season_btn[3]){
            if (season_btn[3].getCurrentTextColor() != Color.WHITE) {
                season_btn[3].setBackground(getResources().getDrawable(R.drawable.purple_button, null));
                season_btn[3].setTextColor(Color.WHITE);
                String btnName = btn.getText().toString();
                selectedSeasons.add(btnName);
            } else {
                season_btn[3].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                season_btn[3].setTextColor(Color.DKGRAY);
            }
        }

        else if(v==color_btn[0]){
            if (color_btn[0].getCurrentTextColor() != Color.TRANSPARENT) {
                color_btn[0].setBackground(getResources().getDrawable(R.drawable.red_button1, null));
                color_btn[0].setTextColor(Color.TRANSPARENT);
                selectedColor=color_btn[0].getText().toString();
            } else {
                color_btn[0].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                color_btn[0].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==color_btn[1]){
            if (color_btn[1].getCurrentTextColor() != Color.TRANSPARENT) {
                color_btn[1].setBackground(getResources().getDrawable(R.drawable.orange_button, null));
                color_btn[1].setTextColor(Color.TRANSPARENT);
                selectedColor=color_btn[1].getText().toString();
            } else {
                color_btn[1].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                color_btn[1].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==color_btn[2]){
            if (color_btn[2].getCurrentTextColor() != Color.TRANSPARENT) {
                color_btn[2].setBackground(getResources().getDrawable(R.drawable.yellow_button, null));
                color_btn[2].setTextColor(Color.TRANSPARENT);
                selectedColor=color_btn[2].getText().toString();
            } else {
                color_btn[2].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                color_btn[2].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==color_btn[3]){
            if (color_btn[3].getCurrentTextColor() != Color.TRANSPARENT) {
                color_btn[3].setBackground(getResources().getDrawable(R.drawable.green_button, null));
                color_btn[3].setTextColor(Color.TRANSPARENT);
                selectedColor=color_btn[3].getText().toString();
            } else {
                color_btn[3].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                color_btn[3].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==color_btn[4]){
            if (color_btn[4].getCurrentTextColor() != Color.TRANSPARENT) {
                color_btn[4].setBackground(getResources().getDrawable(R.drawable.blue_button, null));
                color_btn[4].setTextColor(Color.TRANSPARENT);
                selectedColor=color_btn[4].getText().toString();
            } else {
                color_btn[4].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                color_btn[4].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==color_btn[5]){
            if (color_btn[5].getCurrentTextColor() != Color.TRANSPARENT) {
                color_btn[5].setBackground(getResources().getDrawable(R.drawable.darkblue_button, null));
                color_btn[5].setTextColor(Color.TRANSPARENT);
                selectedColor=color_btn[5].getText().toString();
            } else {
                color_btn[5].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                color_btn[5].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==color_btn[6]){
            if (color_btn[6].getCurrentTextColor() != Color.TRANSPARENT) {
                color_btn[6].setBackground(getResources().getDrawable(R.drawable.violet_button, null));
                color_btn[6].setTextColor(Color.TRANSPARENT);
                selectedColor=color_btn[6].getText().toString();
            } else {
                color_btn[6].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                color_btn[6].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==color_btn[7]){
            if (color_btn[7].getCurrentTextColor() != Color.TRANSPARENT) {
                color_btn[7].setBackground(getResources().getDrawable(R.drawable.pink_button, null));
                color_btn[7].setTextColor(Color.TRANSPARENT);
                selectedColor=color_btn[7].getText().toString();
            } else {
                color_btn[7].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                color_btn[7].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==color_btn[8]){
            if (color_btn[8].getCurrentTextColor() != Color.TRANSPARENT) {
                color_btn[8].setBackground(getResources().getDrawable(R.drawable.white_button, null));
                color_btn[8].setTextColor(Color.TRANSPARENT);
                selectedColor=color_btn[8].getText().toString();
            } else {
                color_btn[8].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                color_btn[8].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==color_btn[9]){
            if (color_btn[9].getCurrentTextColor() != Color.TRANSPARENT) {
                color_btn[9].setBackground(getResources().getDrawable(R.drawable.black_button, null));
                color_btn[9].setTextColor(Color.TRANSPARENT);
                selectedColor=color_btn[9].getText().toString();
            } else {
                color_btn[9].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                color_btn[9].setTextColor(Color.DKGRAY);
            }
        }
        else if(v==color_btn[10]){
            if (color_btn[10].getCurrentTextColor() != Color.TRANSPARENT) {
                color_btn[10].setTextColor(Color.TRANSPARENT);
                selectedColor=color_btn[10].getText().toString();
            } else {
                color_btn[10].setBackground(getResources().getDrawable(R.drawable.gray_button, null));
                color_btn[10].setTextColor(Color.DKGRAY);
            }
        }

        else if(v==btn_back){
            //저장하지 않고
             onBackPressed();
        }
        else if(v==btn_save){
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
             result.setIMAGE(uniqueID);
             result.setTTL(clothe_title.getText().toString());
             result.setMEMO(memo.getText().toString());
             result.setSORT(selectedSort);
             result.setCOLOR(selectedColor);
             result.setImageBitmap(getBitmap);

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
             result.setREGDATE(y + "-" + m + "-" + d + " " + h + ":" + min);

             new DBUtil().addClothe(result);

            //저장하고
            intent = new Intent(this,ClosetActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
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
                    getBitmap=bitmap;
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

