package com.ssu.mylook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ListView;

public class UnfavoriteClotheActivity extends AppCompatActivity {


    private JungeunCustomAdapter adapter;
    private ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfavorite_clothe);
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("나의 성향 분석");


        adapter = new JungeunCustomAdapter();
        listView = (ListView)findViewById(R.id.listView);

        //setData();

        listView.setAdapter(adapter);
    }

    // 보통 ListView는 통신을 통해 가져온 데이터를 보여줍니다.
    // arrResId, titles, contents를 서버에서 가져온 데이터라고 생각하시면 됩니다.
    private void setData() {
        String[] ranks = getResources().getStringArray(R.array.favorite_clothe_ranking);
        TypedArray arrResId = getResources().obtainTypedArray(R.array.res_Id);
        String[] titles = getResources().getStringArray(R.array.favorite_clothe_title);
        String[] contents = getResources().getStringArray(R.array.favorite_clothe_number);

        for (int i = 0; i < arrResId.length(); i++) {
            CustomDTO dto = new CustomDTO();
            dto.setContent(ranks[i]);
            dto.setResId(arrResId.getResourceId(i, 0));
            dto.setTitle(titles[i]);
            dto.setContent(contents[i]);

            adapter.addItem(dto);
        }
    }

}
