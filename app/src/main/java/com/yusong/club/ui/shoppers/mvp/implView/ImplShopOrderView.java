package com.yusong.club.ui.shoppers.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.shoppers.bean.OrderShopBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 商城订单
 */

public interface ImplShopOrderView extends BaseView {
    void orderSucced(List<OrderShopBean> data);
    void orderClose();
}
