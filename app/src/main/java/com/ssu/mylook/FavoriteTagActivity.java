package com.ssu.mylook;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.FavoriteTagAdapter;
import com.ssu.mylook.dto.Custom2DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FavoriteTagActivity extends AppCompatActivity {

    private FavoriteTagAdapter adapter;
    private ListView myListView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public int simple=0;
    int campus=0;
    int casual=0;
    int unique=0;
    int sporty=0;
    int lovely=0;
    int office=0;
    int sexy=0;
    int fancy=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_tag);
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("나의 성향 분석");

        myListView =(ListView)findViewById(R.id.TagListView);

        Spinner s = (Spinner)findViewById(R.id.favor_tag_spin);
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
            db.collection("coordi")
                    .whereEqualTo("tag","심플베이직")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<Custom2DTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                Custom2DTO item = doc.toObject(Custom2DTO.class);
                                simple++;
                                item.setCount(simple);
                            }
                        }
                    });
            db.collection("coordi")
                    .whereEqualTo("tag","캠퍼스")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<Custom2DTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                Custom2DTO item = doc.toObject(Custom2DTO.class);
                                campus++;
                                item.setCount(campus);
                            }
                        }
                    });
            db.collection("coordi")
                    .whereEqualTo("tag","캐주얼")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<Custom2DTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                Custom2DTO item = doc.toObject(Custom2DTO.class);
                                casual++;
                                item.setCount(casual);
                            }
                        }
                    });
            db.collection("coordi")
                    .whereEqualTo("tag","유니크")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<Custom2DTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                Custom2DTO item = doc.toObject(Custom2DTO.class);
                                unique++;
                                item.setCount(unique);
                            }
                        }
                    });db.collection("coordi")
                    .whereEqualTo("tag","스포티")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<Custom2DTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                Custom2DTO item = doc.toObject(Custom2DTO.class);
                                sporty++;
                                item.setCount(lovely);
                            }
                        }
                    });db.collection("coordi")
                    .whereEqualTo("tag","러블리")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<Custom2DTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                Custom2DTO item = doc.toObject(Custom2DTO.class);
                                lovely++;
                                item.setCount(lovely);
                            }
                        }
                    });db.collection("coordi")
                    .whereEqualTo("tag","오피스룩")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<Custom2DTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                Custom2DTO item = doc.toObject(Custom2DTO.class);
                                office++;
                                item.setCount(office);
                            }
                        }
                    });db.collection("coordi")
                    .whereEqualTo("tag","섹시글램")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<Custom2DTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                Custom2DTO item = doc.toObject(Custom2DTO.class);
                                sexy++;
                                item.setCount(sexy);
                            }
                        }
                    });
            db.collection("coordi")
                    .whereEqualTo("tag","화려한")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<Custom2DTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                Custom2DTO item = doc.toObject(Custom2DTO.class);
                                fancy++;
                                item.setCount(fancy);
                            }
                        }
                    });
            Map<String, Integer> tags = new HashMap<>();
            tags.put("심플베이직", simple);
            tags.put("캠퍼스룩", campus);
            tags.put("캐주얼", casual);
            tags.put("유니크", unique);
            tags.put("스포티", sporty);
            tags.put("러블리", lovely);
            tags.put("오피스룩", office);
            tags.put("섹시글램", sexy);
            tags.put("화려한", fancy);
            //MapPost myMapPost = new MapPost("My great post", categories);
            db.collection("coordi")
                    .orderBy("tag")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<Custom2DTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                Custom2DTO item = doc.toObject(Custom2DTO.class);
                                list.add(item);
                            }
                            adapter = new FavoriteTagAdapter(FavoriteTagActivity.this, list);
                            myListView.setAdapter(adapter);
                        }
                    });

        }
//        else if(position==1){
//            db.collection("coordi").orderBy("tag", Query.Direction.ASCENDING)
//                    .get()
//                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                        @Override
//                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                            ArrayList<CustomDTO> list = new ArrayList<>();
//                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
//                                CustomDTO item = doc.toObject(CustomDTO.class);
//                                item.setId(doc.getId());
//                                list.add(item);
//                            }
//                            adapter = new FavoriteTagAdapter(FavoriteTagActivity.this, list);
//                            myListView.setAdapter(adapter);
//                        }
//                    });
//        }

    }



    private void setRank(final String mytag) {
//        String[2][10] tag = {{"심플베이직","캠퍼스룩",  "캐주얼", "유니크", "스포티", "러블리", "오피스룩", "섹시글램", "화려한"},
//                {0}};
        db.collection("coordi").whereEqualTo("tag", mytag).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (mytag.equals("심플베이직")) {
                        simple = task.getResult().size();
                    } else if (mytag.equals("캠퍼스룩")) {
                        campus = task.getResult().size();
                    } else if (mytag.equals("캐주얼")) {
                        casual = task.getResult().size();
                    } else if (mytag.equals("유니크")) {
                        unique = task.getResult().size();
                    } else if (mytag.equals("스포티")) {
                        sporty = task.getResult().size();
                    } else if (mytag.equals("러블리")) {
                        lovely = task.getResult().size();
                    } else if (mytag.equals("오피스룩")) {
                        office = task.getResult().size();
                    } else if (mytag.equals("섹시글램")) {
                        sexy = task.getResult().size();
                    } else if (mytag.equals("화려한")) {
                        fancy = task.getResult().size();
                    }
                } else {
                    //Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });

    }
}
