package com.yusong.community.ui.express.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public interface ISenderOrderDetailsPresenter extends BasePresenter {
    void querySenderDetails(String id);

    void scanOrder(String no);

    void querySaveDetails(String orderid);
}
