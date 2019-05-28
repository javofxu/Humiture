package com.example.humiture.ui.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.ui.activity.AirActivity;
import com.example.humiture.utils.helper.DataTypeHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 许格.
 * Date on 2019/5/20.
 * dec: 操作首页展示适配器
 */
public class OperateAdapter extends RecyclerView.Adapter<OperateAdapter.ItemHolder> {

    private List<Integer> mBackground;
    private Context mContext;

    public OperateAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_operate,viewGroup,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        mBackground = DataTypeHelper.getBackground();
        itemHolder.group_bg.setBackgroundResource(mBackground.get(i));
        itemHolder.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, AirActivity.class)));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.operate_group_bg)
        RelativeLayout group_bg;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
