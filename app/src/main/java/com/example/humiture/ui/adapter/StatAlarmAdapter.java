package com.example.humiture.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.humiture.R;

import java.util.List;

/**
 *Time:2019/5/21
 *Author:冰冰凉
 *dec:报警统计下拉列表的适配器
 */
public class StatAlarmAdapter extends RecyclerView.Adapter<StatAlarmAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<String> list;

    public StatAlarmAdapter(Context context){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public StatAlarmAdapter(Context context,List<String> list){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public StatAlarmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stat_alarm_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatAlarmAdapter.ViewHolder viewHolder, int i) {
        //设置数据
        viewHolder.alarm_img.setImageResource(R.mipmap.stat_pm);
        viewHolder.tv_title.setText(list.get(i));
        /*viewHolder.tv_value.setText(list.get(i));
        viewHolder.tv_state.setText(list.get(i));
        viewHolder.tv_day.setText(list.get(i));
        viewHolder.tv_time.setText(list.get(i));*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //初始化子项控件
    static class ViewHolder extends RecyclerView.ViewHolder {

        //图片
        ImageView alarm_img;
        //类型显示
        TextView tv_title;
        //类型的值
        TextView tv_value;
        //状态
        TextView tv_state;
        //日期
        TextView tv_day;
        //具体时间
        TextView tv_time;

        public ViewHolder(View itemView) {
            super(itemView);
            this.alarm_img = itemView.findViewById(R.id.alarm_img);
            this.tv_title = itemView.findViewById(R.id.alarm_tv_pm);
            this.tv_value = itemView.findViewById(R.id.alarm_tv_value);
            this.tv_state = itemView.findViewById(R.id.alarm_tv_value);
            this.tv_day = itemView.findViewById(R.id.alarm_tv_day);
            this.tv_time = itemView.findViewById(R.id.alarm_tv_time);
        }
    }

}
