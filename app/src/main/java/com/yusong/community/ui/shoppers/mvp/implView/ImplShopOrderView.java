package com.yusong.community.ui.shoppers.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.bean.OrderShopBean;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 商城订单
 */

public interface ImplShopOrderView extends BaseView {
    void orderSucced(List<OrderShopBean> data);
    void orderClose();
}
