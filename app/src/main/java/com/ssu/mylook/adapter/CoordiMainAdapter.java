package com.ssu.mylook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssu.mylook.R;
import com.ssu.mylook.dto.CustomDTO;

import java.util.ArrayList;

public class CoordiMainAdapter extends BaseAdapter {

    Context context;
    private ArrayList<CustomDTO> listCustom;
    ArrayList<String> clicked = new ArrayList<>();

    public CoordiMainAdapter(Context context){
        this.context = context;
        listCustom = new ArrayList<>();
    }
    public CoordiMainAdapter(Context context, ArrayList<CustomDTO> list) {
        this.context = context;
        list.addAll(list);
        this.listCustom=list;

    }

    public ArrayList<CustomDTO> getListCustom() {
        return listCustom;
    }
    public void setListCustom(ArrayList<CustomDTO> listCustom) {
        this.listCustom = listCustom;
    }

    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return listCustom.size();
    }

    // 하나의 Item(ImageView 1, TextView 3)
    @Override
    public Object getItem(int position) {
        return listCustom.get(position);
    }

    // Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coordi_main_item,null,false);

            holder = new CoordiMainAdapter.CustomViewHolder();
            holder.textCount=(TextView)convertView.findViewById(R.id.coordi_item_count);
            holder.imageView = (ImageView) convertView.findViewById(R.id.coordi_item_img);
            holder.textTitle = (TextView) convertView.findViewById(R.id.coordi_item_title);
            holder.textCount = (TextView) convertView.findViewById(R.id.coordi_item_count);
            holder.textRank = (TextView) convertView.findViewById(R.id.coordi_item_rating);

            convertView.setTag(holder);

        } else {
            holder = (CoordiMainAdapter.CustomViewHolder) convertView.getTag();
        }

        CustomDTO dto = listCustom.get(position);

        holder.textRank.setText(dto.getRank());
       // holder.imageView.setImageResource(dto.getResId());
        holder.textTitle.setText(dto.getTitle());
        holder.textCount.setText(dto.getCount());
        holder.textRank.setText(dto.getRank());


        return convertView;
    }

    class CustomViewHolder {
        TextView textCount;
        ImageView imageView;
        TextView textTitle;
        TextView textRank;
    }

    // FavoriteClotheActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(CustomDTO dto) {
        listCustom.add(dto);
    }
}


