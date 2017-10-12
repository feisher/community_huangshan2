package com.yusong.community.ui.supermarket.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-08.
 * @describe: null
 */

public interface SmPayPresenter extends BasePresenter {
    void balanceSmPay(String orderId);

    void weixinSmPay(String orderId);

    void zfbSmPay(String orderId);
}
