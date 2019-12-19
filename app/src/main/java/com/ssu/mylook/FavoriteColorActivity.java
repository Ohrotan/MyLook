package com.ssu.mylook;

import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.FavoriteColorAdapter;
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



public class FavoriteColorActivity extends AppCompatActivity {
    private FavoriteColorAdapter adapter;
    private ListView myListView;
    private TextView ranking;
    private TextView color;
    private TextView number;
    private int num = 0;
    private int initialNum = 1;

    static int red = 0;
    static int blue = 0;
    static int black = 0;
    static int yellow = 0;
    static int orange = 0;
    static int green = 0;
    static int purple = 0;
    static int pink = 0;
    //static int pattern = 0;
    static int gray = 0;
    static int navy = 0;
    static int white = 0;


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_color);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("입은 옷의 색깔 분석");
        myListView = (ListView) findViewById(R.id.ColorListView);
        ranking = findViewById(R.id.color_rank);
        color = findViewById(R.id.color_title);
        number = findViewById(R.id.color_number);
        Spinner s = (Spinner) findViewById(R.id.favor_color_spin);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setData(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void setData(int position) {
        if (position == 0) {//활용횟수 많은순(내림차순)
            db.collection("clothes").whereEqualTo("color", "빨강").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("TAG", task.getResult().size() + "red");
                        red=task.getResult().size();
                        Log.d("TAG", red + "this is "+red);
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });db.collection("clothes").whereEqualTo("color", "주황").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("TAG", task.getResult().size() + "orange");
                        orange=task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });db.collection("clothes").whereEqualTo("color", "노랑").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("TAG", task.getResult().size() + "simple");
                        yellow=task.getResult().size();
                        Log.d("TAG", yellow + "this is "+yellow);
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });db.collection("clothes").whereEqualTo("color", "초록").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("TAG", task.getResult().size() + "green");
                        green=task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });db.collection("clothes").whereEqualTo("color", "파랑").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("TAG", task.getResult().size() + "blue");
                        blue=task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });db.collection("clothes").whereEqualTo("color", "남색").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("TAG", task.getResult().size() + "navy");
                        navy=task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });db.collection("clothes").whereEqualTo("color", "보라").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("TAG", task.getResult().size() + "purple");
                        purple=task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });db.collection("clothes").whereEqualTo("color", "분홍").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        Log.d("TAG", task.getResult().size() + "pink");
                        pink=task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });db.collection("clothes").whereEqualTo("color", "검정").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        black=task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });db.collection("clothes").whereEqualTo("color", "하양").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        white=task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });db.collection("clothes").whereEqualTo("color", "회색").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        gray=task.getResult().size();
                    } else {
                        //Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });
//            db.collection("clothes").whereEqualTo("color", "패턴").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if (task.isSuccessful()) {
//                        pattern=task.getResult().size();
//                    } else {
//                        //Log.d("TAG", "Error getting documents: ", task.getException());
//                    }
//                }
//            });
            db.collection("clothes")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            final Map<String, Integer> tags = new HashMap<>();
                            tags.put("빨강", red);
                            tags.put("주황", orange);
                            tags.put("노랑", yellow);
                            tags.put("초록", green);
                            tags.put("파랑", blue);
                            tags.put("남색", navy);
                            tags.put("보라", purple);
                            tags.put("분홍", pink);
                            tags.put("검정", black);
                            tags.put("하양", white);
                            tags.put("회색", gray);
                            //tags.put("패턴", pattern);

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
                            for(Iterator<Map.Entry<String, Integer>> iter = sorting.iterator(); iter.hasNext();){
                                Map.Entry<String, Integer> entry = iter.next();
                                list.add(new TagColorDTO(entry.getKey(),entry.getValue()));
                                sortedMap.put(entry.getKey(), entry.getValue());
                            }

                            adapter = new FavoriteColorAdapter(FavoriteColorActivity.this, list);
                            myListView.setAdapter(adapter);
                        }
                    });

        } else if (position == 1) {
            db.collection("clothes")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            final Map<String, Integer> tags = new HashMap<>();
                            tags.put("빨강", red);
                            tags.put("주황", orange);
                            tags.put("노랑", yellow);
                            tags.put("초록", green);
                            tags.put("파랑", blue);
                            tags.put("남색", navy);
                            tags.put("보라", purple);
                            tags.put("분홍", pink);
                            tags.put("검정", black);
                            tags.put("하양", white);
                            tags.put("회색", gray);
                            //tags.put("패턴", pattern);

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

                            adapter = new FavoriteColorAdapter(FavoriteColorActivity.this, list);
                            myListView.setAdapter(adapter);
                        }
                    });
        }


    }
}
