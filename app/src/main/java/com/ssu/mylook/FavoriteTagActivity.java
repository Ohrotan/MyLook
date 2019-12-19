package com.ssu.mylook;

import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.FavoriteTagAdapter;
import com.ssu.mylook.dto.TagColorDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FavoriteTagActivity extends AppCompatActivity {

    private FavoriteTagAdapter adapter;
    private ListView myListView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    static int simple = 0;
    static int campus = 0;
    static int casual = 0;
    static int unique = 0;
    static int sporty = 0;
    static int lovely = 0;
    static int office = 0;
    static int sexy = 0;
    static int fancy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_tag);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("입은 코디 스타일 분석");

        myListView = (ListView) findViewById(R.id.TagListView);

        Spinner s = (Spinner) findViewById(R.id.favor_tag_spin);
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
        if (position == 0) {
            simple = 0;
            unique = 0;
            casual = 0;
            campus = 0;
            sporty = 0;
            lovely = 0;
            sexy = 0;
            fancy = 0;
            office = 0;
            db.collection("coordi").whereEqualTo("tag", "심플베이직").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("TAG", task.getResult().size() + "simple");
                        simple = task.getResult().size();
                        Log.d("TAG", simple + "this is simple");
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });
            db.collection("coordi").whereEqualTo("tag", "캠퍼스룩").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("TAG", task.getResult().size() + "campus");
                        campus = task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });
            db.collection("coordi").whereEqualTo("tag", "캐주얼").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("TAG", task.getResult().size() + "casual");
                        casual = task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });
            db.collection("coordi").whereEqualTo("tag", "유니크").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
//                        Log.d("TAG", task.getResult().size() + "asdfghj");
                        unique = task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });
            db.collection("coordi").whereEqualTo("tag", "스포티").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
//                        Log.d("TAG", task.getResult().size() + "asdfghj");
                        sporty = task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });
            db.collection("coordi").whereEqualTo("tag", "러블리").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("TAG", task.getResult().size() + "lovely");
                        lovely = task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });
            db.collection("coordi").whereEqualTo("tag", "오피스룩").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
//                        Log.d("TAG", task.getResult().size() + "asdfghj");
                        office = task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });
            db.collection("coordi").whereEqualTo("tag", "섹시글램").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
//                        Log.d("TAG", task.getResult().size() + "asdfghj");
                        sexy = task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });
            db.collection("coordi").whereEqualTo("tag", "화려한").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
//                        Log.d("TAG", task.getResult().size() + "asdfghj");
                        fancy = task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });
            db.collection("coordi")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            final Map<String, Integer> tags = new HashMap<>();
                            tags.put("심플베이직", simple);
                            tags.put("캠퍼스룩", campus);
                            tags.put("캐주얼", casual);
                            tags.put("유니크", unique);
                            tags.put("스포티", sporty);
                            tags.put("러블리", lovely);
                            tags.put("오피스룩", office);
                            tags.put("섹시글램", sexy);
                            tags.put("화려한", fancy);

                            ArrayList<TagColorDTO> list = new ArrayList<>();
                            // value 내림차순으로 정렬하고, value가 같으면 key 오름차순으로 정렬
                            List<Map.Entry<String, Integer>> sorting = new LinkedList<>(tags.entrySet());
                            Collections.sort(sorting, new Comparator<Map.Entry<String, Integer>>() {
                                @Override
                                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                                    int comparision = (o1.getValue() - o2.getValue()) * -1;
                                    return comparision == 0 ? o1.getKey().compareTo(o2.getKey()) : comparision;
                                }

                            });
                            // 순서유지를 위해 LinkedHashMap을 사용
                            Map<String, Integer> sortedMap = new LinkedHashMap<>();
                            for (Iterator<Map.Entry<String, Integer>> iter = sorting.iterator(); iter.hasNext(); ) {
                                Map.Entry<String, Integer> entry = iter.next();
                                list.add(new TagColorDTO(entry.getKey(), entry.getValue()));
                                sortedMap.put(entry.getKey(), entry.getValue());
                            }

                            adapter = new FavoriteTagAdapter(FavoriteTagActivity.this, list);
                            myListView.setAdapter(adapter);
                        }
                    });
        }else if(position==1){
            db.collection("coordi")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            final Map<String, Integer> tags = new HashMap<>();
                            tags.put("심플베이직", simple);
                            tags.put("캠퍼스룩", campus);
                            tags.put("캐주얼", casual);
                            tags.put("유니크", unique);
                            tags.put("스포티", sporty);
                            tags.put("러블리", lovely);
                            tags.put("오피스룩", office);
                            tags.put("섹시글램", sexy);
                            tags.put("화려한", fancy);

                            ArrayList<TagColorDTO> list = new ArrayList<>();
                            // value 내림차순으로 정렬하고, value가 같으면 key 오름차순으로 정렬
                            List<Map.Entry<String, Integer>> sorting = new LinkedList<>(tags.entrySet());
                            Collections.sort(sorting, new Comparator<Map.Entry<String, Integer>>() {
                                @Override
                                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                                    int comparision = (o1.getValue() - o2.getValue());
                                    return comparision == 0 ? o1.getKey().compareTo(o2.getKey()) : comparision;
                                }

                            });
                            // 순서유지를 위해 LinkedHashMap을 사용
                            Map<String, Integer> sortedMap = new LinkedHashMap<>();
                            for(Iterator<Map.Entry<String, Integer>> iter = sorting.iterator(); iter.hasNext();){
                                Map.Entry<String, Integer> entry = iter.next();
                                list.add(new TagColorDTO(entry.getKey(),entry.getValue()));
                                sortedMap.put(entry.getKey(), entry.getValue());
                            }

                            adapter = new FavoriteTagAdapter(FavoriteTagActivity.this, list);
                            myListView.setAdapter(adapter);
                        }
                    });


    }

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
