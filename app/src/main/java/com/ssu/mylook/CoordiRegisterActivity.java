package com.ssu.mylook;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.ssu.mylook.adapter.ClotheListAdapter;
import com.ssu.mylook.util.DBUtil;

import java.util.UUID;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CoordiRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    final String DEBUG_TAG = "img drag";
    FrameLayout coordi_v;

    Button delete_btn;

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

        delete_btn = findViewById(R.id.delete_btn);
        coordi_v = findViewById(R.id.coordi_view);

        clothe_add_btn = findViewById(R.id.clothe_add_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        next_btn = findViewById(R.id.next_btn);

//test용
        ImageView img = new ImageView(CoordiRegisterActivity.this);
        img.setImageResource(R.drawable.clothe3);
        img.setOnTouchListener(new MyDragListener(getResources().getDrawable(R.drawable.red_button),
                delete_btn));
        coordi_v.addView(img);
//test용

        //옷 추가 레이아웃
        clotheListAdapter = new ClotheListAdapter(this);

        delete_btn.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                delete_btn.setBackgroundColor(Color.RED);
                return false;
            }

        });
        delete_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.v("trash", "delete btn up");
                }
                return false;
            }

        });

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
            String uniqueID = UUID.randomUUID().toString();
            new DBUtil().uploadImage(getBitmapFromView(coordi_v), uniqueID);
            Intent intent = new Intent(this, CoordiInfoRegisterActivity.class);
            intent.putExtra("imgId", uniqueID);

            startActivity(intent);
            finish();

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
                            img.setOnTouchListener(
                                    new MyDragListener(getResources().getDrawable(R.drawable.red_button),
                                            delete_btn));
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

    public Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        delete_btn.setVisibility(View.GONE);
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        delete_btn.setVisibility(View.VISIBLE);
        //return the bitmap

        return returnedBitmap;
    }

}

class MyDragListener implements View.OnTouchListener {

    private float xCoOrdinate, yCoOrdinate;
    private Button delete_btn;
    // FrameLayout ly;
    Drawable dr;
    Drawable org_dr;
    int[] del = {0, 0, 0, 0}; //x-start,x-end,y-start,y-end

    MyDragListener(Drawable dr, Button delete_btn) {
        super();
        //  this.ly = ly;
        this.dr = dr;
        org_dr = delete_btn.getBackground();
        this.delete_btn = delete_btn;

    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        delete_btn.getLocationOnScreen(del);
        del[2] = del[1];
        del[1] = del[0] + delete_btn.getWidth();
        del[3] = del[2] + delete_btn.getHeight();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                xCoOrdinate = view.getX() - event.getRawX();
                yCoOrdinate = view.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                if (event.getRawX() < del[1] && event.getRawX() > del[0]
                        && event.getRawY() < del[3] && event.getRawY() > del[2]) {
                    delete_btn.setBackground(dr);
                } else {
                    delete_btn.setBackground(org_dr);
                }
                break;
            case MotionEvent.ACTION_UP:

                Log.v("trash", "buton y" + del[1] + "");
                Log.v("trash", event.getRawX() + "/" + event.getRawY() + "/" + view.getHeight());
                Log.v("trash", "Button:" + del[0] + "~" + (del[0] + delete_btn.getWidth()) + "/"
                        + del[1] + "~" + (del[1] + delete_btn.getHeight()));
                if (event.getRawX() < del[1] && event.getRawX() > del[0]
                        && event.getRawY() < del[3] && event.getRawY() > del[2]) {
                    ((ViewGroup) view.getParent()).removeView(view);
                    delete_btn.setBackground(org_dr);
                }
                //delete_btn.setBackground(dr);
                break;

            default:
                return false;
        }
        return true;
    }

}

