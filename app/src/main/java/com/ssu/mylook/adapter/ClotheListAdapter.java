package com.ssu.mylook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssu.mylook.R;
import com.ssu.mylook.dto.ClotheListItem;
import com.ssu.mylook.util.DBUtil;

import java.util.ArrayList;

public class ClotheListAdapter extends BaseAdapter {
    Context context;
    ArrayList<ClotheListItem> list;
    ViewHolder viewholder;

    ArrayList<String> clickedImgs = new ArrayList<>();
    ArrayList<String> clickedIds = new ArrayList<>();

    public ClotheListAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
       /*
        list.add(new ClotheListItem("원피스", R.drawable.clothe1, "clothe-1"));
        list.add(new ClotheListItem("테니스 스커트", R.drawable.clothe2, "clothe-2"));
        list.add(new ClotheListItem("블라우스", R.drawable.clothe3, "clothe-3"));
        list.add(new ClotheListItem("검정 구두", R.drawable.shoes1, "clothe-4"));
        list.add(new ClotheListItem("원피스", R.drawable.clothe1, "clothe-5"));
        list.add(new ClotheListItem("테니스 스커트", R.drawable.clothe2, "clothe-6"));
        list.add(new ClotheListItem("블라우스", R.drawable.clothe3, "clothe-7"));
        list.add(new ClotheListItem("검정 구두", R.drawable.shoes1, "clothe-8"));

        */
    }

    public ClotheListAdapter(Context context, ArrayList<ClotheListItem> list) {
        this.context = context;
        //list.addAll(list);
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

    public String getItemImg(int position) {
        return list.get(position).getImage();
    }

    public ArrayList<String> getClickedImgs() {
        return clickedImgs;
    }

    public void setClickedImgs(ArrayList<String> clicked) {
        this.clickedImgs = clicked;
    }

    public ArrayList<String> getClickedIds() {
        return clickedIds;
    }

    public void setClickedIds(ArrayList<String> clicked) {
        this.clickedIds = clicked;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_clothe, null);
            viewholder = new ViewHolder();
            viewholder.clothe_img = (ImageView) convertView.findViewById(R.id.clothe_img);
            viewholder.clothe_name = (TextView) convertView.findViewById(R.id.clothe_name);
            viewholder.coordi_clothe_checkBox = (CheckBox) convertView.findViewById(R.id.coordi_clothe_checkBox);

            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        viewholder.coordi_clothe_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    clickedImgs.add(list.get(position).getImage());
                    clickedIds.add(list.get(position).getId());
                } else {
                    clickedImgs.remove(list.get(position).getImage());
                    clickedIds.remove(list.get(position).getId());
                }
                //  Toast.makeText(context,""+list.get(position).getImage(),Toast.LENGTH_SHORT).show();
            }

        });

        viewholder.clothe_name.setText(list.get(position).getName());
        new DBUtil().setImageViewFromDB(context, viewholder.clothe_img, list.get(position).getImage());

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"click"+position,Toast.LENGTH_SHORT).show();
//
//                if (viewholder.coordi_clothe_checkBox.isChecked()) {
//                    viewholder.coordi_clothe_checkBox.setChecked(false);
//                    clicked.remove(list.get(position).getImage());
//                } else {
//                    Toast.makeText(context,"unclick"+list.get(position).getImage(),Toast.LENGTH_SHORT).show();
//                    viewholder.coordi_clothe_checkBox.setChecked(true);
//
//
//
//                }
//
//            }
//        });
        return convertView;
    }


}

class ViewHolder {

    ImageView clothe_img;
    TextView clothe_name;
    CheckBox coordi_clothe_checkBox;

}
