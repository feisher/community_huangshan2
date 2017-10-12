package com.yusong.community.ui.charge.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface IChargeYuEPayView extends BaseView {
    void refreshView();
    void yuEMessage(String message);
}
