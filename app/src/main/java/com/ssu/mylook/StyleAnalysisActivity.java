package com.ssu.mylook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.ssu.mylook.adapter.AnalysisZeroAdapter;
import com.ssu.mylook.dto.AnalysisDTO;
import com.ssu.mylook.dto.Custom2DTO;
import com.ssu.mylook.util.DBUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StyleAnalysisActivity extends AppCompatActivity implements View.OnClickListener{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button ratioBtn;
    Button tagBtn;
    Button colorBtn;
    Button mostBtn;

    ListView myZeroListView;
    AnalysisZeroAdapter adapterZero;
    TextView zeroClothesName;
    ImageView zeroClotheImg;
    TextView mostClothesName;
    ImageView mostClothesImg;

    TextView zeroRatio;
    static String analtag;
    TextView favorTag1;
    TextView favorTag2;
    TextView favorTag3;
    static String color;
    TextView favorColor1;
    TextView favorColor2;
    TextView favorColor3;
    TextView analysis;

    static int nevercoordi;
    static int allcoordi;
    static int coordiratio=0;

    static int simple = 0;
    static int campus = 0;
    static int casual = 0;
    static int unique = 0;
    static int sporty = 0;
    static int lovely = 0;
    static int office = 0;
    static int sexy = 0;
    static int fancy = 0;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_analysis);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("나의 성향 분석");

        ratioBtn = findViewById(R.id.btn_ratio_more);
        tagBtn = findViewById(R.id.btn_tag_more);
        colorBtn = findViewById(R.id.btn_color_more);
        mostBtn = findViewById(R.id.btn_favor_clothes_more);

        //myZeroListView=findViewById(R.id.style_analysis_zero_list_view);
        zeroClotheImg=findViewById(R.id.zero_clothes_img);
        zeroClothesName=findViewById(R.id.zero_clothes_name);
        mostClothesImg=findViewById(R.id.favor_clothes_img);
        mostClothesName=findViewById(R.id.favor_clothes_name);

        zeroRatio=findViewById(R.id.line1_1_ratio);
        favorTag1=findViewById(R.id.favor_tag1);
        favorTag2=findViewById(R.id.favor_tag2);
        favorTag3=findViewById(R.id.favor_tag3);
        favorColor1=findViewById(R.id.favor_color1);
        favorColor2=findViewById(R.id.favor_color2);
        favorColor3=findViewById(R.id.favor_color3);
        analysis=findViewById(R.id.analysis);

        setData();
    }

    private void setData() {

        //입지않은 옷 비율 계산
        db.collection("coordi").whereEqualTo("count", 0).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("nevercoordi :", task.getResult().size() + "");
                    nevercoordi=task.getResult().size();
                } else {
                    //Log.d("TAG", "Error getting documents: ", task.getException());

                }
            }
        });
        db.collection("coordi").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d("allcoordi", task.getResult().size() + "");
                    allcoordi=task.getResult().size();
                } else {
                    //Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });
//
//        zeroRatio.setText(20+"%");

        //0번 입은 옷 중 랜덤 옷 추천
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("coordi")
                .whereEqualTo("count", 0)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<AnalysisDTO> list = new ArrayList<>();
                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                            AnalysisDTO item = doc.toObject(AnalysisDTO.class);
                            //item.setId(doc.getId());
                            list.add(item);
                        }
                        double randomValue = Math.random();
                        int random=(int)(randomValue*nevercoordi)+1;
                        zeroClothesName.setText(list.get(random).getName());
                        new DBUtil().setImageViewFromDB(StyleAnalysisActivity.this,zeroClotheImg,list.get(random).getImg());

//
//                        adapterZero = new AnalysisZeroAdapter(StyleAnalysisActivity.this, list);
//                        myZeroListView.setAdapter(adapterZero);
                    }
                });


        //태그계산
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

                        ArrayList<Custom2DTO> list = new ArrayList<>();
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
                            list.add(new Custom2DTO(entry.getKey(), entry.getValue()));
                            sortedMap.put(entry.getKey(), entry.getValue());
                        }
                        favorTag1.setText("#"+list.get(0).getField());
                        analtag=list.get(0).getField();
                        favorTag2.setText("#"+list.get(1).getField());
                        favorTag3.setText("#"+list.get(2).getField());
                    }
                });

        //색깔분석
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

                        ArrayList<Custom2DTO> list = new ArrayList<>();
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
                            list.add(new Custom2DTO(entry.getKey(),entry.getValue()));
                            sortedMap.put(entry.getKey(), entry.getValue());

                        }
                        favorColor1.setText("#"+list.get(0).getField());
                        color=list.get(0).getField();
                        favorColor2.setText("#"+list.get(1).getField());
                        favorColor3.setText("#"+list.get(2).getField());
                    }
                });


        //가장 많이 활용된 옷



        //최종 분석 메시지
        db.collection("clothes")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        //입지 않는 옷의 비율 계산
                        coordiratio= (int) (((double)nevercoordi/allcoordi)*100);
                        zeroRatio.setText(coordiratio+"%");
                        //최종 분석 메시지
                        analysis.setText("당신은 " + color + "컬러를\n가장 선호하고,\n" + analtag + " 스타일을\n좋아하시는군요!");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v==ratioBtn){
            startActivity(new Intent(this,UnfavoriteClotheActivity.class));
        } else if(v==tagBtn){
            startActivity(new Intent(this,FavoriteTagActivity.class));
        } else if(v==colorBtn){
            startActivity(new Intent(this,FavoriteColorActivity.class));
        } else if(v==mostBtn){
            startActivity(new Intent(this,FavoriteClotheActivity.class));
        }
    }
}
