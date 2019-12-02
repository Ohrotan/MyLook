/*package com.ssu.mylook.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ssu.mylook.dto.ClotheItem;
import com.ssu.mylook.ItemViewerActivity;

import java.util.ArrayList;

public class ClosetViewAdapter extends BaseAdapter {
    ArrayList<ClotheItem> items=new ArrayList<ClotheItem>();
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ClotheItem getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ItemViewerActivity itemViewer = new ItemViewerActivity(getApplicationContext());
        itemViewer.setItem(items.get(i));
        return itemViewer;
    }


}
*/
