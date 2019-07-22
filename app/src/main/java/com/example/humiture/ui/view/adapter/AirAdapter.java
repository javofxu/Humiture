package com.example.humiture.ui.view.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.AllList;
import com.example.humiture.data.DeviceData;
import com.example.humiture.ui.activity.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 许格.
 * Date on 2019/5/20.
 * dec:
 */
public class AirAdapter extends RecyclerView.Adapter<AirAdapter.ItemHolder> {

    private static final String TAG = "AirAdapter";

    private Context mContext;
    private List<DeviceData> list;
    private static Selected selected;
    private float x1 = 0,x2 = 0,y1 = 0,y2 = 0;
    private float downX ;    //按下时 的X坐标
    private float downY ;    //按下时 的Y坐标

    public AirAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public AirAdapter(Context context, List<DeviceData> list){
        this.mContext = context;
        this.list = list;
    }

    public void setSelected(Selected sl){
        selected = sl;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.items_air, viewGroup, false);
        return new ItemHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        itemHolder.itemAirNumber.setText(list.get(i).getName());
        itemHolder.itemAirDeviceId.setText(list.get(i).getDeviceId() + "号设备");
        Log.i(TAG, "onBindViewHolder: " + list.get(i).getDeviceId() + "---" + i);
        if (list.get(i).getName().equals("空调")) {
            //图标
            itemHolder.itemAirIcon.setImageResource(R.mipmap.operate_air);
        } else if (list.get(i).getName().equals("除湿机")) {
            itemHolder.itemAirIcon.setImageResource(R.mipmap.operate_dehumidifier);
        } else if (list.get(i).getName().equals("消毒机")) {
            itemHolder.itemAirIcon.setImageResource(R.mipmap.operate_disinfection);
        } else if (list.get(i).getName().equals("加湿机")) {
            itemHolder.itemAirIcon.setImageResource(R.mipmap.operate_humidification);
        } else if (list.get(i).getName().equals("一体机")) {
            itemHolder.itemAirIcon.setImageResource(R.mipmap.operate_humidity);
        } else if (list.get(i).getName().equals("净化机")) {
            //净化机
        }
        selected.selected(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.item_air_icon)
        ImageView itemAirIcon;
        @BindView(R2.id.item_air_number)
        TextView itemAirNumber;
        @BindView(R2.id.item_air_light)
        TextView itemAirLight;
        @BindView(R2.id.item_air_deviceId)
        TextView itemAirDeviceId;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //回调接口
    public interface Selected{
        void selected(int position);
    }

    /**
     * 根据距离差判断 滑动方向
     * @param dx X轴的距离差
     * @param dy Y轴的距离差
     * @return 滑动的方向
     */
    private int getOrientation(float dx, float dy) {
        Log.e("Tag","========X轴距离差："+dx);
        Log.e("Tag","========Y轴距离差："+dy);
        if (Math.abs(dx)>Math.abs(dy)){
            //X轴移动
            return dx>0?'r':'l';
        }else{
            //Y轴移动
            return dy>0?'b':'t';
        }
    }

}
