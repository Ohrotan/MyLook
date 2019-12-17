package com.ssu.mylook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ssu.mylook.R;
import com.ssu.mylook.dto.Custom2DTO;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FavoriteColorAdapter extends BaseAdapter {
    private ArrayList<Custom2DTO> listCustom = new ArrayList<>();
    Context context;
    ArrayList<String> clicked = new ArrayList<>();



    public FavoriteColorAdapter(Context context, ArrayList<Custom2DTO> list) {
        this.context=context;
        list.addAll(list);
        this.listCustom=list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        FavoriteColorAdapter.CustomViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.favorite_color_item, null);

            holder = new CustomViewHolder();
            holder.textRank= (TextView)convertView.findViewById(R.id.color_rank);
            holder.textTitle = (TextView) convertView.findViewById(R.id.color_title);
            holder.textContent = (TextView) convertView.findViewById(R.id.color_number);


            convertView.setTag(holder);

        } else {
            holder = (FavoriteColorAdapter.CustomViewHolder) convertView.getTag();
        }

        Custom2DTO dto = listCustom.get(position);
        holder.textContent.setText(Integer.toString(dto.getCount())+"회"); //색깔횟수
        holder.textTitle.setText(dto.getField()); //색깔이름
        holder.textRank.setText(Integer.toString(position+1));

        return convertView;
    }

    class CustomViewHolder {
        TextView textContent;
        TextView textTitle;
        TextView textRank;
    }

}
