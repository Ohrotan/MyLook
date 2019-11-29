package com.ssu.mylook;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class FavoriteColorActivity extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    public RecyclerView.LayoutManager layoutManager;
//    private RecyclerView.Adapter adapter;
    private FavoriteColorAdapter adapter;
    private ListView MyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_color);
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("나의 성향 분석");

        adapter = new FavoriteColorAdapter();
        MyListView =(ListView)findViewById(R.id.ColorListView);

        setData();

        MyListView.setAdapter(adapter);
//        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//
//        layoutManager = new LinearLayoutManager(this);
//
//
//        recyclerView.setLayoutManager(layoutManager);
//
//        adapter = new RecyclerViewColorAdapter();
//        recyclerView.setAdapter(adapter);


    }

    private void setData() {
        String[] ranks = getResources().getStringArray(R.array.favorite_color_rank);
        String[] titles = getResources().getStringArray(R.array.favorite_color_title);
        String[] contents = getResources().getStringArray(R.array.favorite_color_number);

        for (int i = 0; i < ranks.length; i++) {
            CustomDTO dto = new CustomDTO();
            dto.setRank(ranks[i]);
            dto.setTitle(titles[i]);
            dto.setContent(contents[i]);

            adapter.addItem(dto);

        }
    }
}
