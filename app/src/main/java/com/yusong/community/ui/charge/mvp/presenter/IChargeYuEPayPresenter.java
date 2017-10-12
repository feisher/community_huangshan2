package com.yusong.community.ui.charge.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface IChargeYuEPayPresenter extends BasePresenter {
    void yuEPay(String orderId, float price ,int volume);
}
