package com.example.humiture.mvp.presenter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.rx.RxPresenter;
import com.example.humiture.mvp.contract.MineInfoContract;

import java.lang.reflect.Field;

import static com.example.humiture.utils.DensityUtils.dip2px;

/**
 *Time:2019/5/23
 *Author:冰冰凉
 *dec:
 */
public class MineInfoPresenter extends RxPresenter<MineInfoContract.View> implements MineInfoContract.Presenter {

    private Context mContext;
    public MineInfoPresenter(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 设置tablayout的底部线条的宽度
     * @param tabLayout
     */
    @Override
    public void reflex(TabLayout tabLayout) {

        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(() -> {
            try {
                //拿到tabLayout的mTabStrip属性
                LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                int dp10 = dip2px(tabLayout.getContext(), 10);

                for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                    View tabView = mTabStrip.getChildAt(i);

                    //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                    Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                    mTextViewField.setAccessible(true);

                    TextView mTextView = (TextView) mTextViewField.get(tabView);

                    tabView.setPadding(0, 0, 0, 0);

                    //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                    int width = 0;
                    width = mTextView.getWidth();
                    if (width == 0) {
                        mTextView.measure(0, 0);
                        width = mTextView.getMeasuredWidth();
                    }

                    //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                    params.width = width ;
                    params.leftMargin = dp10;
                    params.rightMargin = dp10;
                    tabView.setLayoutParams(params);

                    tabView.invalidate();
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

    }
}
