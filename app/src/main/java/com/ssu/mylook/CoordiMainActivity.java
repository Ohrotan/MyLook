package com.ssu.mylook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class CoordiMainActivity extends AppCompatActivity implements View.OnClickListener {

    //이 화면에서 특정 코디 클릭 시 그 코디의 상세보기 화면으로 넘어가는 함수, 변수 구현필요
    public static final int REQUEST_CODE_COUNT = 1;
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

    List<String> selectedSeasons = new ArrayList<>();
    TextView springtv;
    TextView summertv;
    TextView falltv;
    TextView wintertv;
    static boolean spring = false;
    static boolean summer = false;
    static boolean fall = false;
    static boolean winter = false;
    Spinner s;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
//           Toast.makeText(this, "코디추가버튼 클릭",Toast.LENGTH_SHORT).show();
//           return true;
            Intent intent = new Intent(this, CoordiRegisterActivity.class);
            startActivityForResult(intent, REQUEST_CODE_COUNT);
            return true;
        }
//       else if(id==R.id.action_search) {
//           startActivity(new Intent(this, ClotheSearchActivity.class));
//           return true;
//       }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            setData(0);
//        }
        if (requestCode == REQUEST_CODE_COUNT) {
            if (resultCode == RESULT_OK) {
                setData(0);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordi_main);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("코디 목록");
        springtv = findViewById(R.id.spring_coordi);
        summertv = findViewById(R.id.summer_coordi);
        falltv = findViewById(R.id.fall_coordi);
        wintertv = findViewById(R.id.winter_coordi);

        myGridView = (GridView) findViewById(R.id.CoordiMainGridView);

        springtv.setOnClickListener(this);
        summertv.setOnClickListener(this);
        falltv.setOnClickListener(this);
        wintertv.setOnClickListener(this);


        s = (Spinner) findViewById(R.id.arrange_spin);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Log.v("setdata","on item selected");
                Log.v("spinner select",position+"");
                setData(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
//        selectedSeasons.add("봄");

//        selectedSeasons.add("여름");
//        selectedSeasons.add("가을");
//        selectedSeasons.add("겨울");
        springtv.performClick();
        summertv.performClick();
        falltv.performClick();
        wintertv.performClick();
        //Log.v("setdata","on cre");
        // setData(0);
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ClotheDTO clothe = list.get(position).getID();
                Intent intent = new Intent(CoordiMainActivity.this, CoordiViewActivity.class);

                intent.putExtra("coordiID", adapter.getItem(position).getId());


                startActivityForResult(intent, REQUEST_CODE_COUNT);

            }

        });
    }


    private void setData(final int position) {
        final ArrayList<CoordiDTO> list = new ArrayList<>();
        Query queryList = db.collection("coordi");
        Log.v("seasons", selectedSeasons.toString());
        if(selectedSeasons.size()==0){
            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
            myGridView.setAdapter(adapter);
            return;
        }
        queryList = queryList.whereArrayContainsAny("seasons", selectedSeasons);

        if (position == 0) {
            queryList.orderBy("regDate", Query.Direction.DESCENDING)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                                CoordiDTO item = doc.toObject(CoordiDTO.class);
                                item.setId(doc.getId());
                                list.add(item);
                                Log.v("seasons:", "item name:" + item.getName() + "/" + item.getRegDate());
                            }
                            //Collections.sort(list);

                            adapter = new CoordiMainAdapter(CoordiMainActivity.this, list);
                            myGridView.setAdapter(adapter);
                        }
                    });
        } else if (position == 1) {

            //ArrayList<CoordiDTO> CoordiList = new DBUtil().getDatas("coordi", "rating", false);
            ArrayList<CoordiDTO> CoodiList;
            queryList.orderBy("rating", Query.Direction.ASCENDING)
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
                        }
                    });
            //adapter.setListCustom(CoordiList);

            //  showToast("CoordiList 별점 오름차순 출력중");
        } else if (position == 2) {

            //ArrayList<CoordiDTO> CoordiList = new DBUtil().getDatas("coordi", "rating", true);
            //adapter.setListCustom(CoordiList);
            queryList.orderBy("rating", Query.Direction.DESCENDING)
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
                        }
                    });
            //adapter.setListCustom(CoordiList);

            // showToast("CoordiList 별점 내림차순 출력중");
        } else if (position == 3) {

//            ArrayList<CoordiDTO> CoordiList = new DBUtil().getDatas("coordi", "count", false);
//            adapter.setListCustom(CoordiList);
            queryList.orderBy("count", Query.Direction.ASCENDING)
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
                        }
                    });
            //adapter.setListCustom(CoordiList);

            //showToast("CoordiList 횟수 내림차순 출력중");
        } else if (position == 4) {

//            ArrayList<CoordiDTO> CoordiList = new DBUtil().getDatas("coordi", "count", true);
//            adapter.setListCustom(CoordiList);
            queryList.orderBy("count", Query.Direction.DESCENDING)
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
                        }
                    });
            //adapter.setListCustom(CoordiList);

            // showToast("CoordiList 횟수 오름차순 출력중");

        }

    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onClick(View v) {
        if (v == springtv) {
            if (springtv.getCurrentTextColor() != Color.WHITE) {
                //spring=true;
                selectedSeasons.add("봄");
                //setData(s.getSelectedItemPosition());
                springtv.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                springtv.setTextColor(Color.WHITE);
                //springtv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

            } else {
                //spring=false;
                selectedSeasons.remove("봄");
                springtv.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                //springtv.setTextColor(Color.DKGRAY);
                //springtv.setLinkTextColor(getResources().getColor(R.color.colorPrimaryDark,null));
                springtv.setTextColor(Color.DKGRAY);
            }
        } else if (v == summertv) {
            if (summertv.getCurrentTextColor() != Color.WHITE) {
                //summer=true;
                selectedSeasons.add("여름");
                //setData(s.getSelectedItemPosition());
                summertv.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                //summertv.setLinkTextColor(getResources().getColor(R.color.colorPrimaryDark,null));
                summertv.setTextColor(Color.WHITE);
            } else {
                //summer=false;
                selectedSeasons.remove("여름");
                summertv.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                summertv.setTextColor(Color.DKGRAY);
            }
        } else if (v == falltv) {
            if (falltv.getCurrentTextColor() != Color.WHITE) {
                // fall=true;
                selectedSeasons.add("가을");
                // setData(s.getSelectedItemPosition());
                falltv.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                //falltv.setLinkTextColor(getResources().getColor(R.color.colorPrimaryDark,null));
                falltv.setTextColor(Color.WHITE);

            } else {
                //fall=false;
                selectedSeasons.remove("가을");
                falltv.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                falltv.setTextColor(Color.DKGRAY);
            }
        } else if (v == wintertv) {
            if (wintertv.getCurrentTextColor() != Color.WHITE) {
                //winter=true;
                selectedSeasons.add("겨울");
                setData(s.getSelectedItemPosition());
                wintertv.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                //wintertv.setLinkTextColor(getResources().getColor(R.color.colorPrimaryDark,null));showToast("winter category");
                wintertv.setTextColor(Color.WHITE);
            } else {
                //winter=false;
                selectedSeasons.remove("겨울");
                wintertv.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                wintertv.setTextColor(Color.DKGRAY);
            }

        }
        setData(s.getSelectedItemPosition());

    }

    public void clickTab(View v) {
        ImageView[] img = new ImageView[3];
        img[0] = findViewById(R.id.tab_closet);
        img[1] = findViewById(R.id.tab_coordi);
        img[2] = findViewById(R.id.tab_analysis);

        if (v == img[0]) {
            Intent intent = new Intent(this, ClosetActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } else if (v == img[1]) {
            Intent intent = new Intent(this, CoordiMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } else if (v == img[2]) {
            Intent intent = new Intent(this, StyleAnalysisActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }

}
