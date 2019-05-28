package com.example.humiture.ui.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.RealTimeData;
import com.example.humiture.ui.view.WaveLoadingView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 许格.
 * Date on 2019/5/16.
 * dec:
 */
public class LoopShowAdapter extends RecyclerView.Adapter<LoopShowAdapter.ItemHolder>{

    private Context context;
    private int pagerNumber;
    private RealTimeData mData;

    public LoopShowAdapter(Context context,int size) {
        this.context = context;
        this.pagerNumber = size;
    }

    public void getRealTime(RealTimeData realTimeData){
        this.mData = realTimeData;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_index_wave, parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        switch (position){
            case 0:
                holder.left_wave.setWaveColor(0xffdc4d9d);
                holder.left_wave.setTopTitle(mData==null?"0.0":mData.getData().getTemperature());
                holder.left_icon.setImageResource(R.mipmap.icon_temperature);
                holder.left_msg.setText(R.string.index_temp);
                holder.right_wave.setWaveColor(0xff04b9e5);
                holder.right_wave.setTopTitle(mData==null?"0.0":mData.getData().getHumidity());
                holder.right_icon.setImageResource(R.mipmap.icon_humidity);
                holder.right_msg.setText(R.string.index_hum);
                break;
            case 1:
                holder.left_wave.setWaveColor(0xff11df99);
                holder.left_wave.setTopTitle(mData==null?"0.0":mData.getData().getPm2());
                holder.left_icon.setImageResource(R.mipmap.icon_pm25);
                holder.left_msg.setText(R.string.index_pm);
                holder.right_wave.setWaveColor(0xffb3d25a);
                holder.right_wave.setTopTitle(mData==null?"0.0":mData.getData().getColony());
                holder.right_icon.setImageResource(R.mipmap.icon_bacterial);
                holder.right_msg.setText(R.string.index_colony);
                break;
            case 2:
                holder.left_wave.setWaveColor(0xff13b745);
                holder.left_wave.setTopTitle(mData==null?"0.0":mData.getData().getFormaldehyde());
                holder.left_icon.setImageResource(R.mipmap.icon_formol);
                holder.left_msg.setText(R.string.index_formaldehyde);
                holder.right_wave.setWaveColor(0xfff2c107);
                holder.right_wave.setTopTitle(mData==null?"0.0":mData.getData().getTvoc());
                holder.right_icon.setImageResource(R.mipmap.icon_tvoc);
                holder.right_msg.setText(R.string.index_tv);
                break;
            case 3:
                holder.left_wave.setWaveColor(0xff8f31e1);
                holder.left_wave.setTopTitle(mData==null?"0.0":mData.getData().getHarmful());
                holder.left_icon.setImageResource(R.mipmap.icon_gas);
                holder.left_msg.setText(R.string.index_harmful);
                holder.right_wave.setWaveColor(0xff75cfea);
                holder.right_wave.setTopTitle(mData==null?"0.0":mData.getData().getEco2());
                holder.right_icon.setImageResource(R.mipmap.icon_eco2);
                holder.right_msg.setText(R.string.index_eco);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return pagerNumber;
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.item_left_wave)
        WaveLoadingView left_wave;
        @BindView(R2.id.item_right_wave)
        WaveLoadingView right_wave;
        @BindView(R2.id.item_left_icon)
        ImageView left_icon;
        @BindView(R2.id.item_right_icon)
        ImageView right_icon;
        @BindView(R2.id.item_left_msg)
        TextView left_msg;
        @BindView(R2.id.item_right_msg)
        TextView right_msg;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
