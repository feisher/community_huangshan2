package com.yusong.community.ui.shoppers.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 商城订单
 */

public interface ImplShopOrderPersenter extends BasePresenter {
    void queryShopOrder(String type , int offset,int limit);
}
