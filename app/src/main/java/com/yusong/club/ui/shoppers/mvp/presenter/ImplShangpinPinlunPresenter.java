package com.yusong.club.ui.shoppers.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by Mr_Peng on 2017/3/2.
 * 添加商品评论
 */

public interface ImplShangpinPinlunPresenter extends BasePresenter {
    void pinLun(String orderId, String content);
}
