package com.yusong.club.ui.shoppers.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 商城订单
 */

public interface ImplShopOrderPersenter extends BasePresenter {
    void queryShopOrder(String type , int offset,int limit);
}
