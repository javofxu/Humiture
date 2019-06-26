package com.example.humiture.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.humiture.R;
import com.example.humiture.utils.ToastUtils;

import java.util.List;

public class RadarView extends View {

    private final static String TAG = RadarView.class.getSimpleName();

    private List<RadarData> dataList;

    private int count = 6;//雷达网圈数
    private float angle;//多边形弧度
    private float radius;
    private float maxValue = 100f;
    private Paint mainPaint;//雷达区画笔
    private Paint valuePaint;//数据区画笔
    private Paint textPaint;//文本画笔
    private Paint textPaintPercentage; //百分比画笔

    private int mainColor = 0xFF888888;//雷达区颜色
    private int valueColor = 0xFF79D4FD;//数据区颜色
    private int textColor = 0xff333333;//文本颜色
    private int textpercentageColor = 0xffffa800;//百分比颜色

    private float mainLineWidth = 0.5f;//雷达网线宽度dp
    private float valueLineWidth = 1f;//数据区边宽度dp
    private float valuePointRadius = 2f;//数据区圆点半径dp
    private float textSize = 12f;//字体大小sp
    private float textPercentageSize = 10f;

    private int mWidth, mHeight;

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setup();
    }

    private void setup() {
        //雷达区
        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(mainColor);
        mainPaint.setStyle(Paint.Style.STROKE);

        //数据区
        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setColor(valueColor);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        //文本区
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(textColor);

        //百分比
        textPaintPercentage = new Paint();
        textPaintPercentage.setAntiAlias(true);
        textPaintPercentage.setStyle(Paint.Style.FILL);
        textPaintPercentage.setColor(textpercentageColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w) / 2 * 0.6f;
        mWidth = w;
        mHeight = h;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);

        if (isDataListValid()) {
            drawSpiderweb(canvas);
            drawText(canvas);
            drawRegion(canvas);
        }
    }

    /**
     * 绘制蜘蛛网
     *
     * @param canvas
     */
    private void drawSpiderweb(Canvas canvas) {
        //设置画笔的粗细度
        mainPaint.setStrokeWidth(dip2px(getContext(), mainLineWidth));
        //设置线的颜色
        mainPaint.setColor(getResources().getColor(R.color.state_radarview));
        Path webPath = new Path();
        Path linePath = new Path();
        float r = radius / (count - 1);//蜘蛛丝之间的间距
        for (int i = 0; i < count; i++) {
            float curR = r * i;//当前半径
            webPath.reset();
            for (int j = 0; j < count; j++) {
                float x = (float) (curR * Math.sin(angle / 2 + angle * j));
                float y = (float) (curR * Math.cos(angle / 2 + angle * j));
                if (j == 0) {
                    webPath.moveTo(x, y);
                } else {
                    webPath.lineTo(x, y);
                }
                if (i == count - 1) {//当绘制最后一环时绘制连接线
                    linePath.reset();
                    linePath.moveTo(0, 0);
                    linePath.lineTo(x, y);
                    canvas.drawPath(linePath, mainPaint);
                }
            }
            webPath.close();
            canvas.drawPath(webPath, mainPaint);
        }
    }

    /**
     * 绘制文本
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        textPaint.setTextSize(sp2px(getContext(), textSize));
        textPaintPercentage.setTextSize(sp2px(getContext(), textPercentageSize));
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {
            float x = (float) ((radius + fontHeight * 2) * Math.sin(angle / 2 + angle * i));
            float y = (float) ((radius + fontHeight * 2) * Math.cos(angle / 2 + angle * i));
            String title = dataList.get(i).getTitle();
            Log.i(TAG, "drawText: " + title);
            int percentage = dataList.get(i).getPercentage();
            float dis = textPaint.measureText(title);//文本长度
            float disp = textPaint.measureText(percentage+"%");
            Log.i(TAG, "drawText: " + dis + "--------------" + disp);
            //绘制文本
            canvas.drawText(title, x - dis / 2, y, textPaint);
            //绘制百分比
            canvas.drawText(percentage+"%",x-disp / 2,y + disp/2,textPaintPercentage);
        }
    }

    /**
     * 绘制区域
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        valuePaint.setStrokeWidth(dip2px(getContext(), valueLineWidth));
        Path path = new Path();
        //设置填充区域边的透明度，255为最大，看起来像一条线，0为最小，看起来没东西
        valuePaint.setAlpha(0);
        path.reset();

        for (int i = 0; i < count; i++) {
            double percent = dataList.get(i).getPercentage() / maxValue;
            float x = (float) (radius * Math.sin(angle / 2 + angle * i) * percent);
            float y = (float) (radius * Math.cos(angle / 2 + angle * i) * percent);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
            //绘制小圆点
            canvas.drawCircle(x, y, dip2px(getContext(), valuePointRadius), valuePaint);
        }
        path.close();
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setAlpha(128);
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }


    private boolean isDataListValid() {
        return dataList != null && dataList.size() >= 3;
    }

    /**
     * 根据数据开始画图
     * @param dataList
     */
    public void setDataList(List<RadarData> dataList) {
        if (dataList == null || dataList.size() < 3) {
            throw new RuntimeException("The number of data can not be less than 3");
        } else {
            this.dataList = dataList;
            count = dataList.size();//圈数等于数据个数，默认为6
            angle = (float) (Math.PI * 2 / count);
            invalidate();
        }
    }

    /**
     * 修改参数，改变画笔的粗细
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 2.0f);
    }

    /**
     * 设置文本的字体大小
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 5.0f);
    }

}
