package com.ssu.mylook;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.GridView;

import com.ssu.mylook.adapter.UnfavoriteClotheAdapter;
import com.ssu.mylook.dto.CustomDTO;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class UnfavoriteClotheActivity extends AppCompatActivity {

    private UnfavoriteClotheAdapter adapter;
    private GridView MyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfavorite_clothe);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("나의 성향 분석");

        adapter = new UnfavoriteClotheAdapter();
        MyListView =(GridView)findViewById(R.id.ZeroClotheGridView);

        setData();

        MyListView.setAdapter(adapter);
    }

    private void setData() {
        TypedArray arrResId = getResources().obtainTypedArray(R.array.zero_clothe_Id);
        String[] titles = getResources().getStringArray(R.array.zero_clothe_title);

        for (int i = 0; i < arrResId.length(); i++) {
            CustomDTO dto = new CustomDTO();
            //dto.setResId(arrResId.getResourceId(i, 0));
            dto.setTitle(titles[i]);

            adapter.addItem(dto);

        }
    }
}