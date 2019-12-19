package com.ssu.mylook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssu.mylook.ClotheSearchActivity;
import com.ssu.mylook.R;
import com.ssu.mylook.dto.ClotheDTO;
import com.ssu.mylook.util.DBUtil;
import java.util.ArrayList;
import java.util.Locale;

public class ClosetViewAdapter extends BaseAdapter {


    private ArrayList<ClotheDTO> items;
    private Context context;
    ClotheDTO itemDTO;

    public ClosetViewAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    public ClosetViewAdapter(Context context, ArrayList<ClotheDTO> items) {
        this.context = context;
        this.items = items;
    }

    class ViewHolder {
        ImageView clothe_img;
        TextView clothe_title;

    }

    @Override
    public int getCount() {
        return items.size();
    }


    public void addItem(ClotheDTO clotheItem) {

        items.add(clotheItem);
    }


    @Override
    public ClotheDTO getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClosetViewAdapter.ViewHolder holder;
        //항목 레이아웃 초기화
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.closet_item, parent, false);
            holder = new ClosetViewAdapter.ViewHolder();
            holder.clothe_img = (ImageView) convertView.findViewById(R.id.closet_imageview);
            holder.clothe_title = (TextView) convertView.findViewById(R.id.closet_textview);

            convertView.setTag(holder);
        } else {
            holder = (ClosetViewAdapter.ViewHolder) convertView.getTag();
        }
        //Toast.makeText(context, "" + position, Toast.LENGTH_LONG).show();
        itemDTO = items.get(position);


        new DBUtil().setImageViewFromDB(context, holder.clothe_img, itemDTO.getImage());
        holder.clothe_title.setText(itemDTO.getTitle());

        //Toast.makeText(context, holder.clothe_title.getText().toString() + "", Toast.LENGTH_LONG).show(); //옷 이름이 안뜸

        return convertView;
    }

    /*public void filter(String charText) {
        db.collection("clothes").whereEqualTo("title",)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        ArrayList<ClotheDTO> list = new ArrayList<>();
                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                            ClotheDTO item = doc.toObject(ClotheDTO.class);
                            item.setId(doc.getId());
                            list.add(item);
                            Toast.makeText(getApplicationContext(), ""+item.getTitle(), Toast.LENGTH_LONG).show();
                            //Toast.makeText(getApplicationContext(), ""+item.getTTL(), Toast.LENGTH_LONG).show();
                        }

                        closetViewAdapter = new ClosetViewAdapter(ClotheSearchActivity.this,list);
                        gridView.setAdapter(closetViewAdapter);
                    }});
        }
    }*/
}