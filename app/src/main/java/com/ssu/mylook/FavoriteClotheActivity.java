package com.ssu.mylook;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.FavoriteClotheAdapter;
import com.ssu.mylook.dto.Custom3DTO;

import java.util.ArrayList;

public class FavoriteClotheActivity extends AppCompatActivity {

    private FavoriteClotheAdapter adapter;
    private ListView myListView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_clothe);

        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("나의 성향 분석");

        myListView =(ListView)findViewById(R.id.ListView);

        Spinner s = (Spinner)findViewById(R.id.favor_clothes_spin);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setData(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        setData(0);
    }


    private void setData(int position) {
        if(position==0){
            db.collection("clothes").orderBy("used")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<Custom3DTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                Custom3DTO item = doc.toObject(Custom3DTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                            adapter = new FavoriteClotheAdapter(FavoriteClotheActivity.this, list);
                            myListView.setAdapter(adapter);
                        }});
        } else if(position==1){
            db.collection("clothes").orderBy("used")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<Custom3DTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                Custom3DTO item = doc.toObject(Custom3DTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                            adapter = new FavoriteClotheAdapter(FavoriteClotheActivity.this, list);
                            myListView.setAdapter(adapter);
                        }});
        }
}

}
