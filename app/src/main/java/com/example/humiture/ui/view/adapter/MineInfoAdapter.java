package com.example.humiture.ui.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.humiture.R;
import com.example.humiture.data.MessageData;

import java.util.List;

public class MineInfoAdapter extends RecyclerView.Adapter<MineInfoAdapter.ViewHolder>{

    private static final String TAG = "MineInfoAdapter";

    private Context context;
    private LayoutInflater inflater;
    private List<MessageData.Data> list;

    public MineInfoAdapter(Context context, List<MessageData.Data> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public MineInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mine_info_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.img.setImageResource(R.mipmap.mine_icon_police);
        viewHolder.tv_state.setText("【"+list.get(i).getStorename().toString() + list.get(i).getAlarmTypeName().toString()+"】");
        viewHolder.tv_day.setText(list.get(i).getStarttime().toString());
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: " + list.size());
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        LinearLayout linearLayout;
        TextView tv_state;
        TextView tv_day;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.mine_info_img);
            this.linearLayout = itemView.findViewById(R.id.mine_info_ll);
            this.tv_state = itemView.findViewById(R.id.mine_info_state);
            this.tv_day = itemView.findViewById(R.id.mine_info_day);
        }
    }

}
