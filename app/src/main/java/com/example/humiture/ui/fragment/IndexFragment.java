package com.example.humiture.ui.fragment;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.ui.view.adapter.LoopShowAdapter;
import com.example.humiture.utils.ItemDecorationUtils;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by 许格.
 * Date on 2019/5/14.
 * A simple {@link Fragment} subclass.
 * 首页
 */
public class IndexFragment extends BaseFragment {

    @BindView(R2.id.picker)
    DiscreteScrollView picker;
    @BindView(R2.id.index_point)
    LinearLayout point;

    LoopShowAdapter adapter;
    private ImageView[] mImageViews;
    private int pagerNumber;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView() {
        super.initView();
        pagerNumber = 4;
        HashMap<String, Integer> map = new HashMap<>();
        map.put(ItemDecorationUtils.LEFT_DECORATION,20);//右间距
        map.put(ItemDecorationUtils.RIGHT_DECORATION,20);//右间距
        adapter = new LoopShowAdapter(mContext,pagerNumber);
        picker.addItemDecoration(new ItemDecorationUtils(map));
        picker.setAdapter(adapter);
        picker.setCurrentItemChangeListener((viewHolder, adapterPosition) -> {
            drawPoint(adapterPosition);
        });
    }

    @Override
    protected void initData() {
        super.initData();
    }

    void drawPoint(int position){
        point.removeAllViews();
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
            point.addView(imageView);
        }
    }

}
