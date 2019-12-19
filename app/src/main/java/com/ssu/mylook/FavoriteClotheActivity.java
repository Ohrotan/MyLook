package com.ssu.mylook;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.FavoriteClotheAdapter;
import com.ssu.mylook.dto.FavorDTO;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FavoriteClotheActivity extends AppCompatActivity {

    private FavoriteClotheAdapter adapter;
    private ListView myListView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_clothe);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("나의 성향 분석");

        myListView = (ListView) findViewById(R.id.ListView);

        Spinner s = (Spinner) findViewById(R.id.favor_clothes_spin);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setData(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        setData(0);
    }


    private void setData(int position) {
        final ArrayList<FavorDTO> favors = new ArrayList<>();
        Query query = db.collection("clothes");
        if (position == 0) {
            query = query.orderBy("count", Query.Direction.DESCENDING);
        } else if (position == 1) {
            query = query.orderBy("count", Query.Direction.ASCENDING);
        }
        query.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot obj : queryDocumentSnapshots) {
                            FavorDTO dto = obj.toObject(FavorDTO.class);
                            favors.add(dto);
                        }
                        adapter = new FavoriteClotheAdapter(FavoriteClotheActivity.this, favors);
                        myListView.setAdapter(adapter);

                    }
                });

    }

}
