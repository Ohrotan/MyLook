package com.ssu.mylook;

import androidx.annotation.NonNull;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ssu.mylook.dto.ClotheDTO;
import com.ssu.mylook.util.DBUtil;

public class ClotheViewActivity extends ClotheRegisterActivity implements View.OnClickListener {
    RelativeLayout edit_clothe_info;
    RelativeLayout delete_clothe;
    ImageView view_clothe_img;
    TextView view_name;
    TextView view_seasons;
    TextView view_memo;
    TextView view_sort;
    String seasons="";
    String clotheID;
    private static final String TAG="MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothe_view);
        getSupportActionBar().setTitle("옷 조회");

        view_clothe_img=findViewById(R.id.clothe_view_photo);
        edit_clothe_info=findViewById(R.id.edit_btn);
        delete_clothe=findViewById(R.id.remove_btn);
        view_name=findViewById(R.id.clothe_view_title);
        view_seasons=findViewById(R.id.clothe_view_season);
        view_memo=findViewById(R.id.memo_content);
        view_sort=findViewById(R.id.clothe_view_sort);


        edit_clothe_info.setOnClickListener(this);
        delete_clothe.setOnClickListener(this);

        //아직 인텐트로 아이디 받아오기 전
        clotheID = getIntent().getStringExtra("coltheID");
        if (clotheID == null) {
            clotheID = "bjJwiHkTuC8SP7341iuF";
        }
        clotheID = "bjJwiHkTuC8SP7341iuF";

        getData(clotheID);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v==edit_clothe_info)
        {
            //.update()
            intent = new Intent(this,ClotheEditActivity.class);
            startActivity(intent);
        }
        else if(v==delete_clothe)
        {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("clothes").document(clotheID)
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully deleted!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error deleting document", e);
                        }
                    });
            intent=new Intent(this,ClosetActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();//저장
        }
    }


    public void setField(ClotheDTO result) {
        new DBUtil().setImageViewFromDB(this,view_clothe_img,result.getIMAGE());
        view_name.setText(result.getTTL());
        view_memo.setText(result.getMEMO());
        view_sort.setText(result.getSORT());
        for (String s : result.getSEASON()) {
            switch (s) {
                case "봄":
                   seasons="봄";
                    break;
                case "여름":
                    seasons+=" ,여름";
                    break;
                case "가을":
                    seasons+=" ,가을";
                    break;
                case "겨울":
                    seasons+=" ,겨울";
                    break;
                default:
                    break;
            }
        }
        view_seasons.setText(seasons);


    }
    public void getData(String id) {
        final String TAG = "clothe database";
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("clothes").document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                result = document.toObject(ClotheDTO.class);
                                Log.v(TAG, "DocumentSnapshot data: " + result.toString());
                                setField(result);
                            } else {
                                Log.v(TAG, "No such document");
                            }
                        } else {
                            Log.v(TAG, "get failed with ", task.getException());
                        }
                    }
                });

    }
}