package com.example.humiture.utils;

import android.widget.Toast;
import com.example.humiture.app.App;

/**
 * Created by 许格.
 * time 15:06
 * des 吐司帮助类
 */
public class ToastUtils {
    private static Toast mToast;

    /**
     * 显示Toast
     */
    public static void showToast(CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

    public static void showToast(CharSequence text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getInstance(), text, duration);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

}
