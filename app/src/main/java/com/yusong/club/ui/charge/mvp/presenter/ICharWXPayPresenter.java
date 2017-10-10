package com.yusong.club.ui.charge.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface ICharWXPayPresenter extends BasePresenter {
    void WXPay(String orderId,float price,int volume);
}
