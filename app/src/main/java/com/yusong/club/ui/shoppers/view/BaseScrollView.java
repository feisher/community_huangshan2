package com.yusong.club.ui.shoppers.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author Mr_Peng
 * @created at 2017/7/4 9:14.
 * @describe: 获取滑动高度
 */

public class BaseScrollView extends ScrollView {
    private ScrollViewListener scrollViewListener = null;


    public BaseScrollView(Context context) {
        super(context);
    }

    public BaseScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface ScrollViewListener {
        void onScrollChanged(int x, int y, int oldx, int oldy);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    //    onScrollChanged里面有4个参数，l代表滑动后当前ScrollView可视界面的左上角在整个ScrollView的X轴中的位置，oldi也就是滑动前的X轴位置了。
//    同理，t也是当前可视界面的左上角在整个ScrollView的Y轴上的位置，oldt也就是移动前的Y轴位置了。
    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(x, y, oldx, oldy);
        }
    }

}
