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
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.ClosetViewAdapter;
import com.ssu.mylook.dto.ClotheDTO;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ClosetActivity extends AppCompatActivity implements View.OnClickListener {

    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;

    TextView textA;
    TextView textB;
    TextView textC;
    TextView textD;
    TextView textE;
    ArrayList<String> selectedSeasons = new ArrayList<>();
    ArrayList<String> selectedCates = new ArrayList<>();
    GridView gridView;
    ClosetViewAdapter closetViewAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        Intent intent = new Intent();
        gridView = (GridView) findViewById(R.id.gridView);
        text1 = (TextView) findViewById(R.id.upper_spring);
        text2 = (TextView) findViewById(R.id.upper_summer);
        text3 = (TextView) findViewById(R.id.upper_fall);
        text4 = (TextView) findViewById(R.id.upper_winter);

        textA = (TextView) findViewById(R.id.upper_top);
        textB = (TextView) findViewById(R.id.upper_bottom);
        textC = (TextView) findViewById(R.id.upper_hat);
        textD = (TextView) findViewById(R.id.upper_shoes);
        textE = (TextView) findViewById(R.id.upper_etc);

        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);
        text4.setOnClickListener(this);

        textA.setOnClickListener(this);
        textB.setOnClickListener(this);
        textC.setOnClickListener(this);
        textD.setOnClickListener(this);
        textE.setOnClickListener(this);


        //gridView.setAdapter(closetViewAdapter);
        setDataAll();


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ClotheDTO clothe = list.get(position).getID();
                Intent intent = new Intent(ClosetActivity.this, ClotheViewActivity.class);

                intent.putExtra("clotheID", closetViewAdapter.getItem(position).getId());

                //Bundle bundle = new Bundle();

                startActivity(intent);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivityForResult(new Intent(this, ClotheRegisterActivity.class), 1);
                return true;

            case R.id.action_search:
                startActivity(new Intent(this, ClotheSearchActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //String resultMsg=data.getStringExtra("clotheID");
            setData();
        }
    }

    private void setDataAll() {
        selectedCates.add("상의");
        selectedCates.add("하의");
        selectedCates.add("모자");
        selectedCates.add("신발");
        selectedCates.add("기타");

        selectedSeasons.add("봄");
        selectedSeasons.add("여름");
        selectedSeasons.add("가을");
        selectedSeasons.add("겨울");

        textA.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
        textA.setTextColor(Color.WHITE);
        textB.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
        textB.setTextColor(Color.WHITE);
        textC.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
        textC.setTextColor(Color.WHITE);
        textD.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
        textD.setTextColor(Color.WHITE);
        textE.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
        textE.setTextColor(Color.WHITE);

        text1.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
        text1.setTextColor(Color.WHITE);
        text2.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
        text2.setTextColor(Color.WHITE);
        text3.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
        text3.setTextColor(Color.WHITE);
        text4.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
        text4.setTextColor(Color.WHITE);

        final String TAG = "clothe database";
        db.collection("clothes")
                .orderBy("regDate", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<ClotheDTO> list = new ArrayList<>();
                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                            ClotheDTO item = doc.toObject(ClotheDTO.class);
                            item.setId(doc.getId());
                            list.add(item);

                        }

                        closetViewAdapter = new ClosetViewAdapter(ClosetActivity.this, list);
                        gridView.setAdapter(closetViewAdapter);
                    }
                });


    }

    private void setData() {
        final String TAG = "clothe database";
        Log.v("seasons", selectedSeasons.toString());
        Log.v("seasons", selectedCates.toString());
        db.collection("clothes")
                .whereArrayContainsAny("seasons", selectedSeasons)
                .orderBy("regDate", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<ClotheDTO> list = new ArrayList<>();
                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                            ClotheDTO item = doc.toObject(ClotheDTO.class);
                            if (selectedCates.contains(item.getSort())) {
                                item.setId(doc.getId());
                                list.add(item);
                            }
                        }

                        closetViewAdapter = new ClosetViewAdapter(ClosetActivity.this, list);
                        gridView.setAdapter(closetViewAdapter);
                    }
                });


    }

    @Override
    public void onClick(View v) {

        if (v == textA) {
            //색깔 바뀌도록
            if (textA.getCurrentTextColor() != Color.WHITE) {
                selectedCates.add("상의");
                textA.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                textA.setTextColor(Color.WHITE);
            } else {
                selectedCates.remove("상의");
                textA.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                textA.setTextColor(Color.DKGRAY);
            }
        } else if (v == textB) {
            if (textB.getCurrentTextColor() != Color.WHITE) {
                selectedCates.add("하의");
                textB.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                textB.setTextColor(Color.WHITE);
            } else {
                selectedCates.remove("하의");
                textB.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                textB.setTextColor(Color.DKGRAY);
            }
        } else if (v == textC) {
            if (textC.getCurrentTextColor() != Color.WHITE) {
                selectedCates.add("모자");
                textC.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                textC.setTextColor(Color.WHITE);
            } else {
                selectedCates.remove("모자");
                textC.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                textC.setTextColor(Color.DKGRAY);
            }
        } else if (v == textD) {
            //색깔 바뀌도록
            if (textD.getCurrentTextColor() != Color.WHITE) {
                selectedCates.add("신발");
                textD.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                textD.setTextColor(Color.WHITE);
            } else {
                selectedCates.remove("신발");
                textD.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                textD.setTextColor(Color.DKGRAY);
            }
        } else if (v == textE) {
            if (textE.getCurrentTextColor() != Color.WHITE) {
                selectedCates.add("기타");
                textE.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                textE.setTextColor(Color.WHITE);
            } else {
                selectedCates.remove("기타");
                textE.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                textE.setTextColor(Color.DKGRAY);
            }
        } else if (v == text1) {
            if (text1.getCurrentTextColor() != Color.WHITE) {
                selectedSeasons.add("봄");
                text1.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                text1.setTextColor(Color.WHITE);
            } else {
                selectedSeasons.remove("봄");
                text1.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                text1.setTextColor(Color.DKGRAY);
            }
        } else if (v == text2) {
            if (text2.getCurrentTextColor() != Color.WHITE) {
                selectedSeasons.add("여름");
                text2.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                text2.setTextColor(Color.WHITE);
            } else {
                selectedSeasons.remove("여름");
                text2.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                text2.setTextColor(Color.DKGRAY);
            }

        } else if (v == text3) {
            if (text3.getCurrentTextColor() != Color.WHITE) {
                selectedSeasons.add("가을");
                text3.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                text3.setTextColor(Color.WHITE);
            } else {
                selectedSeasons.remove("가을");
                text3.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                text3.setTextColor(Color.DKGRAY);
            }
        } else if (v == text4) {
            if (text4.getCurrentTextColor() != Color.WHITE) {
                selectedSeasons.add("겨울");
                text4.setBackground(getResources().getDrawable(R.drawable.colorButtonClicked, null));
                text4.setTextColor(Color.WHITE);
            } else {
                selectedSeasons.remove("겨울");
                text4.setBackground(getResources().getDrawable(R.drawable.colorButtonNotClick, null));
                text4.setTextColor(Color.DKGRAY);
            }
        }
        setData();
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