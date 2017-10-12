package com.yusong.community.ui.community_service.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * @author Mr_Peng
 * @created at 2017-09-23.
 * @describe: null
 */

public interface ServiceOrderPresenter extends BasePresenter {
    void queryServiceOrderList(String type, int offset, int limit);

    void commitServiceComment(String orderId, String content);

    void cancelServiceOrder(String orderId);

    void confirmOrder(String orderId);
}
