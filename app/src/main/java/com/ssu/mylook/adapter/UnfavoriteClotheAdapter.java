package com.ssu.mylook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssu.mylook.dto.CustomDTO;
import com.ssu.mylook.R;

import java.util.ArrayList;

public class UnfavoriteClotheAdapter extends BaseAdapter {
    private ArrayList<CustomDTO> listCustom = new ArrayList<>();

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

    // 실제로 Item이 보여지는 부분
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UnfavoriteClotheAdapter.CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.unfavorite_clothe_item,null,false);

            holder = new CustomViewHolder();
           // holder.imageView = (ImageView) convertView.findViewById(R.id.zero_item_img);
            holder.textTitle = (TextView) convertView.findViewById(R.id.zero_item_title);


            convertView.setTag(holder);

        } else {
            holder = (UnfavoriteClotheAdapter.CustomViewHolder) convertView.getTag();
        }

        CustomDTO dto = listCustom.get(position);

       // holder.imageView.setImageResource(dto.getResId());
        holder.textTitle.setText(dto.getName());

        return convertView;
    }

    class CustomViewHolder {
        ImageView imageView;
        TextView textTitle;
    }

    // FavoriteClotheActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(CustomDTO dto) {
        listCustom.add(dto);
    }
}
