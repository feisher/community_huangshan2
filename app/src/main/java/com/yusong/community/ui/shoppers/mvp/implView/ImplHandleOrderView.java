package com.yusong.community.ui.shoppers.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;

/**
 * Created by Mr_Peng on 2017/3/2.
 * 取消订单
 */

public interface ImplHandleOrderView extends BaseView {
    void cancelSucced();

    void affirmSucced();

    void reimburseSucced();
}
