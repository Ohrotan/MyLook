package com.ssu.mylook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.ClosetViewAdapter;
import com.ssu.mylook.dto.ClotheDTO;

import java.util.ArrayList;

public class ClotheSearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG="MyTag";
    GridView gridView;
    ClosetViewAdapter closetViewAdapter;
    SearchView searchView;
    String[] clotheNameList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

  //  int j=list.size();
   // int k=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothe_search_layout);

        searchView=(SearchView) findViewById(R.id.search_view);

        final CharSequence query = searchView.getQuery();
        boolean isIconfied=searchView.isIconfiedByDefault();

        CharSequence queryHint = searchView.getQueryHint();
        searchView.setQueryHint("검색어 입력");

        gridView=(GridView)findViewById(R.id.gridView);
        setData(0); //setAdapter


     /*   searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
// do something when the focus of the query text field changes

                db.collection("clothes").whereEqualTo("title",query)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                ArrayList<ClotheDTO> list = new ArrayList<>();
                                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                    ClotheDTO item = doc.toObject(ClotheDTO.class);
                                    item.setId(doc.getId());
                                    list.add(item);
                                    Toast.makeText(getApplicationContext(), ""+item.getTitle(), Toast.LENGTH_LONG).show();
                                    //Toast.makeText(getApplicationContext(), ""+item.getTTL(), Toast.LENGTH_LONG).show();
                                }

                                closetViewAdapter = new ClosetViewAdapter(ClotheSearchActivity.this,list);
                                gridView.setAdapter(closetViewAdapter);
                            }});

            }
        });*/

        searchView.setOnQueryTextListener(this);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ClotheDTO clothe = list.get(position).getID();
                Intent intent = new Intent(ClotheSearchActivity.this,ClotheViewActivity.class);
                Log.d(TAG, closetViewAdapter.getItem(position).getId());

                    intent.putExtra("clotheID",closetViewAdapter.getItem(position).getId());

                //Bundle bundle = new Bundle();

                startActivity(intent);
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
       if(v==summer_menu){
           if (summer_menu.getCurrentTextColor() != Color.WHITE) {
               summer_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
               summer_menu.setTextColor(Color.WHITE);
           }else {
               summer_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
               summer_menu.setTextColor(Color.DKGRAY);
           }
       }
       if(v==fall_menu) {
           if (fall_menu.getCurrentTextColor() != Color.WHITE) {
               fall_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
               fall_menu.setTextColor(Color.WHITE);
           } else {
               fall_menu.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
               fall_menu.setTextColor(Color.DKGRAY);
           }
       }
       if(v==winter_menu){
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
        db.collection("clothes").whereEqualTo("title",newText)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<ClotheDTO> list = new ArrayList<>();
                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                            ClotheDTO item = doc.toObject(ClotheDTO.class);
                            item.setId(doc.getId());
                            list.add(item);
                              //Toast.makeText(getApplicationContext(), ""+item.getTTL(), Toast.LENGTH_LONG).show();
                        }

                        closetViewAdapter = new ClosetViewAdapter(ClotheSearchActivity.this,list);
                        gridView.setAdapter(closetViewAdapter);
                    }});
        return false;
    }



    private void setData(int position) {

        if(position==0){
            db.collection("clothes").orderBy("regdate", Query.Direction.DESCENDING)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<ClotheDTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                ClotheDTO item = doc.toObject(ClotheDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                                //clotheNameList[k]+=item.getTTL().toString();
                               // Toast.makeText(getApplicationContext(),"옷 이름: "+clotheNameList[k].toString(),Toast.LENGTH_LONG);
                            }
                            closetViewAdapter = new ClosetViewAdapter(ClotheSearchActivity.this,list);
                            gridView.setAdapter(closetViewAdapter);
                        }});

        }

    }
}
