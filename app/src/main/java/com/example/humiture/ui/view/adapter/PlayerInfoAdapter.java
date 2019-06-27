package com.example.humiture.ui.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.humiture.R;
import com.example.humiture.ui.activity.PlayActivity;

import org.MediaPlayer.PlayM4.Player;
import org.w3c.dom.Text;
import org.w3c.dom.ls.LSException;

import java.util.List;

/**
 *Time:2019/6/26
 *Author:冰冰凉
 *dec:
 */
public class PlayerInfoAdapter extends RecyclerView.Adapter<PlayerInfoAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<String> list;

    public PlayerInfoAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public PlayerInfoAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PlayerInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_playerinfo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerInfoAdapter.ViewHolder viewHolder, int i) {
        viewHolder.txt.setText(list.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PlayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("num",i);
                bundle.putString("title",list.get(i));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this .txt = itemView.findViewById(R.id.play_txt);
        }
    }

}
