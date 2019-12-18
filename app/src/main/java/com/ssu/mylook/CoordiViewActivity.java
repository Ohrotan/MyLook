package com.ssu.mylook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ssu.mylook.adapter.CoordiViewAdapter;
import com.ssu.mylook.dto.CustomDTO;
import com.ssu.mylook.util.DBUtil;

public class CoordiViewActivity extends AppCompatActivity implements View.OnClickListener {
    private CoordiViewAdapter adapter;
    private ListView myListView;
    private View header;

    //코디 메인에서 누른 코디가 출력되도록 하는 함수, id값 받아와야 함
    String coordiID;
    ImageView coordi_view_img;
    TextView coordi_view_name;
    TextView coordi_view_tag;
    RatingBar coordi_view_rating;
    TextView coordi_view_seasons;
    TextView coordi_view_count;
    String seasons="";

    CustomDTO results = new CustomDTO();

    //+,-버튼 클릭시 코디 입은횟수 증감
    ImageButton minusBtn;
    TextView count;
    ImageButton plusBtn;
    static int dbcount;
    //해당 코디 수정/삭제 버튼
    RelativeLayout editBtn; //수정 : 해당 코디 (이미지 수정/내용정보 수정) 액티비티로 이동
    RelativeLayout removeBtn;   //삭제 : DB 에서 바로 삭제
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordi_view);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("코디 상세");

        myListView = findViewById(R.id.CoordiListView);
        //코디 횟수 증가
        header = getLayoutInflater().inflate(R.layout.coordi_view_item, null, false);
        minusBtn = (ImageButton)header.findViewById(R.id.minusCount);
        plusBtn = (ImageButton)header.findViewById(R.id.plusCount);
//        mButton.setOnClickListener(new View.OnClickListener() {
////            @Override  public void onClick(View v) {
////                 mList.addFooterView(header);
////                 }
////                 });


        //minusBtn = findViewById(R.id.minusCount);
        count = findViewById(R.id.coordi_item_count);
       // plusBtn = findViewById(R.id.plusCount);

        //수정하기, 삭제하기 버튼
        editBtn = (RelativeLayout) findViewById(R.id.edit_btn);
        removeBtn = (RelativeLayout) findViewById(R.id.remove_btn);

        //id얻기
        coordiID=getIntent().getStringExtra("coordiID");
        coordi_view_img=findViewById(R.id.coordi_view_img);
        coordi_view_name=findViewById(R.id.coordi_view_title);
        coordi_view_rating=findViewById(R.id.coordi_view_rating);
        coordi_view_tag=findViewById(R.id.coordi_view_tag);
        coordi_view_seasons=findViewById(R.id.coordi_view_weather);
        coordi_view_count=findViewById(R.id.coordi_item_count);

        editBtn.setOnClickListener(this);
        removeBtn.setOnClickListener(this);
        minusBtn.setOnClickListener(this);
        plusBtn.setOnClickListener(this);

        coordiID=getIntent().getStringExtra("coordiID");
        getData(coordiID);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.edit_btn:
                /* <구현해야 함>
                 * 수정하기 : 해당 코디의 수정하기로 넘어감
                 * 코디 클릭시 나오는 코디ID 값으로 수정하기로 넘어가야 됨
                 *  */
                intent = new Intent(this,CoordiInfoEditActivity.class);
                startActivity(intent);
                showToast("수정하기 버튼 클릭");
                break;
            case R.id.remove_btn :
                /* <구현해야 함>
                 * 삭제하기 : 삭제하시겠습니까? 출력 후 , '네' 누르면
                 * 코디 클릭시 나오는 코디ID 값을 이용해 삭제함
                 *  */
                deleteDoc();
                showToast("삭제하기 버튼 클릭");
                break;

            case R.id.minusCount :
                //DB에서 이 count 값 가져오기, 변경시킨 값 다시 DB에 저장하기 구현해야함
                //int printCount = Integer.parseInt(count.getText().toString());
//                int printCount=dbcount;
//                printCount--;
//                count.setText(""+printCount);
                showToast("- 버튼 클릭 : "+dbcount--);

                //final String TAG="";
                DocumentReference countMinusRef = db.collection("coordi").document(coordiID);
                countMinusRef
                        .update("count", dbcount--)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG", "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error updating document", e);
                            }
                        });
                break;
            case R.id.plusCount:
                //DB에서 이 count 값 가져오기, 변경시킨 값 다시 DB에 저장하기 구현해야함
//                printCount = Integer.parseInt(count.getText().toString());
//                printCount++;
//                count.setText(""+printCount);
                showToast("+ 버튼 클릭 : "+dbcount++);
                DocumentReference countPlusRef = db.collection("coordi").document(coordiID);
                countPlusRef
                        .update("count", dbcount++)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG", "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error updating document", e);
                            }
                        });
                break;
        }

    }



    private void showToast(String message){
        Toast toast=Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void deleteDoc() {
        db.collection("coordi").document(coordiID)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("jungeun", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("jungeun", "Error deleting document", e);
                    }
                });
    }
    public void setField(CustomDTO result) {
        new DBUtil().setImageViewFromDB(this, coordi_view_img, result.getImg());
        coordi_view_name.setText(result.getName());//name
        coordi_view_rating.setRating(result.getRating()); //rating
        coordi_view_tag.setText(result.getTag());//tag, count
        coordi_view_count.setText(result.getCount());
        dbcount=result.getCount();
        for (String s : result.getSeasons()) {
            switch (s) {
                case "봄":
                    seasons = "봄";
                    break;
                case "여름":
                    seasons += " ,여름";
                    break;
                case "가을":
                    seasons += " ,가을";
                    break;
                case "겨울":
                    seasons += " ,겨울";
                    break;
                default:
                    break;
            }
        }
        coordi_view_seasons.setText(seasons);
    }

    private void getData(String id) {

        final String TAG = "coordi database";
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("coordi").document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                results = document.toObject(CustomDTO.class);
                                Log.v(TAG, "DocumentSnapshot data: " + results.toString());
                                setField(results);
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
