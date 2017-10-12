package com.yusong.community.ui.charge.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.R;

/**
 * Created by Mr_Peng on 2016/12/29.
 */

public class SelectMoneyLayout extends LinearLayout {
    private TextView moneyTv, timeTv, quantityTv;
    private ImageView selectImage;

    public SelectMoneyLayout(Context context) {
        super(context);
    }


    public SelectMoneyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.select_money_layout, this);
        moneyTv = (TextView) findViewById(R.id.layout_money_tv);
        timeTv = (TextView) findViewById(R.id.layout_time_tv);
        quantityTv = (TextView) findViewById(R.id.layout_quantity_tv);
        selectImage = (ImageView) findViewById(R.id.xuanzhong_image);
    }

    public void setLayoutText(String money, String time, String quantituTv) {
        moneyTv.setText(String.format("%s 元充", money));
        timeTv.setText(String.format("%s 分钟", time));
        quantityTv.setText(quantituTv + "%电量");
    }

    public void setLayoutBuluColor() {
        moneyTv.setTextColor(Color.parseColor("#1DACFF"));
        timeTv.setTextColor(Color.parseColor("#1DACFF"));
        quantityTv.setTextColor(Color.parseColor("#1DACFF"));
        selectImage.setVisibility(View.VISIBLE);
    }

    public void setLayoutBackColor() {
        moneyTv.setTextColor(Color.parseColor("#555555"));
        timeTv.setTextColor(Color.parseColor("#888888"));
        quantityTv.setTextColor(Color.parseColor("#888888"));
        selectImage.setVisibility(View.GONE);
    }
}
