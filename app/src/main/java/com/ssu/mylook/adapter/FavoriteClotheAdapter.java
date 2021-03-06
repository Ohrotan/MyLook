package com.ssu.mylook.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssu.mylook.R;
import com.ssu.mylook.dto.FavorDTO;
import com.ssu.mylook.util.DBUtil;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class FavoriteClotheAdapter extends BaseAdapter {
    private ArrayList<FavorDTO> listCustom = new ArrayList<>();
    Context context;
    ArrayList<String> clicked = new ArrayList<>();


    public FavoriteClotheAdapter(Context context) {
        this.context = context;
        listCustom = new ArrayList<>();
    }

    public FavoriteClotheAdapter(Context context, ArrayList<FavorDTO> list) {
        this.context = context;
        this.listCustom = list;
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

    // 실제로 Item이 보여지는 부분
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CustomViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.favorite_clothe_item, null);

            holder = new CustomViewHolder();
            holder.textRank = (TextView) convertView.findViewById(R.id.item_ranking);
            holder.imageView = (ImageView) convertView.findViewById(R.id.item_image);
            holder.textTitle = (TextView) convertView.findViewById(R.id.item_title);
            holder.textContent = (TextView) convertView.findViewById(R.id.item_number);


            convertView.setTag(holder);

        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        FavorDTO dto = listCustom.get(position);

        //holder.textRank.setText(dto.getRank());
        //holder.imageView.setImageResource(dto.getResId());
        holder.textTitle.setText(dto.getTitle());
        new DBUtil().setImageViewFromDB(context, holder.imageView, dto.getImage());
        holder.textRank.setText((position + 1) + "");
        holder.textContent.setText(dto.getCount() + " 회");
//        holder.textRank.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), (position+1)+"번째", Toast.LENGTH_SHORT).show();
//            }
//        });

        return convertView;
    }

    class CustomViewHolder {
        TextView textContent;
        ImageView imageView;
        TextView textTitle;
        TextView textRank;
    }

    // FavoriteClotheActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(FavorDTO dto) {
        listCustom.add(dto);
    }
}