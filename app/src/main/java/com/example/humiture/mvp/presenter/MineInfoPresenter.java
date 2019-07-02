package com.example.humiture.mvp.presenter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.rx.RxPresenter;
import com.example.humiture.data.Common;
import com.example.humiture.data.MessageData;
import com.example.humiture.mvp.contract.MineInfoContract;
import com.example.humiture.mvp.model.MineInfoModel;
import com.example.humiture.utils.ToastUtils;
import com.example.humiture.utils.helper.SpUtils;

import java.lang.reflect.Field;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.example.humiture.utils.DensityUtils.dip2px;

/**
 *Time:2019/5/23
 *Author:冰冰凉
 *dec:
 */
public class MineInfoPresenter extends RxPresenter<MineInfoContract.View> implements MineInfoContract.Presenter {

    private static final String TAG = "MineInfoPresenter";
    private Context mContext;
    private MineInfoContract.Model model = new MineInfoModel();
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

    /**
     * 修改密码
     * @param userId
     * @param password
     */
    @Override
    public void getChangePwd(int userId, String password) {
        Observable<Common> observable = model.getChangePwd(userId,password);
        observable.subscribe(common -> {
            if (common.getStatus() == 0) {
                //成功,将账号密码保存到本地，保存时间也是一天
                SpUtils.getInstance(mContext).setString("password", password, SpUtils.TIME_DAY);
                mView.onSuccess(common.getMsg());
            } else {
                //失败
                mView.onFail(common.getMsg());
            }
        }, throwable -> Log.d(TAG, "loginPresenter:获取数据失败"));
    }

    /**
     * 报警信息列表
     * @param strtime
     */
    @Override
    public void getAlarmMessage(String strtime) {
        Observable<MessageData> observable = model.getAlarmMessage(strtime);
        observable.subscribe(new Consumer<MessageData>() {
            @Override
            public void accept(MessageData messageData) throws Exception {
                Log.i(TAG, "getAlarmMessage: " + messageData.getStatus());
                if (messageData.getStatus() == 0) {
                    //成功
                    if(messageData.getData().size() > 0){
                        mView.onListSuccess(messageData);
                    }else {
                        mView.noDetails();
                    }


                } else {
                    //失败
                    mView.onFail("");
                }
            }
        }, throwable -> Log.d(TAG, "getAlarmMessage:获取数据失败"));
    }
}
