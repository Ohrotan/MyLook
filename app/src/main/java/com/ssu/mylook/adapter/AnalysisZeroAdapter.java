package com.ssu.mylook.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssu.mylook.R;
import com.ssu.mylook.dto.AnalysisDTO;
import com.ssu.mylook.util.DBUtil;

import java.util.ArrayList;

public class AnalysisZeroAdapter extends BaseAdapter {
    private ArrayList<AnalysisDTO> listCustom = new ArrayList<>();
    Context context;
    ArrayList<String> clicked = new ArrayList<>();

    public AnalysisZeroAdapter(Context context){
        this.context = context;
        listCustom = new ArrayList<>();
    }

    public AnalysisZeroAdapter(Context context, ArrayList<AnalysisDTO> list) {
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
        AnalysisZeroAdapter.CustomViewHolder holder;
        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.style_analysis_item, null);

            holder = new AnalysisZeroAdapter.CustomViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.zero_clothes_img);
            holder.textTitle = (TextView) convertView.findViewById(R.id.zero_clothes_name);
            convertView.setTag(holder);

        } else {
            holder = (AnalysisZeroAdapter.CustomViewHolder) convertView.getTag();
        }

        AnalysisDTO dto = listCustom.get(position);

        // holder.imageView.setImageResource(dto.getResId());
        holder.textTitle.setText(dto.getName());
        new DBUtil().setImageViewFromDB(context,holder.imageView,dto.getImg());

        return convertView;
    }

    class CustomViewHolder {
        ImageView imageView;
        TextView textTitle;
    }
}
