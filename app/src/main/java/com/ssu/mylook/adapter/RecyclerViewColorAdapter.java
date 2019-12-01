package com.ssu.mylook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ssu.mylook.R;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewColorAdapter extends RecyclerView.Adapter<RecyclerViewColorAdapter.ViewHolder> {

    private String[] color_rank = {"1","2","3","4","5","6","7"};
    private String[] color_title = {"빨강", "주황", "노랑", "초록", "파랑", "남색", "보라"};
    private String[] color_number = {"10회", "9회", "8회", "7회", "6회", "5회", "4회"};

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView textView2;
        public TextView textView3;

        public ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.color_rank);
            this.textView2 = view.findViewById(R.id.color_title);
            this.textView3 = view.findViewById(R.id.color_number);
        }
    }

    @Override
    public RecyclerViewColorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_color_item, parent, false);
        RecyclerViewColorAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(color_rank[position]);
        holder.textView2.setText(color_title[position]);
        holder.textView3.setText(color_number[position]);

        holder.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Test!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), (position+1) + "번 째!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return color_rank.length;
    }
}