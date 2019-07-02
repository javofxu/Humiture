package com.example.humiture.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.utils.DisplayUtils;
import com.example.humiture.utils.SaveBitMap;
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

    private static final String TAG = "PlayActivity";

    @BindView(R2.id.stat_suplayer)
    SurfaceView surfaceView;
    @BindView(R2.id.player_title)
    TextView name;
    @BindView(R2.id.player_magnify)
    ImageView magnify;
    @BindView(R2.id.player_magnify_exit)
    ImageView magnify_exit;
    @BindView(R2.id.player_mask)
    RelativeLayout mask;
    @BindView(R2.id.view_top)
    View view_top;
    @BindView(R2.id.rlt_top)
    RelativeLayout rlt_top;
    @BindView(R2.id.ll_bottom)
    LinearLayout ll_bottom;
    private TvUtils tvUtils;
    private int num = 0;
    private String title = null;
    private boolean isBack = false;
    private int count = 0;
    private boolean isFull = false;
    private SaveBitMap saveBitMap;

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
        Intent intent = getIntent();
        num = intent.getIntExtra("num",0);
        title = intent.getStringExtra("title");
        Log.i(TAG, "initView: " + num);
        if(!tvUtils.initeSdk()){
            this.finish();
            return;
        }
        name.setText(title);
        //视频播放的底栏的显示与消失
        surfaceView.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    count++;
                    if (count % 2 == 0) {     //偶数
                        magnify.setVisibility(View.GONE);
                        magnify_exit.setVisibility(View.GONE);
                        mask.setVisibility(View.GONE);
                    } else if (count % 2 == 1) { //奇数
                        if(isFull){
                            magnify.setVisibility(View.GONE);
                            magnify_exit.setVisibility(View.VISIBLE);
                            mask.setVisibility(View.VISIBLE);
                        }else {
                            magnify_exit.setVisibility(View.GONE);
                            magnify.setVisibility(View.VISIBLE);
                            mask.setVisibility(View.VISIBLE);
                        }

                    }
                    break;
            }
            return true;
        });
        Log.e(TAG, "initeSdk!");
        Log.e(TAG, "onCreate!");
    }

    @Override
    public void initData() {
        super.initData();

    }

    @OnClick({R2.id.play_back,R2.id.player_magnify,R2.id.player_magnify_exit,R2.id.folder,R2.id.screenshot,R2.id.videotape})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.play_back:
                finish();
                break;
            case R.id.player_magnify:
                DisplayUtils.toggleScreenOrientation(PlayActivity.this);
                view_top.setVisibility(View.GONE);
                rlt_top.setVisibility(View.GONE);
                ll_bottom.setVisibility(View.GONE);
                magnify.setVisibility(View.GONE);
                magnify_exit.setVisibility(View.VISIBLE);
                isBack = true;
                isFull = true;
                break;
            case R.id.player_magnify_exit:
                DisplayUtils.toggleScreenOrientation(PlayActivity.this);
                view_top.setVisibility(View.VISIBLE);
                rlt_top.setVisibility(View.VISIBLE);
                ll_bottom.setVisibility(View.VISIBLE);
                magnify.setVisibility(View.VISIBLE);
                magnify_exit.setVisibility(View.GONE);
                isBack = false;
                isFull = false;
                break;
            case R.id.folder:
                //文件夹
                showToast("暂不支持");
                break;
            case R.id.screenshot:
                //抓图
                Bitmap bitmap = tvUtils.setJpg(tvUtils.getM_iStartChan() + num);
                saveBitMap = new SaveBitMap();
                saveBitMap.saveBitmap(bitmap,this);
                break;
            case R.id.videotape:
                //录像
                showToast("暂不支持");
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(isBack){
                DisplayUtils.toggleScreenOrientation(PlayActivity.this);
                view_top.setVisibility(View.VISIBLE);
                rlt_top.setVisibility(View.VISIBLE);
                ll_bottom.setVisibility(View.VISIBLE);
                magnify.setVisibility(View.VISIBLE);
                magnify_exit.setVisibility(View.GONE);
                isBack = false;
                isFull = false;
                return false;
            }else{
                return super.onKeyDown(keyCode,event);
            }
        }else {
            return super.onKeyDown(keyCode,event);
        }
    }
}
