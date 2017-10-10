package com.yusong.club.ui.express.mvp.implView;

import android.content.Intent;

import com.yusong.club.mvp.implView.BaseView;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface IFillInfoView extends BaseView {

    

    void close();

    void result(int respons, Intent intent);
}
