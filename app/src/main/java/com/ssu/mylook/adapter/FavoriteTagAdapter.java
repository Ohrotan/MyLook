package com.ssu.mylook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.ssu.mylook.R;
import com.ssu.mylook.dto.Custom2DTO;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class FavoriteTagAdapter extends BaseAdapter {
    private ArrayList<Custom2DTO> listCustom = new ArrayList<>();
    Context context;
    ArrayList<String> clicked = new ArrayList<>();


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    int lastposition=0;
    int lastcount=0;
    int currentcount=0;

    public int simple=0;
    int campus=0;
    int casual=0;
    int unique=0;
    int sporty=0;
    int lovely=0;
    int office=0;
    int sexy=0;
    int fancy=0;

//    Map<String, Integer> tags=new HashMap<>();
//    tags.put("simple",0);
//    tags.put("campus",0);


    private ArrayList<List<String>> mArrayList;
    ArrayList<String[]> list = new ArrayList<String[]>();

//    public FavoriteTagAdapter(Context context, ArrayList<Custom2DTO> list){
//        this.context = context;
//        listCustom = new ArrayList<>();
//    }

    public FavoriteTagAdapter(Context context, ArrayList<Custom2DTO> list) {
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

        CustomViewHolder holder;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.favorite_tag_item, null);

            holder = new CustomViewHolder();
            holder.textRank= (TextView)convertView.findViewById(R.id.tag_rank);
            holder.textTitle = (TextView) convertView.findViewById(R.id.tag_title);
            holder.textContent = (TextView) convertView.findViewById(R.id.tag_number);

            convertView.setTag(holder);

        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }
        Custom2DTO dto = listCustom.get(position);
//
//
//        db.collection("coordi").whereEqualTo("tag", "심플베이직").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    simple=task.getResult().size();
//                } else {
//                    //Log.d("TAG", "Error getting documents: ", task.getException());
//                }
//            }
//        });
//
//        db.collection("coordi").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    //Log.d("TAG", task.getResult().size() + "");
//                    allCoordi.setText("현재 옷장에 등록한 옷 : "+task.getResult().size());
//                } else {
//                    //Log.d("TAG", "Error getting documents: ", task.getException());
//                    allCoordi.setText("Error : 옷장에 등록된 옷 data를 불러올 수 없습니다");
//                }
//            }
//        });

        //setText
        if(lastcount < currentcount)
            holder.textRank.setText(Integer.toString(lastposition));
        else
        holder.textRank.setText(Integer.toString(position));
        holder.textTitle.setText(dto.getField());
        holder.textContent.setText(Integer.toString(dto.getCount())+"회");

        return convertView;
    }

    class CustomViewHolder {
        TextView textContent;
        TextView textTitle;
        TextView textRank;
    }

    // FavoriteTagActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(Custom2DTO dto) {
        listCustom.add(dto);
    }
}

/*if(doc.get("tag").equals("심플베이직")){
                                    simple++;
                                } else if(doc.get("tag").equals("캠퍼스룩")){
                                    campus++;
                                } else if(doc.get("tag").equals("캐주얼")){
                                    casual++;
                                }else if(doc.get("tag").equals("유니크")){
                                    unique++;
                                }else if(doc.get("tag").equals("스포티")){
                                    sporty++;
                                }else if(doc.get("tag").equals("러블리")){
                                    lovely++;
                                }else if(doc.get("tag").equals("오피스룩")){
                                    office++;
                                }else if(doc.get("tag").equals("섹시글램")){
                                    sexy++;
                                }else if(doc.get("tag").equals("화려한")){
                                    fancy++;
                                }*/

