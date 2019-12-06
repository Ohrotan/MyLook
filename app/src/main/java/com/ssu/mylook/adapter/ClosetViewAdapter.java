package com.ssu.mylook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssu.mylook.ClotheSearchLayout;
import com.ssu.mylook.R;
import com.ssu.mylook.dto.ClotheItem;

import java.util.ArrayList;

public class ClosetViewAdapter extends BaseAdapter {
    ArrayList<ClotheItem> items ;
    Context context;

    public ClosetViewAdapter(Context context)
    {
        this.items=new ArrayList<>();
        this.context=context;
    }

    public ClosetViewAdapter(Context context, ArrayList<ClotheItem> items) {
        this.items = items;
        items.addAll(items);
        this.context = context;
    }

    class ViewHolder {
        ImageView clothe_img;
        TextView clothe_title;
    }

    @Override
    public int getCount() {
        return items.size();
    }


    public void addItem(ClotheItem clotheItem) {

        items.add(clotheItem);
    }


    @Override
    public ClotheItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ClosetViewAdapter.ViewHolder holder;
        //항목 레이아웃 초기화
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.closet_item, parent, false);
            holder = new ClosetViewAdapter.ViewHolder();
            holder.clothe_img = (ImageView) convertView.findViewById(R.id.closet_imageview);
            holder.clothe_title = (TextView) convertView.findViewById(R.id.closet_textview);

            convertView.setTag(holder);
        }
            holder = (ClosetViewAdapter.ViewHolder) convertView.getTag();

        return convertView;
    }
}