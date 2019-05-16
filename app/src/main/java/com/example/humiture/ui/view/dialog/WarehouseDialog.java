package com.example.humiture.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.humiture.R;

/**
 * Created by 许格.
 * Date on 2019/5/16.
 * dec:
 */
public class WarehouseDialog extends Dialog {

    private Context context;

    public WarehouseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_warehouse);
    }

}
