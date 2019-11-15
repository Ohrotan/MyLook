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
    public ClotheListAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
        list.add(new ClotheListItem("원피스",R.drawable.clothe1,"clothe-1"));
        list.add(new ClotheListItem("테니스 스커트",R.drawable.clothe2,"clothe-2"));
        list.add(new ClotheListItem("블라우스",R.drawable.clothe3,"clothe-3"));
        list.add(new ClotheListItem("검정 구두",R.drawable.shoes1,"clothe-4"));
        list.add(new ClotheListItem("원피스",R.drawable.clothe1,"clothe-5"));
        list.add(new ClotheListItem("테니스 스커트",R.drawable.clothe2,"clothe-6"));
        list.add(new ClotheListItem("블라우스",R.drawable.clothe3,"clothe-7"));
        list.add(new ClotheListItem("검정 구두",R.drawable.shoes1,"clothe-8"));
    }
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
        viewholder.clothe_img.setImageResource(list.get(position).getClothe_img());

    return convertView;
    }

    class ViewHolder{

        ImageView clothe_img;
        TextView clothe_name;
        CheckBox coordi_clothe_checkBox;

    }
}
