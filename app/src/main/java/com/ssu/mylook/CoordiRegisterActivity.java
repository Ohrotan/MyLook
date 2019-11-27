package com.ssu.mylook;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CoordiRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    final String DEBUG_TAG = "img drag";
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
            ConnectDatabase db = new ConnectDatabase();
            db.uploadImage(getBitmapFromView(coordi_v),"coordi1");
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
                            img.setImageResource(R.drawable.clothe3);
                            img.setOnTouchListener(new MyDragListener());

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
    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

}

class MyDragListener implements View.OnTouchListener {

    private float xCoOrdinate, yCoOrdinate;

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                xCoOrdinate = view.getX() - event.getRawX();
                yCoOrdinate = view.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                break;
            default:
                return false;
        }
        return true;
    }
}

