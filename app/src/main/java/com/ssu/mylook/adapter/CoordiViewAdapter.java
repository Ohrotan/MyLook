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

public class CoordiViewAdapter extends BaseAdapter {
    private ArrayList<CoordiDTO> listCustom = new ArrayList<>();
    Context context;
    ArrayList<String> clicked = new ArrayList<>();

    public CoordiViewAdapter(Context context){
        this.context = context;
        listCustom = new ArrayList<>();
    }

    public CoordiViewAdapter(Context context, ArrayList<CoordiDTO> list) {
        this.context=context;
        list.addAll(list);
        this.listCustom=list;
    }

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
        CustomViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_coordi_view, null);

            holder = new CustomViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.coordi_view_img);
            holder.textTitle = (TextView) convertView.findViewById(R.id.coordi_view_title);
            holder.textCount =(TextView)  convertView.findViewById(R.id.coordi_view_number);
            holder.textTag =(TextView)  convertView.findViewById(R.id.coordi_view_tag);
            holder.textSeasons=(TextView) convertView.findViewById(R.id.coordi_view_weather);
            holder.ratingBar=(RatingBar)convertView.findViewById(R.id.coordi_view_rating);
            convertView.setTag(holder);

        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        CoordiDTO dto = listCustom.get(position);

        // holder.imageView.setImageResource(dto.getResId());
        holder.textTitle.setText(dto.getName());
        new DBUtil().setImageViewFromDB(context,holder.imageView,dto.getImg());
        holder.textTag.setText("#"+dto.getTag());
        holder.textCount.setText(dto.getCount());
        holder.textSeasons.setText((CharSequence) dto.getSeasons());
        holder.ratingBar.setRating(dto.getRating());

        return convertView;
    }

    class CustomViewHolder {
        ImageView imageView;
        TextView textTitle;
        TextView textTag;
        TextView textSeasons;
        TextView textCount;
        RatingBar ratingBar;
    }
}
