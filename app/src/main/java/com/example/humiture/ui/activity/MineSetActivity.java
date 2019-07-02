package com.example.humiture.ui.activity;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.utils.CacheDataManager;
import com.example.humiture.utils.helper.SpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *Time:2019/5/23
 *Author:冰冰凉
 *dec:
 */
public class MineSetActivity extends BaseActivity {

    @BindView(R2.id.mine_cache_num)
    TextView tv_cache_num;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_set;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
    }

    @Override
    protected void initView() {
        super.initView();
        try{
            tv_cache_num.setText(CacheDataManager.getTotalCacheSize(this));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id.mine_back,R.id.mine_ll_safe,R.id.mine_ll_bio,R.id.mine_ll_kufang,R.id.mine_ll_clean,R.id.mine_ll_change,R.id.mine_ll_exit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.mine_back:
                finish();
                break;
            case R.id.mine_ll_safe:
                skipAnotherActivity(MineSafeActivity.class);
                break;
            case R.id.mine_ll_bio:
                //以后开发
                break;
            case R.id.mine_ll_kufang:
                //库房
                skipAnotherActivity(KuFangActivity.class);
                break;
            case R.id.mine_ll_clean:
                //清除缓存
                skipAnotherActivity(CacheDataActivity.class);
                break;
            case R.id.mine_ll_change:
                break;
                //退出登录
            case R.id.mine_ll_exit:
                SpUtils.getInstance(this).clear();
                if(SpUtils.getInstance(this).getString("username","").length() <= 0){
                    skipAnotherActivity(LoginActivity.class);
                }else{
                    showToast("退出登录失败");
                }
                break;
        }
    }

}
