package com.ssu.mylook;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CoordiMainActivity extends AppCompatActivity implements View.OnClickListener {

    private CoordiMainAdapter adapter;
    private GridView MyGridView;
    TextView Springtv;
    TextView Summertv;
    TextView Falltv;
    TextView Wintertv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordi_main);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("코디 목록");
        Springtv=findViewById(R.id.spring_coordi);
        Summertv=findViewById(R.id.summer_coordi);
        Falltv=findViewById(R.id.fall_coordi);
        Wintertv=findViewById(R.id.winter_coordi);

        Springtv.setOnClickListener(this);
        Summertv.setOnClickListener(this);
        Falltv.setOnClickListener(this);
        Wintertv.setOnClickListener(this);

        adapter = new CoordiMainAdapter();
        MyGridView =(GridView)findViewById(R.id.CoordiMainGridView);

        //textView tv 대신 그리드뷰 적용되게 변경해보기
        final TextView tv = (TextView)findViewById(R.id.arrange_text);
        Spinner s = (Spinner)findViewById(R.id.arrange_spin);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv.setText("position : " + position + parent.getItemAtPosition(position));
                tv.setText(""+parent.getItemAtPosition(position));
                MyGridView.setAdapter(adapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
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
    private void showToast(String message){
        Toast toast=Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onClick(View v) {
        if (v==Springtv){
            showToast("spring category");
        } else if(v==Summertv){
            showToast("summer category");
        }else if(v==Falltv){
            showToast("fall category");
        } else if(v==Wintertv){
            showToast("winter category");
        }
    }
}
