package com.ssu.mylook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ssu.mylook.dto.ClotheItem;

import java.io.ByteArrayOutputStream;

public class ItemViewerActivity extends LinearLayout {
    TextView textView;
    ImageView imageView;

    Bitmap decodedBitmap;
    String encodedBitmap;
    private ClotheItem clotheItem;

    public ItemViewerActivity(Context context){
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.closet_item,this,true);

        textView=(TextView)findViewById(R.id.closet_textview);
        imageView=(ImageView)findViewById(R.id.closet_imageview);
    }

    public void setItem(ClotheItem clotheItem){

        textView.setText(clotheItem.getTitle());
        encodedBitmap=clotheItem.getBitmap();

        byte[] decodedByteArray = Base64.decode(encodedBitmap, Base64.DEFAULT);
        decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);

        imageView.setImageBitmap(decodedBitmap);
    }

}
