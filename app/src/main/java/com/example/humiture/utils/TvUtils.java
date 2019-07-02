package com.example.humiture.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.humiture.ui.view.MyPlaySurfaceView;
import com.hikvision.netsdk.ExceptionCallBack;
import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.INT_PTR;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.hikvision.netsdk.NET_DVR_JPEGPARA;
import com.hikvision.netsdk.NET_DVR_PREVIEWINFO;
import com.hikvision.netsdk.RealPlayCallBack;

import org.MediaPlayer.PlayM4.Player;

/**
 * Time:2019/6/4
 * Author:冰冰凉
 * dec:摄像头
 * ip：在后台获取
 * 端口：外网映射的端口是8801
 * 端口：内网端口为8000
 */
public class TvUtils implements SurfaceHolder.Callback {

    //ip在后台获取
    String ip = "115.231.60.194";
    //端口：内网为8000（通常用），外网映射为8801
    int port = 8801;
    String user = "admin";
    String psd = "fxs12345";
    private final String TAG = "TvActivity";
    private NET_DVR_DEVICEINFO_V30 m_oNetDvrDeviceInfoV30 = null;
    private int m_iPort = -1;                // play port
    private int m_iStartChan = 0;                // start channel no
    private int m_iChanNum = 0;                //channel number
    private int myIChanNum = 0;
    private int m_iLogID = -1;                // return by NET_DVR_Login_v30
    private int m_iPlayID = -1;                // return by NET_DVR_RealPlay_V30
    private int m_iPlaybackID = -1;                // return by NET_DVR_PlayBackByTime
    private boolean m_bNeedDecode = true;
    private MyPlaySurfaceView[] playView = null;
    private Context context;
    private SurfaceView m_osurfaceView = null;

    public TvUtils(Context context, SurfaceView m_osurfaceView, MyPlaySurfaceView[] playView) {
        this.m_osurfaceView = m_osurfaceView;
        this.context = context;
        this.playView = playView;
        this.m_osurfaceView.getHolder().addCallback(this);
    }

    public boolean initeSdk() {
        //init net sdk
        if (!HCNetSDK.getInstance().NET_DVR_Init()) {
            Log.e(TAG, "HCNetSDK init is failed!");
            return false;
        }
        HCNetSDK.getInstance().NET_DVR_SetLogToFile(3, "/mnt/sdcard/sdklog/", true);
        return true;
    }

    private int loginDevice() {
        // get instance
        m_oNetDvrDeviceInfoV30 = new NET_DVR_DEVICEINFO_V30();
        if (null == m_oNetDvrDeviceInfoV30) {
            Log.e(TAG, "HKNetDvrDeviceInfoV30 new is failed!");
            return -1;
        }

        // call NET_DVR_Login_v30 to login on, port 8000 as default
        int iLogID = HCNetSDK.getInstance().NET_DVR_Login_V30(ip, port, user, psd, m_oNetDvrDeviceInfoV30);
        if (iLogID < 0) {
            Log.e(TAG, "NET_DVR_Login is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            return -1;
        }
        if (m_oNetDvrDeviceInfoV30.byChanNum > 0) {
            m_iStartChan = m_oNetDvrDeviceInfoV30.byStartChan;
            m_iChanNum = m_oNetDvrDeviceInfoV30.byChanNum;
        } else if (m_oNetDvrDeviceInfoV30.byIPChanNum > 0) {
            m_iStartChan = m_oNetDvrDeviceInfoV30.byStartDChan;
            m_iChanNum = m_oNetDvrDeviceInfoV30.byIPChanNum + m_oNetDvrDeviceInfoV30.byHighDChanNum * 256;
        }
        Log.i(TAG, "NET_DVR_Login is Successful!");

        return iLogID;
    }

    public boolean login() {

        if (m_iLogID < 0) {
            // login on the device
            m_iLogID = loginDevice();
            if (m_iLogID < 0) {
                Log.e(TAG, "This device logins failed!");
                return false;
            }
            // get instance of exception callback and set
            ExceptionCallBack oexceptionCbf = getExceptiongCbf();
            if (oexceptionCbf == null) {
                Log.e(TAG, "ExceptionCallBack object is failed!");
                return false;
            }

            if (!HCNetSDK.getInstance().NET_DVR_SetExceptionCallBack(oexceptionCbf)) {
                Log.e(TAG, "NET_DVR_SetExceptionCallBack is failed!");
                return false;
            }

            return true;
        }
        return true;
    }

    public boolean logOut() {
        if (!HCNetSDK.getInstance().NET_DVR_Logout_V30(m_iLogID)) {
            Log.e(TAG, " NET_DVR_Logout is failed!");
            return false;
        }

        m_iLogID = -1;
        return true;
    }

    public void startMultiPreview() {

        int i = 0;
        for (i = 0; i < playView.length; i++) {
            if (playView[i] != null) {
                int a = m_iStartChan + i;
                playView[i].startPreview(m_iLogID, a);
                playView[i].setTag(a);
            }
        }
        m_iPlayID = playView[0].m_iPreviewHandle;

    }

    public void startSinglePreview(int num) {
        if (m_iPlaybackID >= 0) {
            Log.i(TAG, "Please stop palyback first");
            return;
        }
        RealPlayCallBack fRealDataCallBack = getRealPlayerCbf();
        if (fRealDataCallBack == null) {
            Log.e(TAG, "fRealDataCallBack object is failed!");
            return;
        }
        Log.i(TAG, "m_iStartChan:" + m_iStartChan);

        NET_DVR_PREVIEWINFO previewInfo = new NET_DVR_PREVIEWINFO();
        previewInfo.lChannel = num;
        previewInfo.dwStreamType = 1; //substream
        previewInfo.bBlocked = 1;
        // HCNetSDK start preview
        m_iPlayID = HCNetSDK.getInstance().NET_DVR_RealPlay_V40(m_iLogID, previewInfo, fRealDataCallBack);
        if (m_iPlayID < 0) {
            Log.e(TAG, "NET_DVR_RealPlay is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            return;
        }

        Log.i(TAG, "NetSdk Play sucess ***********************3***************************");
    }

    public void stopMultiPreview() {
        int i = 0;
        for (i = 0; i < playView.length; i++) {
            playView[i].stopPreview();
        }
        m_iPlayID = -1;
    }

    public void stopSinglePreview() {
        if (m_iPlayID < 0) {
            Log.e(TAG, "m_iPlayID < 0");
            return;
        }

        //  net sdk stop preview
        if (!HCNetSDK.getInstance().NET_DVR_StopRealPlay(m_iPlayID)) {
            Log.e(TAG, "StopRealPlay is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            return;
        }

        m_iPlayID = -1;
        stopSinglePlayer();
    }

    private void stopSinglePlayer() {
        Player.getInstance().stopSound();
        // player stop play
        if (!Player.getInstance().stop(m_iPort)) {
            Log.e(TAG, "stop is failed!");
            return;
        }

        if (!Player.getInstance().closeStream(m_iPort)) {
            Log.e(TAG, "closeStream is failed!");
            return;
        }
        if (!Player.getInstance().freePort(m_iPort)) {
            Log.e(TAG, "freePort is failed!" + m_iPort);
            return;
        }
        m_iPort = -1;
    }


    private ExceptionCallBack getExceptiongCbf() {
        ExceptionCallBack oExceptionCbf = new ExceptionCallBack() {
            public void fExceptionCallBack(int iType, int iUserID, int iHandle) {
            }
        };
        return oExceptionCbf;
    }

    //@Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surface is created" + m_iPort);
        if (-1 == m_iPort) {
            return;
        }
        Surface surface = holder.getSurface();
        if (true == surface.isValid()) {
            if (false == Player.getInstance().setVideoWindow(m_iPort, 0, holder)) {
                Log.e(TAG, "Player setVideoWindow failed!");
            }
        }
    }

    //@Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        Log.e(TAG, "surfaceChanged");
    }

    RealPlayCallBack cbf = (iRealHandle, iDataType, pDataBuffer, iDataSize) -> {
        // player channel 1
        processRealData(1, iDataType, pDataBuffer, iDataSize, Player.STREAM_REALTIME);
    };

    public void processRealData(int iPlayViewNo, int iDataType, byte[] pDataBuffer, int iDataSize, int iStreamMode) {
        if (!m_bNeedDecode) {
            //   Log.i(TAG, "iPlayViewNo:" + iPlayViewNo + ",iDataType:" + iDataType + ",iDataSize:" + iDataSize);
        } else {
            if (HCNetSDK.NET_DVR_SYSHEAD == iDataType) {
                if (m_iPort >= 0) {
                    return;
                }
                m_iPort = Player.getInstance().getPort();
                if (m_iPort == -1) {
                    Log.e(TAG, "getPort is failed with: " + Player.getInstance().getLastError(m_iPort));
                    return;
                }
                Log.i(TAG, "getPort succ with: " + m_iPort);
                if (iDataSize > 0) {
                    if (!Player.getInstance().setStreamOpenMode(m_iPort, iStreamMode))  //set stream mode
                    {
                        Log.e(TAG, "setStreamOpenMode failed");
                        return;
                    }
                    if (!Player.getInstance().openStream(m_iPort, pDataBuffer, iDataSize, 2 * 1024 * 1024)) //open stream
                    {
                        Log.e(TAG, "openStream failed");
                        return;
                    }
                    if (!Player.getInstance().play(m_iPort, m_osurfaceView.getHolder())) {
                        Log.e(TAG, "play failed");
                        return;
                    }
                    if (!Player.getInstance().playSound(m_iPort)) {
                        Log.e(TAG, "playSound failed with error code:" + Player.getInstance().getLastError(m_iPort));
                        return;
                    }
                }
            } else {
                if (!Player.getInstance().inputData(m_iPort, pDataBuffer, iDataSize)) {
//		    		Log.e(TAG, "inputData failed with: " + Player.getInstance().getLastError(m_iPort));
                    for (int i = 0; i < 4000 && m_iPlaybackID >= 0; i++) {
                        if (!Player.getInstance().inputData(m_iPort, pDataBuffer, iDataSize))
                            Log.e(TAG, "inputData failed with: " + Player.getInstance().getLastError(m_iPort));
                        else
                            break;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();

                        }
                    }
                }

            }
        }

    }

    private RealPlayCallBack getRealPlayerCbf() {

        return cbf;
    }

    /**
     * 抓图
     * @param num
     * @return
     */
    public Bitmap setJpg(int num) {
        NET_DVR_JPEGPARA jpeg = new NET_DVR_JPEGPARA();
        INT_PTR a = new INT_PTR();
        /* a.iValue = 1024; */
        byte[] data = new byte[1024 * 1024];
        // 设置图片的分辨率
        jpeg.wPicSize = 0;
        // 设置图片质量
        jpeg.wPicQuality = 0;
        /** 1.userId 返回值 2.通道号 3.图像参数 4.JPEG数据的缓冲区 5.输入缓冲区大小 6.返回图片数据的大小 */
        boolean is = HCNetSDK.getInstance()
                .NET_DVR_CaptureJPEGPicture_NEW(m_iLogID, num, jpeg, data, 1024 * 1024, a);
        Log.e(TAG, "setJpg: " + is + "  "
                + HCNetSDK.getInstance().NET_DVR_GetLastError());

        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

        return bitmap;

    }

    //@Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG, "Player setVideoWindow release!" + m_iPort);
        if (-1 == m_iPort) {
            return;
        }
        if (true == holder.getSurface().isValid()) {
            if (false == Player.getInstance().setVideoWindow(m_iPort, 0, null)) {
                Log.e(TAG, "Player setVideoWindow failed!");
            }
        }
    }

    public int getM_iPort() {
        return m_iPort;
    }

    public void setM_iPort(int m_iPort) {
        this.m_iPort = m_iPort;
    }

    public int getMyIChanNum() {
        return myIChanNum;
    }

    public void setMyIChanNum(int myIChanNum) {
        this.myIChanNum = myIChanNum;
    }

    public int getM_iStartChan() {
        return m_iStartChan;
    }

}
