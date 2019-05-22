package com.example.humiture.ui.view;

import android.content.Context;
import android.widget.TextView;

import com.example.humiture.R;
import com.example.humiture.utils.helper.DataTypeHelper;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

/**
 * Created by 许格.
 * Date on 2019/4/25.
 * dec:图表点击显示详细数字
 */
public class LineChartMarker extends MarkerView {

    TextView time;
    TextView number;
    ValueFormatter value;
    int type;
    DecimalFormat decimalFormat=new DecimalFormat("0.0");//构造方法的字符格式这里如果小数不足1位,会以0补足.
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     */
    public LineChartMarker(Context context, ValueFormatter valueFormatter, int type) {
        super(context, R.layout.item_marker);
        this.value = valueFormatter;
        this.type = type;
        time= findViewById(R.id.marker_time);
        number = findViewById(R.id.marker_number);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        time.setText("时间:"+value.getFormattedValue(e.getX(),null));
        if (type<3) {
            number.setText(DataTypeHelper.getDataNames().get(type) + ":" + decimalFormat.format(e.getY()) + DataTypeHelper.getDataUnit().get(type));
        }else {
            number.setText(DataTypeHelper.getDataNames().get(type) + ":" + decimalFormat.format(e.getY()) + DataTypeHelper.getDataUnit().get(3));
        }
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), getHeight()/2);
    }

}