package com.ssu.mylook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ssu.mylook.adapter.CoordiMainAdapter;
import com.ssu.mylook.dto.CustomDTO;

import java.util.ArrayList;



public class CoordiMainActivity extends AppCompatActivity implements View.OnClickListener {

    //이 화면에서 특정 코디 클릭 시 그 코디의 상세보기 화면으로 넘어가는 함수, 변수 구현필요

    private CoordiMainAdapter adapter;
    private GridView myGridView;

    final ArrayList<CustomDTO> CoordiList = new ArrayList<>();
    final static String TAG = "Database";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    // Create a storage reference from our app
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    //List<Object> Array = new ArrayList<Object>();
    private FirebaseDatabase mDataBase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;


    TextView springtv;
    TextView summertv;
    TextView falltv;
    TextView wintertv;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       int id = item.getItemId();
       if(id==R.id.action_add){
//           Toast.makeText(this, "코디추가버튼 클릭",Toast.LENGTH_SHORT).show();
//           return true;
           startActivity(new Intent(this,CoordiRegisterActivity.class));
       }
       return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordi_main);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("코디 목록");
        springtv =findViewById(R.id.spring_coordi);
        summertv =findViewById(R.id.summer_coordi);
        falltv =findViewById(R.id.fall_coordi);
        wintertv=findViewById(R.id.winter_coordi);

        myGridView =(GridView)findViewById(R.id.CoordiMainGridView);

        springtv.setOnClickListener(this);
        summertv.setOnClickListener(this);
        falltv.setOnClickListener(this);
        wintertv.setOnClickListener(this);
        myGridView =(GridView)findViewById(R.id.CoordiMainGridView);


        //textView tv 대신 그리드뷰 적용되게 변경해보기
        final TextView tv = (TextView)findViewById(R.id.arrange_text);
        Spinner s = (Spinner)findViewById(R.id.arrange_spin);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv.setText("position : " + position + parent.getItemAtPosition(position));
                tv.setText(""+parent.getItemAtPosition(position));
                //MyGridView.setAdapter(adapter);
                setData(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        setData(0);
        //adapter = new CoordiMainAdapter(this);

        //리스트의 데이터를 먼저 세팅한 후에 setAdapter해야함!!!!!!
//        myGridView.setAdapter(adapter);
//        adapter.setListCustom(CoordiList);
//        myGridView.setAdapter(adapter);


    }


    private void setData(int position) {
        if(position==0){
            //ArrayList<CustomDTO> CoordiList;
                    db.collection("coordi").orderBy("regDate", Query.Direction.DESCENDING)
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
                                    adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                                    myGridView.setAdapter(adapter);
                                }});
            //adapter.setListCustom(CoordiList);
            showToast("CoordiList 등록날짜순 출력중");
        } else if(position==1){
            //ArrayList<CustomDTO> CoordiList = new DBUtil().getDatas("coordi", "rating", false);
            ArrayList<CustomDTO> CoodiList;
            db.collection("coordi").orderBy("rating", Query.Direction.ASCENDING)
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
                            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                            myGridView.setAdapter(adapter);
                        }});
            //adapter.setListCustom(CoordiList);
            showToast("CoordiList 별점 오름차순 출력중");
        } else if(position==2){
            //ArrayList<CustomDTO> CoordiList = new DBUtil().getDatas("coordi", "rating", true);
            //adapter.setListCustom(CoordiList);
            db.collection("coordi").orderBy("rating", Query.Direction.DESCENDING)
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
                            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                            myGridView.setAdapter(adapter);
                        }});
            //adapter.setListCustom(CoordiList);
            showToast("CoordiList 별점 내림차순 출력중");
        } else if(position==3) {
//            ArrayList<CustomDTO> CoordiList = new DBUtil().getDatas("coordi", "count", false);
//            adapter.setListCustom(CoordiList);
            db.collection("coordi").orderBy("count", Query.Direction.ASCENDING)
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
                            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                            myGridView.setAdapter(adapter);
                        }});
            //adapter.setListCustom(CoordiList);
            showToast("CoordiList 횟수 내림차순 출력중");
        } else if(position==4){
//            ArrayList<CustomDTO> CoordiList = new DBUtil().getDatas("coordi", "count", true);
//            adapter.setListCustom(CoordiList);
            db.collection("coordi").orderBy("count", Query.Direction.DESCENDING)
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
                            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                            myGridView.setAdapter(adapter);
                        }});
            //adapter.setListCustom(CoordiList);
            showToast("CoordiList 횟수 오름차순 출력중");
        }

    }
    private void showToast(String message){
        Toast toast=Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onClick(View v) {
        if (v== springtv){
            showToast("spring category");
        } else if(v== summertv){
            showToast("summer category");
        }else if(v== falltv){
            showToast("fall category");
        } else if(v== wintertv){
            showToast("winter category");
        }
    }



}
