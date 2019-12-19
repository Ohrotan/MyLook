package com.ssu.mylook;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.UnfavoriteClotheAdapter;
import com.ssu.mylook.dto.CoordiDTO;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class UnfavoriteClotheActivity extends AppCompatActivity {

    private UnfavoriteClotheAdapter adapter;
    private GridView myListView;
    private TextView allCoordi;
    private TextView neverCoordi;
    private TextView tv;
    final int[] ratio = new int[]{1, 1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfavorite_clothe);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("한번도 안 입은 옷 분석");

//        adapter = new UnfavoriteClotheAdapter();
        myListView = (GridView) findViewById(R.id.ZeroClotheGridView);
        allCoordi = findViewById(R.id.all_coori_num);
        neverCoordi = findViewById(R.id.never_coordi_num);
        tv = findViewById(R.id.textView8);

        setData();
    }


    private void setData() {
        //코디 총 갯수, 한 번도 입지않은 코디 가져와서 출력해야함
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("coordi")
                .whereEqualTo("count", 0)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<CoordiDTO> list = new ArrayList<>();
                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                            CoordiDTO item = doc.toObject(CoordiDTO.class);
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
                    ratio[0] = task.getResult().size();
                    allCoordi.setText(" / " + task.getResult().size() + "벌");
                    db.collection("coordi").whereEqualTo("count", 0).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                //Log.d("TAG", task.getResult().size() + "");
                                ratio[1] = task.getResult().size();
                                neverCoordi.setText(+task.getResult().size() + "벌");

                                tv.setText("한번도 안 입은 옷: " + Math.round((float) ratio[1] / ratio[0] * 100.0) +"%");
                            } else {
                                //Log.d("TAG", "Error getting documents: ", task.getException());
                                neverCoordi.setText("Error");
                            }
                        }
                    });
                } else {
                    //Log.d("TAG", "Error getting documents: ", task.getException());
                    allCoordi.setText("Error");
                }
            }
        });


    }

}


