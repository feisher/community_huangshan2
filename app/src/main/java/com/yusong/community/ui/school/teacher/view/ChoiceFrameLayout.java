package com.yusong.community.ui.school.teacher.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.FrameLayout;

/**
 * Created by dingsheng on 2016/12/16.
 */

public class ChoiceFrameLayout extends FrameLayout implements Checkable {
    View mCheckableView;

    public ChoiceFrameLayout(Context context) {
        super(context);
    }

    public ChoiceFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChoiceFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ChoiceFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        View checkableView = selectCheckable(this);
        if (checkableView != null) {
            mCheckableView = checkableView;
        }
    }

    @Override
    public void setChecked(boolean checked) {
        if (mCheckableView != null) {
            ((Checkable) mCheckableView).setChecked(checked) ;
        }
    }

    @Override
    public boolean isChecked() {
        return mCheckableView != null && ((Checkable) mCheckableView).isChecked();
    }

    /**
     * 切唤当前选中状态
     */
    @Override
    public void toggle() {
        if (mCheckableView != null) {
            Checkable checkable = (Checkable)mCheckableView;
//            checkable.setChecked(!checkable.isChecked());
            checkable.toggle();
        }
    }

    /**
     * 遍历搜索ViewGroup中是否存在Checkable
     */
    private View selectCheckable(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = viewGroup.getChildAt(i);
            if (childView instanceof Checkable) {
                return childView;
            } else if (childView instanceof ViewGroup) {
                return selectCheckable((ViewGroup) childView);
            }
        }

        return null;
    }
}
