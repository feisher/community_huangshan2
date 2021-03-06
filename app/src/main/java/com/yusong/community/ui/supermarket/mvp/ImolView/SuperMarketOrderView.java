package com.yusong.community.ui.supermarket.mvp.ImolView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.shoppers.bean.CreataOrderBean;
import com.yusong.community.ui.shoppers.bean.OrderShopBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-06.
 * @describe: null
 */

public interface SuperMarketOrderView extends BaseView {
    void createSucced(CreataOrderBean bean);

    void querySMOrderSucced(List<OrderShopBean> data);

    void querySMOrderError();
}
