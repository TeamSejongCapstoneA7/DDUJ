package com.example.capstone_ryecyclerview_ex.jeju_setplan2;


import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone_ryecyclerview_ex.R;

import java.text.BreakIterator;
import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewModel>
        implements RecyclerRowMoveCallback.RecyclerViewRowTouchHelperContract{
    private static final String TAG = "a";
    private List<ItemModel> dataList;

    int cnt = 0;
    public void setDataList(List<ItemModel> dataList){
        this.dataList =  dataList;
        }

    @NonNull
    @Override
    public MyViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jeju_setplan_item2,parent,false);



        return new MyViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewModel holder, int position) {
        holder.lblItemName.setText(dataList.get(position).getName());
        holder.lblItemDetails.setText(dataList.get(position).getAdress());



        //체류 시간 받기
        int hour = dataList.get(position).getHour();
        if(hour==0){holder.Time_details.setText("start");
        }
        else{holder.Time_details.setText(hour+"h");}

        /////
        ViewGroup.LayoutParams layoutParams = holder.cardView.getLayoutParams();

        /////나중에 체류 시간 별로 크기 변경도 가능 할 듯

        int a = dataList.get(position).getType();
        //Log.d(TAG, "숫자"+a);
        if(a==0) {
            //layoutParams.height = 360;
            //holder.cardView.setLayoutParams(layoutParams);
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FEFFE2"));
        }
        else if(a==1) {
            //layoutParams.height = 300;
            //holder.cardView.setLayoutParams(layoutParams);
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FFF2F2"));
        }
        else if(a==2)
            holder.cardView.setCardBackgroundColor(Color.parseColor("#EFFFF1"));
        //시간 별로 크기 변경


        // 뽀록 용이 섞여있으므로 변경해주세요!!!!!!!!!!!!!
        // 4 -> 2 -> 3 시간으로 설정함
        if(hour<=1) {
            layoutParams.height = 310;
            holder.cardView.setLayoutParams(layoutParams);
        }
        else if(hour==2) {
            layoutParams.height = 370;
            holder.cardView.setLayoutParams(layoutParams);
            //holder.Time.setText("15:00~17:00"); //발표 이후에 지우는 용
        }

        else if(hour>=3){
            layoutParams.height = 430;
            holder.cardView.setLayoutParams(layoutParams);
            //holder.Time.setText("17:30~20:30"); //발표 이후에 지우는 용
        }

            //3 -> 2- > 4


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onRowMoved(int from, int to) {
        if(from < to)
        {
            for(int i=from; i<to; i++)
            {
                Collections.swap(dataList,i,i+1);
            }
        }
        else
        {
            for(int i=from; i>to; i--)
            {
                Collections.swap(dataList,i,i-1);
            }
        }
        notifyItemMoved(from,to);
    }


    //swiped 추가 해봄
    @Override
    public void onItemSwipe(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }
    //

    @Override
    public void onRowSelected(MyViewModel myViewHolder) {
        //myViewHolder.cardView.setCardBackgroundColor(Color.GRAY);
    }

    @Override
    public void onRowClear(MyViewModel myViewHolder) {
        //myViewHolder.cardView.setCardBackgroundColor(Color.parseColor("#F2F8F9"));
    }

    class MyViewModel extends RecyclerView.ViewHolder{


        TextView lblItemName,lblItemDetails,Time_details,Time;
        CardView cardView;


        public MyViewModel(@NonNull View itemView) {

            super(itemView);
            lblItemName = itemView.findViewById(R.id.lblItemName);
            lblItemDetails = itemView.findViewById(R.id.lblItemDetails);
            cardView = itemView.findViewById(R.id.cardView);
            Time_details = itemView.findViewById(R.id.Time_details);

        }
    }




}