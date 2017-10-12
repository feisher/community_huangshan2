package com.yusong.community.ui.charge.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.charge.bean.OrderDetailsBean;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public interface IChargeOrderDetailsView extends BaseView {
    void refreshView(OrderDetailsBean data);
}
