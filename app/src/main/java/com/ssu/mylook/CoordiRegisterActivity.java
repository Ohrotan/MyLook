package com.ssu.mylook;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CoordiRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    FrameLayout coordi_v;

    Button clothe_add_btn;

    Button cancel_btn;
    Button next_btn;

    //옷 추가 레이아웃
    AlertDialog dialog;
    GridView listView;
    ClotheListAdapter clotheListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("코디 등록");

        setContentView(R.layout.activity_coordi_register);

        coordi_v = findViewById(R.id.coordi_view);

        clothe_add_btn = findViewById(R.id.clothe_add_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        next_btn = findViewById(R.id.next_btn);

        //옷 추가 레이아웃
        clotheListAdapter = new ClotheListAdapter(this);

    }

    public void clickFilters(View v) {
        Button btn = (Button) v;
        if (btn.getCurrentTextColor() != Color.WHITE) {
            btn.setBackground(getResources().getDrawable(R.drawable.purple_button, null));
            btn.setTextColor(Color.WHITE);
        } else {
            btn.setBackground(getResources().getDrawable(R.drawable.gray_button, null));
            btn.setTextColor(Color.DKGRAY);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == cancel_btn) {
            onBackPressed();
        } else if (v == next_btn) {

        } else if (v == clothe_add_btn) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            final LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View clotheAddPopup = inflater.inflate(R.layout.layout_clothe_add_search, null);

            builder.setView(clotheAddPopup);
            builder.setPositiveButton("다음", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialo, int which) {
                    View clotheAddPopup = inflater.inflate(R.layout.layout_clothe_add, null);
                    builder.setView(clotheAddPopup);

                    listView = clotheAddPopup.findViewById(R.id.coordi_clothe_result_view);
                    listView.setAdapter(clotheListAdapter);


                    builder.setPositiveButton("다음", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ImageView img = new ImageView(CoordiRegisterActivity.this);
                            img.setImageResource(R.drawable.clothe1);
                            img.setMaxHeight(100);
                            img.setOnDragListener(new View.OnDragListener() {
                                @Override
                                public boolean onDrag(View v, DragEvent event) {
                                    Log.v("drag","");
                                    switch (event.getAction()) {
                                        case DragEvent.ACTION_DRAG_STARTED:
                                            Log.v("drag","ACTION_DRAG_STARTED");
                                            return true;
                                        case DragEvent.ACTION_DRAG_ENTERED:
                                            Log.v("drag","ACTION_DRAG_ENTERED");
                                            return true;
                                        case DragEvent.ACTION_DRAG_EXITED:
                                            Log.v("drag","ACTION_DRAG_EXITED");
                                            return true;
                                        case DragEvent.ACTION_DROP:
                                            Log.v("drag","ACTION_DROP");
                                            //드래그를 떼었다 놓을 때
                                            View view = (View) event.getLocalState();//startDrag에서 3번째 항목을 가져옴
                                            ViewGroup parent = (ViewGroup) view.getParent();//원래 부모를 구함.(리니어레이아웃)
                                            parent.removeView(view);//뷰를 제거함

                                            LinearLayout newparent = (LinearLayout) v;//새로운 부모를 만들고(리니어레이아웃)
                                            newparent.addView(view);//드롭을 받은 부모에게 뷰를 추가함.
                                            view.setVisibility(View.VISIBLE);//보이게 함.
                                            return true;


                                        case DragEvent.ACTION_DRAG_ENDED:
                                            Log.v("drag","ACTION_ENDED");
                                            if (event.getResult() == false) {//드래그 종료시 처음 숨겼던 뷰를 다시 보이도록 한다.
                                                ((View) (event.getLocalState())).setVisibility(View.VISIBLE);
                                            }
                                            return true;
                                    }
                                    return true;

                                }
                            });
                            coordi_v.addView(img);
                        }
                    });
                    builder.setNegativeButton("취소", null);


                    dialog = builder.create();
                    dialog.show();

                }
            });

            builder.setNegativeButton("취소", null);


            dialog = builder.create();
            dialog.show();
        }
    }
}
