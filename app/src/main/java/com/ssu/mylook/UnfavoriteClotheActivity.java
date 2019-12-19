package com.ssu.mylook;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.UnfavoriteClotheAdapter;
import com.ssu.mylook.dto.CustomDTO;

import java.util.ArrayList;

public class UnfavoriteClotheActivity extends AppCompatActivity {

    private UnfavoriteClotheAdapter adapter;
    private GridView myListView;
    private TextView allCoordi;
    private TextView neverCoordi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfavorite_clothe);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("나의 성향 분석");

//        adapter = new UnfavoriteClotheAdapter();
        myListView =(GridView)findViewById(R.id.ZeroClotheGridView);
        allCoordi = findViewById(R.id.all_coori_num);
        neverCoordi = findViewById(R.id.never_coordi_num);


        FirebaseFirestore db = FirebaseFirestore.getInstance();




        setData();
       // MyListView.setAdapter(adapter);

    }


    private void setData() {
        //코디 총 갯수, 한 번도 입지않은 코디 가져와서 출력해야함
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("coordi")
                .whereEqualTo("count", 0)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<CustomDTO> list = new ArrayList<>();
                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                            CustomDTO item = doc.toObject(CustomDTO.class);
                            item.setId(doc.getId());
                            list.add(item);

                        }
                        adapter = new UnfavoriteClotheAdapter(UnfavoriteClotheActivity.this, list);
                        myListView.setAdapter(adapter);
                    }
                });
        //개수 받아오기
        db.collection("coordi").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("allCoordi size :", task.getResult().size() + "");
                    allCoordi.setText("현재 옷장에 등록한 옷 : "+task.getResult().size());
                } else {
                    //Log.d("TAG", "Error getting documents: ", task.getException());
                    allCoordi.setText("Error : 옷장에 등록된 옷 data를 불러올 수 없습니다");
                }
            }
        });
        db.collection("coordi").whereEqualTo("count", 0).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //Log.d("TAG", task.getResult().size() + "");
                    neverCoordi.setText("한 번도 입지 않은 옷 : "+task.getResult().size());
                } else {
                    //Log.d("TAG", "Error getting documents: ", task.getException());
                    neverCoordi.setText("Error : 한 번도 입지 않은 옷 data를 불러올 수 없습니다.");
                }
            }
        });
    }

}


