package com.yusong.community.ui.supermarket.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-08.
 * @describe: null
 */

public interface OrderHandlePresenter extends BasePresenter {

    void commitSMComment(String orderId, String content);

    void confirmSMReceipt(String orderId);

    void salesSMReturn(String orderId, String returnReason);

    void cancelSmOrder(String orderId);
}
