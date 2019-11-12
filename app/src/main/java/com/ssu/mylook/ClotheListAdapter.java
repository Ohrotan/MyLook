package com.ssu.mylook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ClotheListAdapter extends BaseAdapter {
    Context context;
    ArrayList<ClotheListItem> list;
    ViewHolder viewholder;

    public ClotheListAdapter(Context context, ArrayList<ClotheListItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_clothe,null);
            viewholder = new ViewHolder();
            viewholder.clothe_img = (ImageView)convertView.findViewById(R.id.clothe_img);
            viewholder.clothe_name = (TextView)convertView.findViewById(R.id.clothe_name);
            viewholder.coordi_clothe_checkBox = (CheckBox) convertView.findViewById(R.id.coordi_clothe_checkBox);

            convertView.setTag(viewholder);
        }else{
            viewholder = (ViewHolder)convertView.getTag();
        }


        viewholder.clothe_name.setText(list.get(position).getClothe_name());
        viewholder.clothe_img.setImageDrawable(context.getDrawable(list.get(position).getClothe_img()));
        //viewholder.coordi_clothe_checkBox.setId(list.get(position).getClothe_id());


    return convertView;}

    class ViewHolder{

        ImageView clothe_img;
        TextView clothe_name;
        CheckBox coordi_clothe_checkBox;

    }
}
