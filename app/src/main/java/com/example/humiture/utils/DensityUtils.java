package com.example.humiture.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by 许格.
 * Date on 2019/5/16.
 * dec:px dp 转换util
 */
public class DensityUtils {
    private static ViewGroup.LayoutParams params;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public static final float getHeightInPx(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    public static final float getWidthInPx(Context context) {
        final float width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    public static final int getHeightInDp(Context context) {
        final float height = context.getResources().getDisplayMetrics().heightPixels;
        int heightInDp = px2dip(context, height);
        return heightInDp;
    }

    public static final int getWidthInDp(Context context) {
        final float height = context.getResources().getDisplayMetrics().widthPixels;
        int widthInDp = px2dip(context, height);
        return widthInDp;
    }

    /**
     * 获取顶部 status bar 高度
     *
     * @param context
     * @return
     */
    public static final int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    /**
     * 屏幕适配算法（宽）
     */
    public static void getAdaptationWidth(int measureWidth, int measureScreenWidth, View view, Context context) {
        int x = measureWidth * getScreenWidth(context) / measureScreenWidth;
        params = view.getLayoutParams();
        params.width = x;
        view.setLayoutParams(params);
    }

    /**
     * 屏幕适配算法（高）
     */
    public static void getAdaptationHeight(int measureHeight, int measureScreenWidth, View view, Context context) {
        int y = measureHeight * getScreenWidth(context) / measureScreenWidth;
        params = view.getLayoutParams();
        params.height = y;
        view.setLayoutParams(params);
    }

    /**
     * 屏幕适配算法（宽高）
     */
    public static void getAdaptationWH(int measureHeight, int measureScreenWidth, View view, Context context) {
        int y = measureHeight * getScreenWidth(context) / measureScreenWidth;
        int x = measureHeight * getScreenWidth(context) / measureScreenWidth;
        params = view.getLayoutParams();
        params.width = x;
        params.height = y;
        view.setLayoutParams(params);
    }

    /**
     * 屏幕适配算法2（宽高）
     */
    public static void getAdaptationWH(int measureWidth, int measureHeight, int measureScreenWidth, View view, Context context) {
        int y = measureHeight * getScreenWidth(context) / measureScreenWidth;
        int x = measureWidth * getScreenWidth(context) / measureScreenWidth;
        params = view.getLayoutParams();
        params.width = x;
        params.height = y;
        view.setLayoutParams(params);
    }

    /**
     * 动态显示dialog的大小
     * @param dlg
     * @param ctx
     */
    //在dialog.show()之后调用
    public static void setDialogWindowAttr(Dialog dlg, Context ctx){
        Window window = dlg.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = (int) (getScreenWidth(ctx)* 0.8);
        lp.height = (int) (getScreenHeight(ctx) * 0.3);
        dlg.getWindow().setAttributes(lp);
    }
}
