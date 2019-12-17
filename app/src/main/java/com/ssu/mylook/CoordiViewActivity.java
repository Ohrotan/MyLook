package com.ssu.mylook;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.adapter.CoordiViewAdapter;
import com.ssu.mylook.dto.CustomDTO;

import java.util.ArrayList;

public class CoordiViewActivity extends AppCompatActivity{
    private CoordiViewAdapter adapter;
    private ListView myListView;

    //코디 메인에서 누른 코디가 출력되도록 하는 함수, id값 받아와야 함


    //+,-버튼 클릭시 코디 입은횟수 증감
    ImageView minusBtn;
    TextView count;
    ImageView plusBtn;
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
        minusBtn = findViewById(R.id.minusCount);
        count = findViewById(R.id.coordi_item_count);
        plusBtn = findViewById(R.id.plusCount);

        //수정하기, 삭제하기 버튼
        editBtn = (RelativeLayout) findViewById(R.id.edit_btn);
        removeBtn = (RelativeLayout) findViewById(R.id.remove_btn);

        setData();
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.edit_btn:
                        /* <구현해야 함>
                        * 수정하기 : 해당 코디의 수정하기로 넘어감
                        * 코디 클릭시 나오는 코디ID 값으로 수정하기로 넘어가야 됨
                        *  */
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
                        int printCount = Integer.parseInt(count.getText().toString());
                        printCount--;
                        count.setText(""+printCount);
                        showToast("- 버튼 클릭 : "+printCount);
                        break;
                    case R.id.plusCount:
                        //DB에서 이 count 값 가져오기, 변경시킨 값 다시 DB에 저장하기 구현해야함
                        printCount = Integer.parseInt(count.getText().toString());
                        printCount++;
                        count.setText(""+printCount);
                        showToast("+ 버튼 클릭 : "+printCount);
                        break;
                }
            }
        };
        editBtn.setOnClickListener(clickListener);
        removeBtn.setOnClickListener(clickListener);
        minusBtn.setOnClickListener(clickListener);
        plusBtn.setOnClickListener(clickListener);

    }

    private void showToast(String message){
        Toast toast=Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

        private void deleteDoc() {
        db.collection("coordi").document("삭제할document이름여기에넣기")
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

    private void setData() {
        //id받아오는거 모름
        db.collection("coordi")
                .whereEqualTo("id", 0)
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
                        adapter = new CoordiViewAdapter(CoordiViewActivity.this, list);
                        myListView.setAdapter(adapter);
                    }
                });
        //count 개수 받아오기, 수정한 값 저장하기
//        DocumentReference docRef = db.collection("coordi").document("asdf");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                    } else {
//                        Log.d(TAG, "No such document");
//                    }
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });
    }
}
