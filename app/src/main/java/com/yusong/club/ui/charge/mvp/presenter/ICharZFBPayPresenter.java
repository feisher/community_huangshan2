package com.yusong.club.ui.charge.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface ICharZFBPayPresenter extends BasePresenter {
    void ZFBPay(String orderId, float price, int volume);
}
