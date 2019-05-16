package com.example.humiture.mvp.presenter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.base.rx.RxPresenter;
import com.example.humiture.R;
import com.example.humiture.mvp.contract.IndexContract;
import com.example.humiture.ui.view.dialog.WarehouseDialog;
import com.example.humiture.utils.DensityUtils;

/**
 * Created by 许格.
 * Date on 2019/5/16.
 * dec:
 */
public class IndexPresent extends RxPresenter<IndexContract.mView> implements IndexContract.present {

    private Context mContext;
    private ImageView[] mImageViews;

    public IndexPresent(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void drawPoint(LinearLayout layout, int pagerNumber,int position) {
        layout.removeAllViews();
        mImageViews = new ImageView[pagerNumber];
        for (int i = 0; i < pagerNumber; i++) {
            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 0, 10, 0);
            imageView.setLayoutParams(params);
            if(i==position){
                imageView.setImageResource(R.mipmap.index_yuan_sel);
            }
            else {
                imageView.setImageResource(R.mipmap.index_yuan);
            }
            mImageViews[i]=imageView;
            layout.addView(imageView);
        }
    }

    @Override
    public void warehouse() {
        WarehouseDialog dialog = new WarehouseDialog(mContext,R.style.MyDialog);
        dialog.show();
        DensityUtils.setDialogWindowAttr(dialog,mContext);
    }
}
