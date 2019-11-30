package com.ssu.mylook;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class Closet_ListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    public ArrayList<Closet_ListViewItem> data;
    private int layout;

    Context context;

    public Closet_ListViewAdapter(Context context,int layout,ArrayList list){

            inflater=(LayoutInflater)context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            this.context = context;
            this.layout=layout;
            this.data=list;

            data = new ArrayList<>();
            data.add(new Closet_ListViewItem("원피스",R.drawable.clothe1,"clothe-1"));
            data.add(new Closet_ListViewItem("테니스 스커트",R.drawable.clothe2,"clothe-2"));
            data.add(new Closet_ListViewItem("블라우스",R.drawable.clothe3,"clothe-3"));data.add(new Closet_ListViewItem("검정 구두",R.drawable.shoes1,"clothe-4"));
            data.add(new Closet_ListViewItem("원피스",R.drawable.clothe1,"clothe-5"));
            data.add(new Closet_ListViewItem("테니스 스커트",R.drawable.clothe2,"clothe-6"));
            data.add(new Closet_ListViewItem("블라우스",R.drawable.clothe3,"clothe-7"));
            data.add(new Closet_ListViewItem("검정 구두",R.drawable.shoes1,"clothe-8"));
        }
    public Closet_ListViewAdapter(Context context, ArrayList<Closet_ListViewItem> list) {
        this.context = context;
        this.data = list;
    }

    @Override
    public int getCount() {return data.size();}

    @Override
    public String getItem(int position){return data.get(position).closet_getName();}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView=inflater.inflate(R.layout.closet_item,null);
        }else{ }
        Closet_ListViewItem closet_listViewItem=data.get(position);

        ImageView image=(ImageView)convertView.findViewById(R.id.closet_imageview);
        image.setImageResource(closet_listViewItem.closet_getImage());

        return convertView;
    }
}
