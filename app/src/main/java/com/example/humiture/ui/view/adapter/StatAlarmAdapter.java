package com.example.humiture.ui.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AlphabetIndexer;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.humiture.R;
import com.example.humiture.data.Alarm;
import com.example.humiture.utils.helper.DataTypeHelper;

import java.util.List;

/**
 *Time:2019/5/21
 *Author:冰冰凉
 *dec:报警统计下拉列表的适配器
 */
public class StatAlarmAdapter extends RecyclerView.Adapter<StatAlarmAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Alarm.Data.ListAlarm> list;
    private String unit = null;

    public StatAlarmAdapter(Context context){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public StatAlarmAdapter(Context context,List<Alarm.Data.ListAlarm> list){
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
        if(list.get(i).getAlarmType().contains("有害气体")){
            viewHolder.alarm_img.setImageResource(R.mipmap.stat_gas);
            unit = DataTypeHelper.getDataUnit().get(3);
            viewHolder.tv_state.setText(list.get(i).getAlarm_value() + unit);
        }else if(list.get(i).getAlarmType().contains("pm2.5")){
            viewHolder.alarm_img.setImageResource(R.mipmap.stat_pm);
            unit = DataTypeHelper.getDataUnit().get(2);
            viewHolder.tv_state.setText(list.get(i).getAlarm_value() + unit);
        }else if(list.get(i).getAlarmType().contains("温度")){
            viewHolder.alarm_img.setImageResource(R.mipmap.stat_temperature);
            unit = DataTypeHelper.getDataUnit().get(0);
            viewHolder.tv_state.setText(list.get(i).getAlarm_value() + unit);
        }else if(list.get(i).getAlarmType().contains("湿度")){
            viewHolder.alarm_img.setImageResource(R.mipmap.stat_humidity);
            unit = DataTypeHelper.getDataUnit().get(1);
            viewHolder.tv_state.setText(list.get(i).getAlarm_value() + unit);
        }else if(list.get(i).getAlarmType().contains("TVOC")){
            viewHolder.alarm_img.setImageResource(R.mipmap.stat_tvoc);
            unit = DataTypeHelper.getDataUnit().get(3);
            viewHolder.tv_state.setText(list.get(i).getAlarm_value() + unit);
        }else if(list.get(i).getAlarmType().contains("ECO2")){
            viewHolder.alarm_img.setImageResource(R.mipmap.stat_eoc2);
            unit = DataTypeHelper.getDataUnit().get(3);
            viewHolder.tv_state.setText(list.get(i).getAlarm_value() + unit);
        }else if(list.get(i).getAlarmType().contains("甲醛")){
            viewHolder.alarm_img.setImageResource(R.mipmap.stat_formol);
            unit = DataTypeHelper.getDataUnit().get(3);
            viewHolder.tv_state.setText(list.get(i).getAlarm_value() + unit);
        }else if(list.get(i).getAlarmType().contains("菌落")){
            viewHolder.alarm_img.setImageResource(R.mipmap.stat_bacterial);
            unit = DataTypeHelper.getDataUnit().get(4);
            viewHolder.tv_state.setText(list.get(i).getAlarm_value() + unit);
        }
        viewHolder.tv_title.setText(list.get(i).getAlarmType());
        viewHolder.tv_day.setText(list.get(i).getCreated());
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

        public ViewHolder(View itemView) {
            super(itemView);
            this.alarm_img = itemView.findViewById(R.id.alarm_img);
            this.tv_title = itemView.findViewById(R.id.alarm_tv_pm);
            this.tv_value = itemView.findViewById(R.id.alarm_tv_value);
            this.tv_state = itemView.findViewById(R.id.alarm_tv_value);
            this.tv_day = itemView.findViewById(R.id.alarm_tv_day);
        }
    }

}
