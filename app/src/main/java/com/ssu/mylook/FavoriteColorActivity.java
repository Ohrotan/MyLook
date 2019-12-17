package com.ssu.mylook;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.FavoriteColorAdapter;
import com.ssu.mylook.dto.CustomDTO;

import java.util.ArrayList;


public class FavoriteColorActivity extends AppCompatActivity {
    private FavoriteColorAdapter adapter;

    private ListView myListView;
    private TextView ranking;
    private TextView color;
    private TextView number;
    private int num=0;
    private int initialNum=1;


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_color);
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("나의 성향 분석");

        myListView =(ListView)findViewById(R.id.ColorListView);
        ranking=findViewById(R.id.color_rank);
        color=findViewById(R.id.color_title);
        number=findViewById(R.id.color_number);


        Spinner s = (Spinner)findViewById(R.id.favor_color_spin);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setData(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        db.collection("clothes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //Log.d("TAG", task.getResult().size() + "");
                    num= task.getResult().size();
                } else {
                    //Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });
        setData(0);
    }

    private void setData(int position) {
        if(position==0) {//활용횟수 많은순(내림차순)


        } else if(position==1) {//활용횟수 적은순(오름차순)
            db.collection("clothes").orderBy("color", Query.Direction.ASCENDING)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<CustomDTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CustomDTO item = doc.toObject(CustomDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                                int j=1;
                                if(!(j>num)){
                                    ranking.setText(initialNum);
                                    j++;
                                }
                            }
                            adapter = new FavoriteColorAdapter(FavoriteColorActivity.this, list);
                            myListView.setAdapter(adapter);
                        }
                    });
        }


    }
}
