package com.ssu.mylook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ClotheViewActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout edit_clothe_info;
    RelativeLayout delete_clothe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothe_view);

        edit_clothe_info=findViewById(R.id.edit_btn);
        delete_clothe=findViewById(R.id.remove_btn);

        edit_clothe_info.setOnClickListener(this);
        delete_clothe.setOnClickListener(this);
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
            //.remove()
            //제대로 작동함
            //이전 액티비티가 나왔으면 좋겠는데 아직 공부를 덜해서
            intent=new Intent(this,ClosetActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();//저장
        }
    }
}
