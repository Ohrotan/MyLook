package com.ssu.mylook;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ClosetActivity extends AppCompatActivity {

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.closet_list);

        ListView listView=(ListView)findViewById(R.id.closet_listview);

        ArrayList<Closet_ListViewItem> data =new ArrayList<>();
        Closet_ListViewItem clothe1=new Closet_ListViewItem(R.drawable.clothe1,"옷 1");
        Closet_ListViewItem clothe2=new Closet_ListViewItem(R.drawable.clothe1,"옷 2");

        data.add(clothe1);
        data.add(clothe2);

        Closet_ListViewAdapter adapter=new Closet_ListViewAdapter(this,R.layout.closet_item,data);
        listView.setAdapter(adapter);
    }
}
