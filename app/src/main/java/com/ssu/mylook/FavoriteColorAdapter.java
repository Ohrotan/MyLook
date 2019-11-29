package com.ssu.mylook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoriteColorAdapter extends BaseAdapter {
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
        FavoriteColorAdapter.CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_color_item,null,false);

            holder = new CustomViewHolder();
            holder.textRank= (TextView)convertView.findViewById(R.id.color_rank);
            holder.textTitle = (TextView) convertView.findViewById(R.id.color_title);
            holder.textContent = (TextView) convertView.findViewById(R.id.color_number);


            convertView.setTag(holder);

        } else {
            holder = (FavoriteColorAdapter.CustomViewHolder) convertView.getTag();
        }

        CustomDTO dto = listCustom.get(position);

        holder.textRank.setText(dto.getRank());
        holder.textTitle.setText(dto.getTitle());
        holder.textContent.setText(dto.getContent());

        return convertView;
    }

    class CustomViewHolder {
        TextView textContent;
        TextView textTitle;
        TextView textRank;
    }

    // FavoriteTagActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(CustomDTO dto) {
        listCustom.add(dto);
    }
}
