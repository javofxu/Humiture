package com.example.humiture.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.humiture.R;
import com.example.humiture.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 许格.
 * Date on 2019/5/16.
 * dec:
 */
public class WarehouseDialog extends Dialog {

    @BindView(R2.id.dialog_chose)
    LinearLayout chose;

    private Context context;
    private TextView[] textViews;
    private int[] colors = {R.drawable.round_one, R.drawable.round_one, R.drawable.round_one,};

    public WarehouseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_warehouse);
        ButterKnife.bind(this);
        setmImageViews(colors,3);
    }

    void setmImageViews(int[] color,int viewNumber){
        textViews = new TextView[viewNumber];
        for (int i = 0; i < viewNumber; i++) {
            TextView tv = new TextView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 10, 20, 10);
            tv.setLayoutParams(params);
            tv.setText(String.valueOf(i+1));
            tv.setWidth(120);
            tv.setHeight(120);
            tv.setTextColor(0xFFFFFFFF);
            tv.setTextSize(20);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(color[i]);
            textViews[i] = tv;
            chose.addView(textViews[i]);
        }
    }
}
