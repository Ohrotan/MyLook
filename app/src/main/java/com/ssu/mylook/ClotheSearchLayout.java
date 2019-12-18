package com.ssu.mylook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.ClosetViewAdapter;

import com.ssu.mylook.dto.ClotheItem;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ClotheSearchLayout extends AppCompatActivity implements SearchView.OnQueryTextListener {


    GridView gridView;
    ClosetViewAdapter closetViewAdapter;
    SearchView searchView;
    String keyword;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<ClotheItem> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothe_search_layout);

        searchView=(SearchView) findViewById(R.id.search_view);
        CharSequence query = searchView.getQuery();
        boolean isIconfied=searchView.isIconfiedByDefault();

        CharSequence queryHint = searchView.getQueryHint();
        searchView.setQueryHint("검색어 입력");

        gridView=(GridView)findViewById(R.id.gridView);
        setData(0);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
// do something when the focus of the query text field changes
            }
        });

        searchView.setOnQueryTextListener(this);


        closetViewAdapter=new ClosetViewAdapter(this);
        gridView.setAdapter(closetViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(getApplicationContext(),"title:"+closetViewAdapter.getItem(i).getTitle().toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }



   public void clickSeason(View v) {
       TextView spring_menu = findViewById(R.id.upper_spring);
       TextView summer_menu = findViewById(R.id.upper_summer);
       TextView fall_menu = findViewById(R.id.upper_fall);
       TextView winter_menu = findViewById(R.id.upper_winter);

       //중복선택 되도록 짜야함
       if(v==spring_menu){
           if (spring_menu.getCurrentTextColor() != Color.WHITE) {
               spring_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
               spring_menu.setTextColor(Color.WHITE);
           }else {
               spring_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
               spring_menu.setTextColor(Color.DKGRAY);
           }
       }
       else if(v==summer_menu){
           if (summer_menu.getCurrentTextColor() != Color.WHITE) {
               summer_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
               summer_menu.setTextColor(Color.WHITE);
           }else {
               summer_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
               summer_menu.setTextColor(Color.DKGRAY);
           }
       }
       else if(v==fall_menu) {
           if (fall_menu.getCurrentTextColor() != Color.WHITE) {
               fall_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
               fall_menu.setTextColor(Color.WHITE);
           } else {
               fall_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
               fall_menu.setTextColor(Color.DKGRAY);
           }
       }
       else if(v==winter_menu){
           if (winter_menu.getCurrentTextColor() != Color.WHITE) {
               winter_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
               winter_menu.setTextColor(Color.WHITE);
               }
           else {
               winter_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
               winter_menu.setTextColor(Color.DKGRAY);
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text= newText;
        closetViewAdapter.filter(text);
        return false;
    }

    private void setData(int position) {
        if(position==0){
            db.collection("clothes").orderBy("regdate", Query.Direction.DESCENDING)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<ClotheItem> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                ClotheItem item = doc.toObject(ClotheItem.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                            closetViewAdapter = new ClosetViewAdapter(ClotheSearchLayout.this,list);
                            gridView.setAdapter(closetViewAdapter);
                        }});

        }

    }
}
