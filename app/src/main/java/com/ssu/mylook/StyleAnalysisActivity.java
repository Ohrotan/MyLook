package com.ssu.mylook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class StyleAnalysisActivity extends AppCompatActivity implements View.OnClickListener{

    Button ratioBtn;
    Button tagBtn;
    Button colorBtn;
    Button mostBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_analysis);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("나의 성향 분석");

        ratioBtn = findViewById(R.id.btn_ratio_more);
        tagBtn = findViewById(R.id.btn_tag_more);
        colorBtn = findViewById(R.id.btn_color_more);
        mostBtn = findViewById(R.id.btn_favor_clothes_more);

    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent();
//        ComponentName componentName = new ComponentName(
//                "com.ssu.mylook",
//                "com.ssu.mylook.FavoriteColorActivity",
//                "com.ssu.mylook.FavoriteTagActivity",
//                "com.ssu.mylook.FavoriteClotheActivity",
//                "com.ssu.mylook.UnfavoriteClotheActivity");
//        intent.setComponent(componentName);
//        startActivity(intent);

        if(v==ratioBtn){
            startActivity(new Intent(this,UnfavoriteClotheActivity.class));
        } else if(v==tagBtn){
            startActivity(new Intent(this,FavoriteTagActivity.class));
        } else if(v==colorBtn){
            startActivity(new Intent(this,FavoriteColorActivity.class));
        } else if(v==mostBtn){
            startActivity(new Intent(this,FavoriteClotheActivity.class));
        }
    }
}
