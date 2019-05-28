package com.example.humiture.ui.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.data.DetailsList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 许格.
 * Date on 2019/5/23.
 * dec:
 */
public class DetailsListAdapter extends RecyclerView.Adapter<DetailsListAdapter.ItemsHolder>{

    private Context mContext;
    private List<DetailsList.Data> mDetails;

    public DetailsListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void getDetailsList(List<DetailsList.Data> data){
        this.mDetails = data;
    }

    public void getMoreList(List<DetailsList.Data> dataList){
        mDetails.addAll(dataList);
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.items_details_list,viewGroup,false);
        return new ItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsHolder itemsHolder, int i) {
        DetailsList.Data data = mDetails.get(i);
        itemsHolder.mTime.setText(data.getTime());
        itemsHolder.mData.setText(String.valueOf(data.getDate()));
    }

    @Override
    public int getItemCount() {
        return mDetails==null?0:mDetails.size();
    }

    class ItemsHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.details_time)
        TextView mTime;
        @BindView(R2.id.details_data)
        TextView mData;

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
