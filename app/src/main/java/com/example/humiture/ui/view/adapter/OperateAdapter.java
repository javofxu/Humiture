package com.example.humiture.ui.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.AllList;
import com.example.humiture.data.DeviceData;
import com.example.humiture.data.RealTimeData;
import com.example.humiture.ui.activity.AirActivity;
import com.example.humiture.ui.view.VerticalTextview;
import com.example.humiture.utils.helper.DataTypeHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 许格.
 * Date on 2019/5/20.
 * dec: 操作首页展示适配器
 */
public class OperateAdapter extends RecyclerView.Adapter<OperateAdapter.ItemHolder> {

    private static final String TAG = "OperateAdapter";

    private List<Integer> mBackground;
    private Context mContext;
    private List<AllList> list;
    private RealTimeData realData;

    private List<DeviceData> deviceDataList = null;
    private List<String> listText = null;
    private List<String> listState = null;

    public OperateAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public OperateAdapter(Context context, List<AllList> list) {
        this.mContext = context;
        this.list = list;
    }

    public OperateAdapter(Context context, List<AllList> list, RealTimeData realTimeData) {
        this.mContext = context;
        this.list = list;
        this.realData = realTimeData;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_operate, viewGroup, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        mBackground = DataTypeHelper.getBackground();
        listText = new ArrayList<String>();
        listState = new ArrayList<String>();
        deviceDataList = new ArrayList<>();
        deviceDataList = list.get(i).getDeviceData();
        for (int j = 0; j < deviceDataList.size(); j++) {
            listText.add(deviceDataList.get(j).getStatusName());
            listState.add(deviceDataList.get(j).getDeviceId() + "号设备");
        }
        Log.i(TAG, "onBindViewHolder: lists.size" + listText.size());
        if (listText.size() > 0) {
            itemHolder.operateGroupState.setVisibility(View.GONE);
            itemHolder.text.setVisibility(View.VISIBLE);
            //状态的切换
            itemHolder.vertivalStateName.setVisibility(View.VISIBLE);
            itemHolder.text.setTextList((ArrayList<String>) listText);
            itemHolder.text.setText(16, 5, Color.WHITE);//设置属性
            itemHolder.text.setTextStillTime(3000);//设置停留时长间隔
            itemHolder.text.setAnimTime(300);//设置进入和退出的时间间隔
            itemHolder.text.startAutoScroll();
            //设备号的切换
            itemHolder.vertivalStateName.setTextList((ArrayList<String>) listState);
            itemHolder.vertivalStateName.setText(16, 5, Color.WHITE);//设置属性
            itemHolder.vertivalStateName.setTextStillTime(3000);//设置停留时长间隔
            itemHolder.vertivalStateName.setAnimTime(300);//设置进入和退出的时间间隔
            itemHolder.vertivalStateName.startAutoScroll();

        } else {
            itemHolder.text.setVisibility(View.GONE);
            itemHolder.vertivalStateName.setVisibility(View.GONE);
            itemHolder.operateGroupState.setVisibility(View.VISIBLE);
        }
        //设备名称右上角
        itemHolder.operateGroupName.setText(list.get(i).getName());
        Log.i(TAG, "onBindViewHolder:Name " + list.get(i).getName());

        if (list.get(i).getName().equals("空调")) {
            //背景  温度
            itemHolder.operateGroupData.setText(realData.getData().getTemperature());
            itemHolder.operateGroupSymbols.setText(R.string.dw_temp);
            itemHolder.operateGroupType.setText(R.string.operate_temp);
            Log.i(TAG, "onBindViewHolder: " + realData.getData().getTemperature());
            itemHolder.group_bg.setBackgroundResource(mBackground.get(0));
            //图标
            itemHolder.operateGroupIcon.setImageResource(R.mipmap.operate_air);
        } else if (list.get(i).getName().equals("除湿机")) {
            itemHolder.operateGroupData.setText(realData.getData().getHumidity());
            itemHolder.operateGroupSymbols.setText(R.string.dw_hum);
            itemHolder.operateGroupType.setText(R.string.operate_hum);
            itemHolder.group_bg.setBackgroundResource(mBackground.get(1));
            itemHolder.operateGroupIcon.setImageResource(R.mipmap.operate_dehumidifier);
        } else if (list.get(i).getName().equals("消毒机")) {
            //菌落
            //这里容易爆菌落为空
            if (TextUtils.isEmpty(realData.getData().getColony())){
                itemHolder.operateGroupData.setText("");
            }else{
                itemHolder.operateGroupData.setText(realData.getData().getColony());
            }
            itemHolder.operateGroupSymbols.setText(R.string.dw_colony);
            itemHolder.operateGroupType.setText(R.string.operate_colony);
            itemHolder.group_bg.setBackgroundResource(mBackground.get(3));
            itemHolder.operateGroupIcon.setImageResource(R.mipmap.operate_disinfection);
        } else if (list.get(i).getName().equals("加湿机")) {
            //湿度
            itemHolder.operateGroupData.setText(realData.getData().getHumidity());
            itemHolder.operateGroupSymbols.setText(R.string.dw_hum);
            itemHolder.operateGroupType.setText(R.string.operate_hum);
            itemHolder.group_bg.setBackgroundResource(mBackground.get(2));
            itemHolder.operateGroupIcon.setImageResource(R.mipmap.operate_humidification);
        } else if (list.get(i).getName().equals("一体机")) {
            itemHolder.group_bg.setBackgroundResource(mBackground.get(4));
            itemHolder.operateGroupIcon.setImageResource(R.mipmap.operate_humidity);
        } else if (list.get(i).getName().equals("净化机")) {
            //净化机  甲醛
            itemHolder.operateGroupData.setText(realData.getData().getPm2());
            itemHolder.operateGroupSymbols.setText(R.string.dw_pm2);
            itemHolder.operateGroupType.setText(R.string.operate_pm2);
            itemHolder.group_bg.setBackgroundResource(mBackground.get(i));
        }
        itemHolder.itemView.setOnClickListener(v -> {
            if (list.get(i).getDeviceData().size() <= 0) {
                //显示设备暂无运行
                Toast.makeText(mContext, R.string.operate_state, Toast.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "onClick: " + list.get(i).getName());
                Intent intent = new Intent(mContext, AirActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", list.get(i).getName());
                bundle.putSerializable("data", (Serializable) list);
                bundle.putSerializable("realTimeData", (Serializable) realData);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.operate_group_bg)
        RelativeLayout group_bg;
        @BindView(R2.id.operate_group_icon)
        ImageView operateGroupIcon;
        @BindView(R2.id.operate_group_data)
        TextView operateGroupData;
        @BindView(R2.id.operate_group_now)
        LinearLayout operateGroupNow;
        @BindView(R2.id.operate_group_line)
        TextView operateGroupLine;
        @BindView(R2.id.operate_group_state)
        TextView operateGroupState;
        @BindView(R2.id.operate_group_status)
        LinearLayout operateGroupStatus;
        @BindView(R2.id.operate_group_name)
        TextView operateGroupName;
        @BindView(R2.id.operate_group_symbols)
        TextView operateGroupSymbols;
        @BindView(R2.id.operate_group_type)
        TextView operateGroupType;
        @BindView(R2.id.vertivalTextView)
        VerticalTextview text;
        @BindView(R2.id.vertivalStateName)
        VerticalTextview vertivalStateName;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
