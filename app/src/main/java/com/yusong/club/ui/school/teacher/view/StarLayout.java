package com.yusong.club.ui.school.teacher.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yusong.club.R;


/**
 * @Description TODO
 * Created by ds on
 */

public class StarLayout extends LinearLayout implements View.OnClickListener {
    private static final String TAG = "StarLayout";
    private Context context;
    private int num;
    private boolean isHalf;
    private boolean isClick;
    private float starPoint;
    private OnClickBackListener listener;

    public StarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        TypedArray t = getContext().obtainStyledAttributes(attrs, R.styleable.StarLayout);
        int space = t.getInt(R.styleable.StarLayout_space, 0);
        isClick = t.getBoolean(R.styleable.StarLayout_clickable, false);
        for (int i = 0; i < 5; i++) {
            LinearLayout ll = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.star, null);
            ImageView star = (ImageView) ll.findViewById(R.id.star);
            star.setImageResource(R.mipmap.grade_half_big);
            LayoutParams lp =
                    new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.leftMargin = space;
            lp.rightMargin = space;
            star.setTag(i);
            star.setOnClickListener(this);
            ll.setLayoutParams(lp);
            addView(ll);
        }
        float point = t.getFloat(R.styleable.StarLayout_point, 0f);
        setStars(point);
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setStars(float point) {
        if (point < 0 || point > 5) {
            Log.e(TAG, point + " is out");
            return;
        }
        starPoint = point;
        if (point == 0) {
            for (int i = 0; i < 5; i++) {
                ImageView iv = (ImageView) ((LinearLayout) getChildAt(i)).getChildAt(0);
                iv.setImageResource(R.mipmap.big_ic_star_unselected);
            }
            return;
        }
        if (point == 5) {
            for (int i = 0; i < 5; i++) {
                ImageView iv = (ImageView) ((LinearLayout) getChildAt(i)).getChildAt(0);
                iv.setImageResource(R.mipmap.big_ic_star_selected);
            }
            return;
        }
        num = (int) point;
        float half = (point - num) * 10;
        if (half >= 5) {
            isHalf = true;
        } else {
            isHalf = false;
        }
        for (int i = 0; i < num; i++) {
            ImageView iv = (ImageView) ((LinearLayout) getChildAt(i)).getChildAt(0);
            iv.setImageResource(R.mipmap.big_ic_star_selected);
        }
        if (isHalf) {
            ImageView iv = (ImageView) ((LinearLayout) getChildAt(num)).getChildAt(0);
            iv.setImageResource(R.mipmap.grade_half_big);
        } else {
            ImageView iv = (ImageView) ((LinearLayout) getChildAt(num)).getChildAt(0);
            iv.setImageResource(R.mipmap.big_ic_star_unselected);
        }
        for (int i = num + 1; i < 5; i++) {
            ImageView iv = (ImageView) ((LinearLayout) getChildAt(i)).getChildAt(0);
            iv.setImageResource(R.mipmap.big_ic_star_unselected);
        }
    }

    public float getStarPoint() {
        return starPoint;
    }

    public void setListener(OnClickBackListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (isClick) {
            int pos = (int) view.getTag()+1;
            if(pos == 1){
                setStars(pos);
//                return;
            }
            int point = (int) getStarPoint();
            if(pos == point){
                setStars(pos-1);
            }else{
                setStars(pos);
            }

            if (listener!=null)
                listener.onClick(getStarPoint());

        }
    }

    public interface OnClickBackListener{
        public void onClick(float point);
    }
}
