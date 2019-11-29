package com.ssu.mylook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class Closet_ListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Closet_ListViewItem> data;
    private int layout;

    public Closet_ListViewAdapter(Context context, int layout,ArrayList<Closet_ListViewItem> data){
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data=data;
        this.layout=layout;
    }

    @Override
    public int getCount() {return data.size();}

    @Override
    public String getItem(int position){return data.get(position).closet_getName();}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView=inflater.inflate(layout,parent,false);
        }

        Closet_ListViewItem closet_listViewItem=data.get(position);

        ImageView image=(ImageView)convertView.findViewById(R.id.closet_imageview);
        image.setImageResource(closet_listViewItem.closet_getImage());

        return convertView;
    }
}
