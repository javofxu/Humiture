package com.example.humiture.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *Time:2019/7/1
 *Author:冰冰凉
 *dec: 将BitMap保存为图片.jpg
 */
public class SaveBitMap {

    /**
     * 将bitmap保存为.jpg图片
     * @param bmp
     * @param context
     */
    public void saveBitmap(Bitmap bmp,Activity context) {
        //首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Humiture");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        Date nowtime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String retStrFormatNowDate = sdFormatter.format(nowtime);
        String fileName = retStrFormatNowDate + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "抓图成功", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "抓图失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file)));
    }

    /**
     * 使用文件管理器打开指定的文件夹
     */
    public void openAssignFolder(Context context){
        File file = new File(Environment.getExternalStorageDirectory(), "Humiture");
        if(null==file || !file.exists()){
            return;
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(Uri.fromFile(file), "*/*");
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到图库
     */
    public void openImg(Activity activity){
        Intent intent = new Intent();
        if(Build.VERSION.SDK_INT < 19){
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
        }else{
            intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        }
        activity.startActivity(intent);
    }

}
