package com.ssu.mylook;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.dto.CustomDTO;
public class JungEunDBTest extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jung_eun_dbtest);

        Button selectDocBtn=(Button)findViewById(R.id.seldatabtn);
        selectDocBtn.setOnClickListener(this);

        Button whereBtn=(Button)findViewById(R.id.wheredatabtn);
        whereBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.seldatabtn:
                selectDoc();
                break;

            case R.id.wheredatabtn:
                selectwhereDoc();
                break;
        }
    }

    private void selectwhereDoc() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("coordi")
        .whereEqualTo("count",0)
        .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult())
                            {
                                Log.d("jungeun", document.getId()+"=>"+document.getData());
                                CustomDTO customDTO = document.toObject(CustomDTO.class);
                                Log.d("jungeun", "id = "+customDTO.getId());
                                Log.d("jungeun", "name = "+customDTO.getName());
                                Log.d("jungeun", "img = " + customDTO.getImg());
                                Log.d("jungeun","regDate = "+customDTO.getDate());
                               // Log.d("jungeun","seasons = "+customDTO.getSeason());
                                Log.d("jungeun","tag = "+customDTO.getTag());
                                Log.d("jungeun","count = "+customDTO.getCount());
                                Log.d("jungeun","rating = "+customDTO.getRating());
                            }
                        } else{
                            Log.d("jungeun", "Error getting documents: ",task.getException());
                        }
                    }
                });
    }

    private void selectDoc() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("coordi").document("jungeuntest");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>(){

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Log.d("jungeun","DocumentSnapshot data: "+document.getData());

                        CustomDTO customDTO = document.toObject(CustomDTO.class);
                        Log.d("jungeun", "name = "+customDTO.getName());
                        Log.d("jungeun", "img = " + customDTO.getImg());
                        Log.d("jungeun","regDate = "+customDTO.getDate());
                       // Log.d("jungeun","seasons = "+customDTO.getSeason());
                        Log.d("jungeun","tag = "+customDTO.getTag());
                        Log.d("jungeun","count = "+customDTO.getCount());
                        Log.d("jungeun","rating = "+customDTO.getRating());


                    } else{
                        Log.d("jungeun", "No such document");
                    }
                } else{
                    Log.d("jungeun", "get failed with ",task.getException());
                }
            }
        });
    }
}
