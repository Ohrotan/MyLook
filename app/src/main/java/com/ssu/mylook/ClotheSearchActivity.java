package com.ssu.mylook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.ClosetViewAdapter;
import com.ssu.mylook.dto.ClotheDTO;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ClotheSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MyTag";
    GridView gridView;
    ClosetViewAdapter closetViewAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText editText;
    ImageButton search_btn;
    String search_keyword;
    ArrayList<ClotheDTO> list;
    ArrayList<ClotheDTO> resultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothe_search_layout);

        editText = (EditText) findViewById(R.id.search_view);
        search_btn = (ImageButton) findViewById(R.id.search_btn);

        gridView = (GridView) findViewById(R.id.gridView);

        search_btn.setOnClickListener(this);


        //setData(0); //setAdapter

        //데이터를 list에 불러옴, 이걸 사용하면 됨
        db.collection("clothes")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                         list = new ArrayList<>();
                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                            ClotheDTO item = doc.toObject(ClotheDTO.class);
                            item.setId(doc.getId());
                            list.add(item);
                            //Toast.makeText(getApplicationContext(), ""+item.getTTL(), Toast.LENGTH_LONG).show();
                        }
                    }
                });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ClotheDTO clothe = list.get(position).getID();
                Intent intent = new Intent(ClotheSearchActivity.this, ClotheViewActivity.class);
                Log.d(TAG, closetViewAdapter.getItem(position).getId());

                intent.putExtra("clotheID", closetViewAdapter.getItem(position).getId());

                //Bundle bundle = new Bundle();

                startActivity(intent);
            }

        });
    }



        @Override
        public void onClick (View v){
            if (v == search_btn) {
                //검색한 단어
                search_keyword = editText.getText().toString();
               // Toast.makeText(getApplicationContext(), "" + search_keyword, Toast.LENGTH_LONG).show();
                resultList = new ArrayList<>();
                //검색한 단어와 리스트의 단어를 비교
               for(ClotheDTO dto : list){
                   if(dto.getTitle().contains(search_keyword)==true){
                       resultList.add(dto);
                       //list.remove(dto);
                   }
               }
                closetViewAdapter = new ClosetViewAdapter(ClotheSearchActivity.this, resultList);
                gridView.setAdapter(closetViewAdapter);
            }
        }

        public void clickSeason (View v){
            TextView spring_menu = findViewById(R.id.upper_spring);
            TextView summer_menu = findViewById(R.id.upper_summer);
            TextView fall_menu = findViewById(R.id.upper_fall);
            TextView winter_menu = findViewById(R.id.upper_winter);

            //중복선택 되도록 짜야함
            if (v == spring_menu) {
                if (spring_menu.getCurrentTextColor() != Color.WHITE) {
                    spring_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                    spring_menu.setTextColor(Color.WHITE);
                } else {
                    spring_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                    spring_menu.setTextColor(Color.DKGRAY);
                }
            }
            if (v == summer_menu) {
                if (summer_menu.getCurrentTextColor() != Color.WHITE) {
                    summer_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                    summer_menu.setTextColor(Color.WHITE);
                } else {
                    summer_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                    summer_menu.setTextColor(Color.DKGRAY);
                }
            }
            if (v == fall_menu) {
                if (fall_menu.getCurrentTextColor() != Color.WHITE) {
                    fall_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                    fall_menu.setTextColor(Color.WHITE);
                } else {
                    fall_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                    fall_menu.setTextColor(Color.DKGRAY);
                }
            }
            if (v == winter_menu) {
                if (winter_menu.getCurrentTextColor() != Color.WHITE) {
                    winter_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                    winter_menu.setTextColor(Color.WHITE);
                } else {
                    winter_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                    winter_menu.setTextColor(Color.DKGRAY);
                }
            }


        }

        public void clickTab (View v){
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