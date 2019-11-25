package com.ssu.mylook;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CoordiMainActivity extends AppCompatActivity {

    private CoordiMainAdapter adapter;
    private GridView MyGridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordi_main);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("코디 목록");

        adapter = new CoordiMainAdapter();
        MyGridView =(GridView)findViewById(R.id.CoordiMainGridView);

        setData();

        MyGridView.setAdapter(adapter);
    }

    private void setData() {
        TypedArray arrResId = getResources().obtainTypedArray(R.array.coordi_Id);
        String[] titles = getResources().getStringArray(R.array.coordi_title);
        String[] ranks = getResources().getStringArray(R.array.coordi_rank);
        String[] contents = getResources().getStringArray(R.array.coordi_number);

        for (int i = 0; i < arrResId.length(); i++) {
            CustomDTO dto = new CustomDTO();
            dto.setResId(arrResId.getResourceId(i, 0));
            dto.setTitle(titles[i]);
            dto.setRank(ranks[i]);
            dto.setContent(contents[i]);

            adapter.addItem(dto);

        }
    }
}
