package com.ssu.mylook;

import androidx.annotation.NonNull;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ssu.mylook.dto.ClotheDTO;
import com.ssu.mylook.util.DBUtil;

import java.util.ArrayList;

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


        clotheID=getIntent().getStringExtra("clotheID");

        if (clotheID == null) {
            clotheID = "IRv7OYfjuGzp8xSijrTZ";
        }

        //clotheID = "bjJwiHkTuC8SP7341iuF";

        getData(clotheID);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v==edit_clothe_info)
        {
            intent = new Intent(this,ClotheEditActivity.class);
            intent.putExtra("clotheID",clotheID);
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

            setResult(RESULT_OK,intent);
            startActivity(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            overridePendingTransition(0, 0);
            finish();//저장
        }
    }


    public void setField(ClotheDTO result) {
        int check=0;
        new DBUtil().setImageViewFromDB(this,view_clothe_img,result.getImage());
        view_name.setText(result.getTitle());
        view_memo.setText(result.getMemo());
        view_sort.setText(result.getSort());
        for (String s : result.getSeasons()) {
            switch (s) {
                case "봄":
                    check++;
                   seasons="봄 ";
                    break;
                case "여름":
                    if(check==0){
                    seasons+="여름 ";}
                    else
                        seasons+=", 여름";
                    check++;
                    break;
                case "가을":
                    if(check==0){
                        seasons+="가을 ";}
                    else
                        seasons+=", 가을";
                    check++;
                    break;
                case "겨울":
                    if(check==0){
                        seasons+="겨울 ";}
                    else
                        seasons+=", 겨울";
                    check++;
                default:
                    break;
            }
        }
        view_seasons.setText(seasons);


    }
    public void getData(String id) {
        final String TAG = "clothe database";
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Toast.makeText(getApplicationContext(),""+clotheID.toString(),Toast.LENGTH_LONG);
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