package com.yusong.club.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by quaner on 16/12/27.
 * <p>
 * 用于设置recyclerView条目距离
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int verticalSpace;
    private int horizontalSpace;

    public SpaceItemDecoration(int verticalSpace, int horizontalSpace) {
        this.verticalSpace = verticalSpace;
        this.horizontalSpace = horizontalSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        //设置左右的间隔
        outRect.left = horizontalSpace;
        outRect.right = horizontalSpace;

//            if(parent.getChildPosition(view) != parent.getChildCount() - 1)
//            outRect.bottom = horizontalSpace;

        //设置上间距
        if (parent.getChildPosition(view) != -1){
            outRect.top = verticalSpace;
        }

    }
}