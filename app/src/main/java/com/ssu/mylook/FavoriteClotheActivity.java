package com.ssu.mylook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ListView;

public class FavoriteClotheActivity extends AppCompatActivity {

    private JungeunCustomAdapter adapter;
    private ListView MyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_clothe);

        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("나의 성향 분석");


        adapter = new JungeunCustomAdapter();
        MyListView =(ListView)findViewById(R.id.ListView);

        setData();

        MyListView.setAdapter(adapter);
    }

    // ranks, arrResId, titles, contents를 서버에서 가져온 데이터라고 가정.(일단레이아웃부터)
    private void setData() {
        String[] ranks = getResources().getStringArray(R.array.favorite_clothe_ranking);
        TypedArray arrResId = getResources().obtainTypedArray(R.array.res_Id);
        String[] titles = getResources().getStringArray(R.array.favorite_clothe_title);
        String[] contents = getResources().getStringArray(R.array.favorite_clothe_number);

        for (int i = 0; i < arrResId.length(); i++) {
            CustomDTO dto = new CustomDTO();
            dto.setRank(ranks[i]);
            dto.setResId(arrResId.getResourceId(i, 0));
            dto.setTitle(titles[i]);
            dto.setContent(contents[i]);

           adapter.addItem(dto);

        }
    }

}
