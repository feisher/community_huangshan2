package com.yusong.community.ui.shoppers.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/3/2.
 * 订单操作
 */

public interface ImplHandleOrderPersenter extends BasePresenter {
    void cancelOreder(String orderId);

    void affirmOrder(String orderId);

    void reimburseOrder(String orderId,String returnReason);
}
