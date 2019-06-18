package com.example.humiture.ui.activity;

import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.utils.NumCountUtil;
import com.example.humiture.utils.TvUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *Time:2019/6/4
 *Author:冰冰凉
 *dec:摄像头
 * ip：在后台获取
 * 端口：外网映射的端口是8801
 * 端口：内网端口为8000
 */
public class PlayActivity extends BaseActivity {

    @BindView(R2.id.stat_suplayer)
    SurfaceView surfaceView;
    @BindView(R2.id.name)
    TextView name;
    private TvUtils tvUtils;
    int num = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
    }

    @Override
    protected void initView() {
        super.initView();
        tvUtils = new TvUtils(this,surfaceView,null);
        if(!tvUtils.initeSdk()){
            this.finish();
            return;
        }
        Log.e(TAG, "initeSdk!");
        Log.e(TAG, "onCreate!");
    }

    @Override
    public void initData() {
        super.initData();

    }

    @OnClick({R2.id.play_back,R2.id.previous,R2.id.next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.play_back:
                finish();
                break;
            case R.id.previous:
                //上一个
                syg();
                break;
            case R.id.next:
                //下一个
                next();
                break;
        }
    }

    private void startVedio() {
        if (tvUtils != null) {
            tvUtils.login();
            tvUtils.startSinglePreview(tvUtils.getM_iStartChan() + num);           //iStartChan = 33
            Log.i(TAG, "startVedio: " + tvUtils.getM_iStartChan() + "===" + tvUtils.getM_iPort());
            Log.e(TAG, "startSinglePreview!");

        }
    }

    private void stopVedio() {
        if (tvUtils != null) {
            tvUtils.stopSinglePreview();
            Log.e(TAG, "stopSinglePreview!");
        }
    }

    private void syg() {
        --num;
        if (num < 0) {
            ++num;
            return;
        }
        stopVedio();

        startVedio();
        name.setText("通道" + NumCountUtil.numTo(num + 1));

    }

    private void next() {
        ++num;
        if (num > 5) {
            --num;
            return;
        }
        stopVedio();

        startVedio();
        name.setText("通道" + NumCountUtil.numTo(num + 1));

    }

    @Override
    public void onResume() {
        super.onResume();
        startVedio();
        Log.e(TAG, "onResume!");
    }

    @Override
    protected void onStop() {
        stopVedio();
        super.onStop();
        Log.e(TAG, "onStop!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
