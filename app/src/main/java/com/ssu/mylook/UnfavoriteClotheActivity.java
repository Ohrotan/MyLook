package com.ssu.mylook;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.UnfavoriteClotheAdapter;

public class UnfavoriteClotheActivity extends AppCompatActivity {

    private UnfavoriteClotheAdapter adapter;
    private GridView MyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfavorite_clothe);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("나의 성향 분석");

        adapter = new UnfavoriteClotheAdapter();
        MyListView =(GridView)findViewById(R.id.ZeroClotheGridView);

        setData();
        MyListView.setAdapter(adapter);

    }


    private void setData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("coordi")
                .whereEqualTo("count", 0)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("jungeun", document.getId() + "=>" + document.getData());
                            }
                        } else {
                            Log.d("jungeun", "Error getting documents : ", task.getException());
                        }
                    }

                });

    }

}

//       // TypedArray arrResId = getResources().obtainTypedArray(R.array.zero_clothe_Id);
//       // String[] titles = getResources().getStringArray(R.array.zero_clothe_title);
////        for (int i = 0; i < arrResId.length(); i++) {
////            CustomDTO dto = new CustomDTO();
////            //dto.setResId(arrResId.getResourceId(i, 0));
////            dto.setTitle(titles[i]);
////
////            adapter.addItem(dto);
////
////        }
//}


