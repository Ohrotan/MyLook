package com.ssu.mylook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ssu.mylook.R;
import com.ssu.mylook.dto.CoordiDTO;
import com.ssu.mylook.util.DBUtil;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class CoordiMainAdapter extends BaseAdapter {

    Context context;
    private ArrayList<CoordiDTO> listCustom;
    ArrayList<String> clicked = new ArrayList<>();

    public CoordiMainAdapter(Context context){
        this.context = context;
        listCustom = new ArrayList<>();
    }
    public CoordiMainAdapter(Context context, ArrayList<CoordiDTO> list) {
        this.context = context;
        this.listCustom=list;
    }

    public ArrayList<CoordiDTO> getListCustom() {
        return listCustom;
    }
    public void setListCustom(ArrayList<CoordiDTO> listCustom) {
        this.listCustom = listCustom;
    }

    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return listCustom.size();
    }

    // 하나의 Item(ImageView 1, TextView 3)
    @Override
    public CoordiDTO getItem(int position) {
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
            //convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coordi_main_item,null,false);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.coordi_main_item, null);

            holder = new CoordiMainAdapter.CustomViewHolder();
            holder.textCount=(TextView)convertView.findViewById(R.id.coordi_item_count);
            holder.imageView = (ImageView) convertView.findViewById(R.id.coordi_item_img);
            holder.textTitle = (TextView) convertView.findViewById(R.id.coordi_item_title);
            holder.textCount = (TextView) convertView.findViewById(R.id.coordi_item_count);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.coordi_item_rating);

            convertView.setTag(holder);

        } else {
            holder = (CoordiMainAdapter.CustomViewHolder) convertView.getTag();
        }

        CoordiDTO dto = listCustom.get(position);

        holder.ratingBar.setRating(dto.getRating());
       // holder.imageView.setImageResource(dto.getResId());
        new DBUtil().setImageViewFromDB(context,holder.imageView,dto.getImg());
        holder.textTitle.setText(dto.getName());
        holder.textCount.setText(dto.getCount()+"회 착용");
     //   holder.textRank.setText(dto.getRank());


        return convertView;
    }

    class CustomViewHolder {
        TextView textCount;
        ImageView imageView;
        TextView textTitle;
        RatingBar ratingBar;
    }

    // FavoriteClotheActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(CoordiDTO dto) {
        listCustom.add(dto);
    }
}


