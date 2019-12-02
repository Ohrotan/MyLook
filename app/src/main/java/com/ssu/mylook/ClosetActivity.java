package com.ssu.mylook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ssu.mylook.dto.ClotheItem;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ClosetActivity extends AppCompatActivity implements View.OnClickListener{

    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView textA;
    TextView textB;
    TextView textC;
    TextView textD;
    TextView textE;
    GridView gridView;
    ClosetViewAdapter closetViewAdapter;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        gridView=(GridView)findViewById(R.id.gridView);
        text1=(TextView)findViewById(R.id.upper_spring);
        text2=(TextView)findViewById(R.id.upper_summer);
        text3=(TextView)findViewById(R.id.upper_fall);
        text4=(TextView)findViewById(R.id.upper_winter);
        textA=(TextView)findViewById(R.id.upper_top);
        textB=(TextView)findViewById(R.id.upper_bottom);
        textC=(TextView)findViewById(R.id.upper_hat);
        textD=(TextView)findViewById(R.id.upper_shoes);
        textE=(TextView)findViewById(R.id.upper_etc);

        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);
        text4.setOnClickListener(this);
        textA.setOnClickListener(this);
        textB.setOnClickListener(this);
        textC.setOnClickListener(this);
        textD.setOnClickListener(this);
        textE.setOnClickListener(this);

        closetViewAdapter=new ClosetViewAdapter();
        closetViewAdapter.addItem(new ClotheItem("옷 1",R.drawable.clothe1));
        closetViewAdapter.addItem(new ClotheItem("옷 2",R.drawable.clothe2));
        closetViewAdapter.addItem(new ClotheItem("옷 3",R.drawable.clothe3));

        gridView.setAdapter(closetViewAdapter);
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(getApplicationContext(),"title:"+closetViewAdapter.getItem(i).getTitle().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    class ClosetViewAdapter extends BaseAdapter {
        ArrayList<ClotheItem> items=new ArrayList<ClotheItem>();
        @Override
        public int getCount() {
            return items.size();
        }


        public void addItem(ClotheItem clotheItem) {
            items.add(clotheItem);
        }


        @Override
        public ClotheItem getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ItemViewerActivity itemViewer = new ItemViewerActivity(getApplicationContext());
            itemViewer.setItem(items.get(i));
            return itemViewer;
        }


    }

    @Override
    public void onClick(View v) {

        if (v == textA) {
            //색깔 바뀌도록
            if (textA.getCurrentTextColor() != Color.WHITE) {
                textA.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                textA.setTextColor(Color.WHITE);
            } else {
                textA.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                textA.setTextColor(Color.DKGRAY);
            }
        } else if (v == textB) {
            if (textB.getCurrentTextColor() != Color.WHITE) {
                textB.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                textB.setTextColor(Color.WHITE);
            } else {
                textB.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                textB.setTextColor(Color.DKGRAY);
            }
        } else if (v == textC) {
            if (textC.getCurrentTextColor() != Color.WHITE) {
                textC.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                textC.setTextColor(Color.WHITE);
            } else {
                textC.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                textC.setTextColor(Color.DKGRAY);
            }
        } else if (v == textD) {
            //색깔 바뀌도록
            if (textD.getCurrentTextColor() != Color.WHITE) {
                textD.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                textD.setTextColor(Color.WHITE);
            } else {
                textD.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                textD.setTextColor(Color.DKGRAY);
            }
        } else if (v == textE) {
            if (textE.getCurrentTextColor() != Color.WHITE) {
                textE.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                textE.setTextColor(Color.WHITE);
            } else {
                textE.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                textE.setTextColor(Color.DKGRAY);
            }
        }  else if (v == text1) {
            if (text1.getCurrentTextColor() != Color.WHITE) {
                text1.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                text1.setTextColor(Color.WHITE);
            } else {
                text1.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                text1.setTextColor(Color.DKGRAY);
            }
        } else if (v == text2) {
            if (text2.getCurrentTextColor() != Color.WHITE) {
                text2.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                text2.setTextColor(Color.WHITE);
            } else {
                text2.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                text2.setTextColor(Color.DKGRAY);
            }

        }else if (v == text3) {
            if (text3.getCurrentTextColor() != Color.WHITE) {
                text3.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                text3.setTextColor(Color.WHITE);
            } else {
                text3.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                text3.setTextColor(Color.DKGRAY);
            }
        } else if (v == text4) {
            if (text4.getCurrentTextColor() != Color.WHITE) {
                text4.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                text4.setTextColor(Color.WHITE);
            } else {
                text4.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                text4.setTextColor(Color.DKGRAY);
            }

        }
    }


    public void clickTab(View v) {
        ImageView[] img = new ImageView[3];
        img[0] = findViewById(R.id.tab_closet);
        img[1] = findViewById(R.id.tab_coordi);
        img[2] = findViewById(R.id.tab_analysis);

        if (v == img[0]) {
            Intent intent = new Intent(this, ClosetActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } else if (v == img[1]) {
            Intent intent = new Intent(this, CoordiMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } else if (v == img[2]) {
            Intent intent = new Intent(this, StyleAnalysisActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }


    }
}