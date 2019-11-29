package com.ssu.mylook;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class StyleAnalysisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_analysis);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("나의 성향 분석");
    }
}
