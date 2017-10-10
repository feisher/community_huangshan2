package com.yusong.club.ui.charge.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface IChargeYuEPayView extends BaseView {
    void refreshView();
    void yuEMessage(String message);
}
