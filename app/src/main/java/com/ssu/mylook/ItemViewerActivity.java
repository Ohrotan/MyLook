package com.ssu.mylook;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ssu.mylook.ClotheItem;
import com.ssu.mylook.R;

public class ItemViewerActivity extends LinearLayout {
    TextView textView;
    ImageView imageView;

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
        imageView.setImageResource(clotheItem.getImage());
    }
}
