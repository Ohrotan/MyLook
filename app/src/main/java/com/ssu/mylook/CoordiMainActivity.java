package com.ssu.mylook;

import android.content.Intent;
import android.graphics.Color;
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
import com.ssu.mylook.dto.CoordiDTO;

import java.util.ArrayList;



public class CoordiMainActivity extends AppCompatActivity implements View.OnClickListener {

    //이 화면에서 특정 코디 클릭 시 그 코디의 상세보기 화면으로 넘어가는 함수, 변수 구현필요

    private CoordiMainAdapter adapter;
    private GridView myGridView;

    final ArrayList<CoordiDTO> CoordiList = new ArrayList<>();
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
    static boolean spring=false;
    static boolean summer=false;
    static boolean fall=false;
    static boolean winter=false;


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


        Spinner s = (Spinner)findViewById(R.id.arrange_spin);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setData(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        setData(0);
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ClotheDTO clothe = list.get(position).getID();
                Intent intent = new Intent(CoordiMainActivity.this,CoordiViewActivity.class);

                //intent.putExtra("coordiID", CoordiViewAdapter.getItem(position).getID());

                ////Bundle bundle = new Bundle();

                startActivity(intent);
            }

        });
    }


    private void setData(int position) {
        final ArrayList<CoordiDTO> list = new ArrayList<>();
        if(spring){
            db.collection("coordi").whereEqualTo("seasons","봄")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CoordiDTO item = doc.toObject(CoordiDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                        }});
        }
        if(summer){
            db.collection("coordi").whereEqualTo("seasons","여름")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CoordiDTO item = doc.toObject(CoordiDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                        }});
        }
        if(fall){
            db.collection("coordi").whereEqualTo("seasons","가을")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CoordiDTO item = doc.toObject(CoordiDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                        }});
        }
        if(winter){
            db.collection("coordi").whereEqualTo("seasons","겨울")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CoordiDTO item = doc.toObject(CoordiDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                        }});
        }
        if(position==0){
            //ArrayList<CoordiDTO> CoordiList;
                    db.collection("coordi").orderBy("regDate", Query.Direction.DESCENDING)
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                        CoordiDTO item = doc.toObject(CoordiDTO.class);
                                        item.setId(doc.getId());
                                        list.add(item);
                                    }
                                    adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                                    myGridView.setAdapter(adapter);
                                }});
            //adapter.setListCustom(CoordiList);
            showToast("CoordiList 등록날짜순 출력중");
        } else if(position==1){
            //ArrayList<CoordiDTO> CoordiList = new DBUtil().getDatas("coordi", "rating", false);
            ArrayList<CoordiDTO> CoodiList;
            db.collection("coordi").orderBy("rating", Query.Direction.ASCENDING)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                            ArrayList<CoordiDTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CoordiDTO item = doc.toObject(CoordiDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                            myGridView.setAdapter(adapter);
                        }});
            //adapter.setListCustom(CoordiList);
            showToast("CoordiList 별점 오름차순 출력중");
        } else if(position==2){
            //ArrayList<CoordiDTO> CoordiList = new DBUtil().getDatas("coordi", "rating", true);
            //adapter.setListCustom(CoordiList);
            db.collection("coordi").orderBy("rating", Query.Direction.DESCENDING)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                            ArrayList<CoordiDTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CoordiDTO item = doc.toObject(CoordiDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                            myGridView.setAdapter(adapter);
                        }});
            //adapter.setListCustom(CoordiList);
            showToast("CoordiList 별점 내림차순 출력중");
        } else if(position==3) {
//            ArrayList<CoordiDTO> CoordiList = new DBUtil().getDatas("coordi", "count", false);
//            adapter.setListCustom(CoordiList);
            db.collection("coordi").orderBy("count", Query.Direction.ASCENDING)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                            ArrayList<CoordiDTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CoordiDTO item = doc.toObject(CoordiDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                            }
                            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                            myGridView.setAdapter(adapter);
                        }});
            //adapter.setListCustom(CoordiList);
            showToast("CoordiList 횟수 내림차순 출력중");
        } else if(position==4){
//            ArrayList<CoordiDTO> CoordiList = new DBUtil().getDatas("coordi", "count", true);
//            adapter.setListCustom(CoordiList);
            db.collection("coordi").orderBy("count", Query.Direction.DESCENDING)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                            ArrayList<CoordiDTO> list = new ArrayList<>();
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CoordiDTO item = doc.toObject(CoordiDTO.class);
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
            if (springtv.getCurrentTextColor() != Color.WHITE) {
                spring=true;
                springtv.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                springtv.setTextColor(Color.WHITE);
                showToast("spring category");
            } else {
                spring=false;
                springtv.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                springtv.setTextColor(Color.DKGRAY);
            }
        } else if(v== summertv){
            if (summertv.getCurrentTextColor() != Color.WHITE) {
                summer=true;
                summertv.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                summertv.setTextColor(Color.WHITE);showToast("summer category");
            } else {
                summer=false;
                summertv.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                summertv.setTextColor(Color.DKGRAY);
            }
        }else if(v== falltv){
            if (falltv.getCurrentTextColor() != Color.WHITE) {
                fall=true;
                falltv.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                falltv.setTextColor(Color.WHITE);
                showToast("fall category");
            } else {
                fall=false;
                falltv.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                falltv.setTextColor(Color.DKGRAY);
            }
        } else if(v== wintertv){
            if (wintertv.getCurrentTextColor() != Color.WHITE) {
                winter=true;
                wintertv.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                wintertv.setTextColor(Color.WHITE);showToast("winter category");
            } else {
                winter=false;
                wintertv.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                wintertv.setTextColor(Color.DKGRAY);
            }

        }

    }



}
