package com.ssu.mylook;

import android.os.Bundle;
import android.widget.ListView;

import com.ssu.mylook.adapter.FavoriteTagAdapter;
import com.ssu.mylook.dto.CustomDTO;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FavoriteTagActivity extends AppCompatActivity {

    private FavoriteTagAdapter adapter;
    private ListView MyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_tag);
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("나의 성향 분석");

        adapter = new FavoriteTagAdapter();
        MyListView =(ListView)findViewById(R.id.TagListView);

        setData();

        MyListView.setAdapter(adapter);
    }

    private void setData() {
        String[] ranks = getResources().getStringArray(R.array.favorite_tag_rank);
        String[] titles = getResources().getStringArray(R.array.favorite_tag_title);
        String[] contents = getResources().getStringArray(R.array.favorite_tag_number);

        for (int i = 0; i < ranks.length; i++) {
            CustomDTO dto = new CustomDTO();
            dto.setRank(ranks[i]);
            dto.setTitle(titles[i]);
            dto.setContent(contents[i]);

            adapter.addItem(dto);

        }
    }
}
