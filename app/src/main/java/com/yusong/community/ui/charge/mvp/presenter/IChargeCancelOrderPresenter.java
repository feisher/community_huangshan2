package com.yusong.community.ui.charge.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by ruanjian4 on 2017/1/17.
 */

public interface IChargeCancelOrderPresenter extends BasePresenter {
    void cancelOrder(String orderId);
}
