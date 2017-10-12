package com.yusong.community.ui.charge.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Mr_Peng on 2017/1/4.
 */

public class TimerView extends View {

    private Paint paint;
    private RectF rectF;
    private int width;
    private int height;
    private int mCircleLineStrokeWidth;
    private String countDown = "倒计时";
    private int percentage = 0;//百分比
    private int allSecond = 0;//总秒数
    public int second = 0;

    private boolean isRun = false;
    private Handler handler;
    private Runnable timer;


    public TimerView(Context context) {
        super(context);
    }

    public TimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = this.getWidth();
        height = this.getHeight();
        if (width != height) {
            int min = Math.min(width, height);
            width = min;
            height = min;
        }
        mCircleLineStrokeWidth = width / 19;
        paint = new Paint();
        rectF = new RectF();

        paint.setAntiAlias(true);
        paint.setColor(Color.rgb(35, 150, 216));
        canvas.drawColor(Color.TRANSPARENT);
        paint.setStrokeWidth(mCircleLineStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);

        rectF.left = mCircleLineStrokeWidth / 2;
        rectF.top = mCircleLineStrokeWidth / 2;
        rectF.right = width - mCircleLineStrokeWidth / 2;
        rectF.bottom = height - mCircleLineStrokeWidth / 2;
        canvas.drawArc(rectF, -90, 360, false, paint);
        paint.setColor(Color.rgb(14, 204, 116));
        canvas.drawArc(rectF, -90, ((float) second / allSecond) * 360, false, paint);


        paint.setStrokeWidth(16);
        paint.setColor(Color.WHITE);
        int min = second / 60;
        int miao = second % 60;
        if (second >= allSecond) {
            min = 0;
            miao = 0;
        }
        String text = String.format("%s:%s", min < 10 ? "0" + min : min, miao < 10 ? "0" + miao : miao);
        int textHeight = height / 4;
        paint.setTextSize(textHeight);
        int textWidth = (int) paint.measureText(text, 0, text.length());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, width / 2 - textWidth / 2, height / 2 + textHeight / 2, paint);

        paint.setStrokeWidth(10);
        textHeight = height / 8;
        paint.setTextSize(textHeight);
        textWidth = (int) paint.measureText(text, 0, text.length());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(countDown, width / 2 - textWidth / 2, height / 4 + textHeight / 2, paint);

        float pre = ((float) second / allSecond) * 100;
        if (pre < 100) {
            percentage = 100 - (int) pre;
        } else {
            percentage = 100;
        }

        text = String.format("已充%d", (int) percentage > 100 ? 100 : (int) percentage);
        textHeight = height / 12;
        paint.setTextSize(textHeight);
        textWidth = (int) paint.measureText(text, 0, text.length());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text + "%", width / 2 - textWidth / 2, 3 * height / 4 + textHeight / 2, paint);
    }

    public void setSecond(int nowSecond, int allSecind) {
        this.second = nowSecond;
        this.allSecond = allSecind;
        if (second > allSecind) {
            countDown = "已完成";
            this.second = allSecond;
            postInvalidate();
            return;
        }

        handler = new Handler();
        timer = new Runnable() {
            public void run() {
                if (isRun || second <= 0) {
                    countDown = "已完成";
                    postInvalidate();
                    return;
                }
                second--;
                long now = SystemClock.uptimeMillis();
                long next = now + (1000 - System.currentTimeMillis() % 1000);
                postInvalidate();
                handler.postAtTime(timer, next);
            }
        };
        timer.run();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isRun = true;
    }
}

//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int widthSize;
//        int heightSize;
//
//        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED) {
//            widthSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEF_VIEW_SIZE, getResources().getDisplayMetrics());
//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
//        }
//
//        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED) {
//            heightSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEF_VIEW_SIZE, getResources().getDisplayMetrics());
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
//        }
//        Log.d("", widthMeasureSpec + "-" + heightMeasureSpec);
